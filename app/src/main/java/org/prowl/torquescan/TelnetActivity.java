package org.prowl.torquescan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Timer;
import java.util.Vector;

import org.prowl.torque.remote.ITorqueService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Scan a group of PIDs for a response
 */
public class TelnetActivity extends Activity {

   private static final Vector<String> tipsShown = new Vector();
   private ITorqueService torqueService;

   public static final String SCAN = "Start Scan";
   public static final String SEND = "Email logs";


   private Thread socketHandler;
   private boolean running = true;

   private TextView textView;
   private Handler handler;
   private NumberFormat nf;

   private Timer updateTimer;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      LayoutInflater inflater = LayoutInflater.from(this);
      View view = inflater.inflate(R.layout.main, null);
      textView = (TextView)view.findViewById(R.id.textview);
      textView.setText("Telnet Server starting...");

      // Max of 2 digits for readings.
      nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(2);

      handler = new Handler();

      setContentView(view);
   }


   ServerSocket socket;
   protected void onResume() {
      super.onResume();
      socket = null;
      // Bind to the torque service
      Intent intent = new Intent();
      intent.setClassName("org.prowl.torque", "org.prowl.torque.remote.TorqueService");
      boolean successfulBind = bindService(intent, connection, 0);

      if (successfulBind) {
         //updateTimer = new Timer();
         //updateTimer.schedule(new TimerTask() { public void run() {
         // if (torqueService != null) {
         //    update();
         // }
         //}}, 1000, 200);
      } else {
         textView.setText("Unable to connect to Torque plugin service");
      }
      textView.setKeepScreenOn(true);

     


      int port = 20001;
      
      try {
         while (port < 20301 && socket == null) {
            try {
               socket = new ServerSocket(20001);
            } catch(Throwable e) {
               port++;
            }
         } 
         if (port == 20201)
            throw new IOException("All ports in use");
         textView.setText("Telnet Server started on:"+getLocalIpAddress()+ " on port "+port);

         socketHandler = new Thread(new Runnable() { public void run() {

 
            try {

               final Socket conneciton = socket.accept();
               update("Connection accepted from: "+conneciton.getInetAddress().getHostAddress(),new String[] {""});
               if (!running) {
                  return;
               }
               InputStream in = conneciton.getInputStream();
               OutputStream out = conneciton.getOutputStream();

               final DataInputStream din = new DataInputStream(in);
               final DataOutputStream dout = new DataOutputStream(out);


               dout.write("\nTorque OBD passthrough debugging plugin\n\nAdapter commands begin with 'AT', OBD commands begin with a number. \n\nDo not use this tool if you are not familiar with debugging OBD or do not have the vehicle safely parked up.\nYou agree to not hold the author liable for any damage that may be caused by this utility.\n\n>".getBytes());

                  while (conneciton.isConnected() && !conneciton.isClosed()) {
                     if (!running) {
                        return;
                     }
                     String line =  din.readLine();
                     if (!running || line == null) {
                        return;
                     }
                     
                     try {
                        if (!torqueService.hasFullPermissions()) {
                           popupMessage("Permissions problem", "The Scanning activity requires full application permissions to be able to send specific data to the OBD adapter.\n\nPlease enable full permissions in Torque's plugin settings");
                        }
                        
                     } catch(Throwable e) { 
                        e.printStackTrace();
                     }
                     update(line,new String[] {""});

                     String[] response = torqueService.sendCommandGetResponse("", line);
                     if (response != null) {
                        update("",response);

                        for (String s: response) {

                           dout.write((s+"\n").getBytes());
                           dout.flush();
                        }
                        dout.write(">".getBytes());
                        dout.flush();
                        
                     }
                     
                     
                  }




                  update("Telnet server stopping. Restart activity to restart server.", new String[] { "" });

            } catch(Throwable e) {
              update("Telnet server closing: " + e.getMessage(), new String[] { "" } );

            }


         }});
         socketHandler.start();

      } catch(IOException e) { 
         textView.setText("Unable to start telnet server: " + e.getMessage());

      }

   }

   public String getLocalIpAddress() {
      String addrs = "";
      try {
          for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
              NetworkInterface intf = en.nextElement();
              for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                  InetAddress inetAddress = enumIpAddr.nextElement();
                  if (!inetAddress.isLoopbackAddress()) {
                      addrs += inetAddress.getHostAddress().toString()+" ";
                  }
              }
          }
      } catch (SocketException ex) {
          Log.e("TelnetActivity", ex.toString());
      }
      return addrs;
  }


   @Override
   protected void onPause() {
      try {
         if (socket != null)
            socket.close();
      } catch(Throwable e) {
         
      }
      // TODO Auto-generated method stub
      super.onPause();

      if (updateTimer != null)
         updateTimer.cancel();

      tip("Press menu for options");
      unbindService(connection);
   }

   /**
    * Do an update
    */
   String text = "";
   public void update(String upText, String[] myResp) {
      // String used for code readability.\

      if (myResp.length == 1) {
         text = text + upText+myResp[0]+"\n";         
      } else {
         text = text + upText+"\n";

         for (String r: myResp)
            text = text +"  "+ r+"\n";
      }

      // Update the widget.
      final String myText = text;
      handler.post(new Runnable() { public void run() {
         try {
            textView.setText(myText);
         } catch(Throwable e) { 
            e.printStackTrace();
         }
      }});

   }


   public boolean onMenuItemSelected(int featureId, MenuItem item) {
      if (SEND.equals(item.getTitle())) {
         sendEmail();
      }
      return true;
   }


   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(SEND).setIcon(android.R.drawable.ic_menu_share);
      return true;
   }




   public String toHex(int i) {
      String pidHex = Integer.toString(i,16);
      if (pidHex.length() % 2 == 1) {
         pidHex="0"+pidHex;
      }
      return pidHex;
   }


   /**
    * Bits of service code. You usually won't need to change this.
    */
   private ServiceConnection connection = new ServiceConnection() {
      public void onServiceConnected(ComponentName arg0, IBinder service) {
         torqueService = ITorqueService.Stub.asInterface(service);
      };
      public void onServiceDisconnected(ComponentName name) {
         torqueService = null;
      };
   };


   public void tip(String tip) {
      if (!tipsShown.contains(tip)) {
         tipsShown.add(tip);
         toast(tip, null);
      }
   }

   public void toast(final String message) { 
      toast(message, null);
   }


   public void toast(final String message, Context c) { 
      // If not visible, then don't toast.
      // if (!isForeground)
      //    return;
      if (c == null)
         c = this;
      final Context context = c;

      handler.post(new Runnable() { public void run() {
         try {
            // try { Thread.sleep(100); } catch(InterruptedException e) { } 
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
         } catch(Throwable e) { 
            // Do nothing
         }
      }});

   }

   public void sendEmail() {
      try {

         Intent sendIntent = new Intent(Intent.ACTION_SEND);
         String text = "Please enter the vehicle type if sending to the developer, thanks!\n\n";
         sendIntent .putExtra(Intent.EXTRA_EMAIL, new String[]{"Torque Developer Debug <piemmm20@googlemail.com>"}); 
         sendIntent.setType("text/plain");
         sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Comms scan");
         sendIntent.putExtra(Intent.EXTRA_TEXT, text+textView.getText().toString());// Uri.parse("content://org.prowl.torquefree.fileprovider"+sdFile.getAbsolutePath())
         startActivity(Intent.createChooser(sendIntent, "Send:"));
      } catch(Throwable e) {
         toast("Unable to send email");
      }
   }

   public void popupMessage(final String title, final String message) {
      handler.post(new Runnable() {
         public void run() {
            try {
               final AlertDialog adialog = new AlertDialog.Builder(TelnetActivity.this).create();

               adialog.setButton("OK", new OnClickListener() {

                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                     adialog.dismiss();

                  }
               });

               ScrollView svMessage = new ScrollView(TelnetActivity.this);
               TextView tvMessage = new TextView(TelnetActivity.this);

               SpannableString spanText = new SpannableString(message);

               Linkify.addLinks(spanText, Linkify.ALL);
               tvMessage.setText(spanText);
               tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f);
               tvMessage.setMovementMethod(LinkMovementMethod.getInstance());

               svMessage.setPadding(14, 2, 10, 12);
               svMessage.addView(tvMessage);

               adialog.setTitle(title);
               adialog.setView(svMessage);

               adialog.show();
            } catch (Throwable e) {
               e.printStackTrace();
               //DebugConfig.debug(e);
            }
         }
      });
   }

}