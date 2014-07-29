package org.prowl.torquescan;

import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.prowl.torque.remote.ITorqueService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.InputType;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

/*
 * Scan a group of PIDs for a response
 */
public class ScanActivity extends Activity {

   private static final Vector<String> tipsShown = new Vector();
   private ITorqueService torqueService;

   public static final String SCAN = "Start Scan";
   public static final String SEND = "Email results";

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
      textView.setText("Press Menu for scanning options. Make sure you are connected before starting the scan.");

      // Max of 2 digits for readings.
      nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(2);

      handler = new Handler();

      setContentView(view);
   }


   @Override
   protected void onResume() {
      super.onResume();

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

     
   }



   @Override
   protected void onPause() {
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
         textView.setText(myText);
      }});

   }


   public boolean onMenuItemSelected(int featureId, MenuItem item) {
      if (SCAN.equals(item.getTitle())) {
         
         
         

         final LinearLayout layout = new LinearLayout(ScanActivity.this);
         layout.setOrientation(LinearLayout.VERTICAL);

         final EditText editText = new EditText(ScanActivity.this); 
         editText.setText("auto");
         editText.setSingleLine();
///         editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

         final CheckBox autoButton = new CheckBox(ScanActivity.this);
         autoButton.setText("Full scan (don't skip any PIDs, takes a *long* time)");
         autoButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               editText.setEnabled(!isChecked);
            }
         });
         layout.addView(editText);
         layout.addView(autoButton);

         new AlertDialog.Builder(ScanActivity.this)
         .setTitle("Enter header to use (or leave as Auto)")
         .setView(layout)
         .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
               EditText enterNumberField = editText;
               String text = enterNumberField.getText ().toString();
               scan(text, autoButton.isChecked());



            }
         }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface
                  dialog, int
                  whichButton) {
               // cancel.
            }
         }).show();

         

      } else  if (SEND.equals(item.getTitle())) {
         sendEmail();
      }
      return true;
   }


   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(SCAN).setIcon(android.R.drawable.ic_menu_search);
      menu.add(SEND).setIcon(android.R.drawable.ic_menu_share);
      return true;
   }

   boolean scanCancelled = true;
   public void scan(final String header, final boolean fullScan) {
      
      
      try {
         if (!torqueService.hasFullPermissions()) {
            popupMessage("Permissions problem", "The Scanning activity requires full application permissions to be able to send specific data to the OBD adapter.\n\nPlease enable full permissions in Torque's plugin settings");
            return;
         }
      } catch(Throwable e) { 
         e.printStackTrace();
      }
      
      textView.setText("Scanning has started. Discovered PIDs will appear here.");
      scanCancelled = false;
      // Wait for about 5 seconds then see if we got a reply.
      final ProgressDialog progressDialog = ProgressDialog.show(this, "Please wait...", "Scanning for extended PIDs. Press 'back' to stop scan", true, true);
      progressDialog.setCancelable(true);
      progressDialog.setOnCancelListener(new OnCancelListener() {

         @Override
         public void onCancel(DialogInterface dialog) {
            scanCancelled = true;
            update("Scanning was cancelled", new String[] {""});
            progressDialog.dismiss();

         }
      });

      progressDialog.show();
      final Timer timer = new Timer("Mode 22 PID Scanner");
      timer.schedule(new TimerTask() {
         public void run() {

            int i = 0x00;
            try {
               while (i++ < 0x1f) {



                  final String command = toHex(i);
                  final String[] response = torqueService.sendCommandGetResponse(header, "24"+command+"FFFF");
                  if (response != null && response.length > 0) {
                     String myResp = response[0].toLowerCase();
                     if (myResp != null && !"no data".equals(myResp) && (myResp.startsWith("64")  || myResp.length() != 6 || (!myResp.startsWith("7f") && myResp.length() == 6)) ) {
                        update("Command: 24"+command+" response:",response);
                     }
                  }

                  if (scanCancelled)
                     return;


                  handler.post(new Runnable() {
                     public void run() {
                        if (response != null && response.length > 0) {
                           String myResp = response[0]; 
                           progressDialog.setMessage("Scanning...\nCommand: 24"+command+"\nResponse:"+myResp);
                        } else {
                           progressDialog.setMessage("Scanning...\nCommand: 24"+command+"\nResponse: None");

                        }
                     }
                  });

               }
            } catch(RemoteException e) {
               update(e.getMessage(),new String[] {"" });
               Log.e("TorqueScan",e.getMessage(), e);
            }

            if (scanCancelled)
               return;

            try {
               int end = 110;
               if (fullScan)
                  end = 255;
               
               for (i = 0; i < end; i++) {

                  final String command = toHex(i);

                  final String[] response = torqueService.sendCommandGetResponse(header, "21"+command );
                  if (response != null && response.length > 0) {
                     String myResp = response[0].toLowerCase();
                     if (myResp != null && !"no data".equals(myResp) && (myResp.startsWith("61")  || myResp.length() != 6 || (!myResp.startsWith("7f") && myResp.length() == 6)) ) {
                        update("Command: 21"+command+" response:", response);
                     }
                  }

                  handler.post(new Runnable() {
                     public void run() {
                        if (response != null && response.length > 0) {
                           String myResp = response[0]; 
                           progressDialog.setMessage("Scanning...\nCommand: 21"+command+"\nResponse:"+myResp);
                        } else {
                           progressDialog.setMessage("Scanning...\nCommand: 21"+command+"\nResponse: None");

                        }
                     }
                  });
                  
                  
                  
               }
            } catch(RemoteException e) {
               update(e.getMessage(),new String[] {"" });
               Log.e("TorqueScan",e.getMessage(), e);
            }


            i = 0x00;
            int j = 0;
            try {
               while (i < 0xff) {
                  j++;
                  
                  if (fullScan) {
                     if (j > 255) {
                        j = 0;
                        i++;
                     }
                  } else {
                     if (j > 15) {
                        j = 0;
                        i++;
                     }
                  }


                  String sc;
                  if (fullScan) {
                     sc = toHex(i)+toHex(j);
                  } else {
                     sc = toHex(i)+toHex(j*j);
                  }
                  final String command = sc;

                

                  final String[] response = torqueService.sendCommandGetResponse(header, "22"+command +"01");
                  if (response != null && response.length > 0) {
                     String myResp = response[0].toLowerCase();
                     if (myResp != null && !"no data".equals(myResp) && !myResp.startsWith("7f") ) {
                        update("Command: 22"+command+" response:",response);
                     }
                  }

                  if (scanCancelled)
                     return;



                  handler.post(new Runnable() {
                     public void run() {
                        if (response != null && response.length > 0) {
                           String myResp = response[0]; 
                           progressDialog.setMessage("Scanning...\nCommand: 22"+command+"\nResponse:"+myResp);
                        } else {
                           progressDialog.setMessage("Scanning...\nCommand: 22"+command+"\nResponse: None");
                        }
                     }
                  });

               }
            } catch(RemoteException e) {
               update(e.getMessage(),new String[] {"" });
               Log.e("TorqueScan",e.getMessage(), e);
            }


            update("Scanning finished",new String[] {"" });


            handler.post(new Runnable() {
               public void run() {
                  progressDialog.dismiss();
               }
            });
            timer.cancel();
         }
      }, 1000);
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
               final AlertDialog adialog = new AlertDialog.Builder(ScanActivity.this).create();

               adialog.setButton("OK", new OnClickListener() {

                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                     adialog.dismiss();

                  }
               });

               ScrollView svMessage = new ScrollView(ScanActivity.this);
               TextView tvMessage = new TextView(ScanActivity.this);

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