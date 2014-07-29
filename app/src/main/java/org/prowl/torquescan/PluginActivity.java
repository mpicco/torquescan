package org.prowl.torquescan;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.prowl.torque.remote.ITorqueService;
import org.prowl.torquescan.utils.PID;
import org.prowl.torquescan.utils.PIDAdapter;
import org.prowl.torquescan.utils.PIDComparator;

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
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is a very rough, sample plugin that displays a list of all the currently available
 * sensors in Torque (that your ECU supports).
 * 
 * Use the "Display Test Mode" in the main Torque app settings to simulate values.
 * 
 * The plugin name and icon used in Torque are that of the apk icon and name.
 * 
 * Note the intent-filter entries in the manifest - one for this code (an actual activity based plugin)
 * and one for allowing exensions of PID lists for manufacturer specific ECUs. The PID lists are a simple
 * file based resource you can throw together in a normal text-editor. 
 * 
 * The telnetactivity and scanactivity are mainly unfinished classes - feel free to ignore them
 * 
 * Have fun!
 * 
 * @author Ian Hawkins http://torque-bhp.com/
 */
public class PluginActivity extends Activity {


   private ITorqueService torqueService;

   private TextView textView;
   private Handler handler;
   private NumberFormat nf;

   private ListView list;
   private Vector<PID> pids = new Vector();
   private PIDAdapter listViewArrayAdapter;

   private Timer updateTimer;
   private static PluginActivity instance;
   private static final String SCAN = "PID Scanner";
   private static final String TELNET = "Telnet Server";

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      instance = this;
      LayoutInflater inflater = LayoutInflater.from(this);
      View view = inflater.inflate(R.layout.main, null);
      textView = (TextView)view.findViewById(R.id.textview);
      view.setKeepScreenOn(true);

      // Max of 2 digits for readings.
      nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(2);

      handler = new Handler();



   }

   public static final PluginActivity getInstance() {
      return instance;
   }

   @Override
   protected void onResume() {
      super.onResume();


      if (updateTimer != null)
         updateTimer.cancel();
      updateTimer = new Timer();
      updateTimer.schedule(new TimerTask() { public void run() { 
         refreshListItems();
      }
      },1000,1000);


      textView.setText("");

      // Bind to the torque service
      Intent intent = new Intent();
      intent.setClassName("org.prowl.torque", "org.prowl.torque.remote.TorqueService");
      boolean successfulBind = bindService(intent, connection, 0);

      if (successfulBind) {

          // Not really anything to do here.  Once you have bound to the service, you can start calling
          // methods on torqueService.someMethod()  - look at the aidl file for more info on the calls
          
      } else {
         textView.setText("Unable to connect to Torque plugin service");
      }

      tip("Press 'Menu' for more options");

   }



   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();

      if (updateTimer != null)
         updateTimer.cancel();

      unbindService(connection);
   }

    /**
     * Create the list that we are going to show to the user containing PIDS from torque
     **/
   public void setupList() {
      list = new ListView(this);

      list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
      listViewArrayAdapter = new PIDAdapter(this, (Vector)pids.clone());//new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
      list.setAdapter(listViewArrayAdapter);

      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         public void onItemClick(AdapterView<?> av, View v, int a, long b) {
            Object[] dataToAdd = null;
            PID pid = (PID) listViewArrayAdapter.getItem(a);
            if (pid != null) {

               // View in large update thingy?

            }
         }
      });

      refreshListItems();


   }

   public void refreshListItems() {
      if (listViewArrayAdapter == null)
         return;

      try {
         String[] mpids = torqueService.listAllPIDs();
         String[] pidInfo = torqueService.getPIDInformation(mpids);
         pids.clear();
         for (int i = 0; i < mpids.length; i++) {
            String[] info = pidInfo[i].split(",");
            PID newPid = new PID(mpids[i]);
            newPid.setFullName(info[0]);
            newPid.setShortName(info[1]);
            newPid.setUnit(info[2]);
            newPid.setUserPid(false);
            pids.add(newPid);

         }

      } catch(RemoteException e) {
         DebugConfig.debug(e);
      }

      Collections.sort(pids, new PIDComparator());

      handler.post(new Runnable() { public void run() {
         for (PID pid: pids) {
            listViewArrayAdapter.addPID(pid, true);//; checkForDuplicates)
         }
      }});


   }

   /**
    * Do an update of PIDs we know about (that the main app has sent us)
    */
   public void update() {
      // String used for code readability.
      String text = ""; 

      try {
         text = text + "API Version: "+torqueService.getVersion() + "\n";

         long[] pids = torqueService.getListOfActivePids();
         // Arrays.sort(pids);
         for (long pid: pids) {

            String description = torqueService.getDescriptionForPid(pid);
            // If no description, display as hex.
            if (description == null) {

               description = Long.toString(pid, 16);

                if (description.startsWith("fe18"))
                  description = "Transmission ECU["+description+"]";
               else if (description.startsWith("fe28")) {
                  description = "ABS ECU["+description+"]";
               } else if (description.startsWith("ff")) {
                  description = "Internal PID["+description+"]";
               }
            }

            
            // This calls the API and grabs data
            float value = torqueService.getValueForPid(pid, true);
            String unit = torqueService.getUnitForPid(pid);

            text = text + description+": "+nf.format(value);

            if (unit != null)
               text +=" "+unit;

            text+="\n";

         }

      } catch(RemoteException e) {
         Log.e(getClass().getCanonicalName(),e.getMessage(),e);
      }

      // Update the widget.
      final String myText = text;
      handler.post(new Runnable() { public void run() {
         textView.setText(myText);
      }});

   }



   public ITorqueService getTorqueService() {
      return torqueService;
   }


   public boolean onMenuItemSelected(int featureId, MenuItem item) {
      if (SCAN.equals(item.getTitle())) {
         startActivity(new Intent(this, ScanActivity.class));  
      } else if (TELNET.equals(item.getTitle())) { 


         AlertDialog bdialog = new AlertDialog.Builder(PluginActivity.this).create();
         bdialog.setTitle("Obligatory Warning");
         bdialog.setMessage("The telnet utility is only for people who are proficient and understand the OBD2 protocol.\n\nYou will need a desktop computer with IP access to your phone to use this activity.\n\nBy pressing 'OK' you agree to not hold the author liable any possible damage through use of the telnet utility.\n");
         bdialog.setButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface bdialog, int which) {
               startActivity(new Intent(PluginActivity.this, TelnetActivity.class));  
               bdialog.dismiss();
            }});
         bdialog.setButton2("Cancel",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface bdialog, int which) {
               bdialog.dismiss();
            }});
         bdialog.show();

      }
      return true;
   }


   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(SCAN).setIcon(android.R.drawable.ic_menu_search);
      menu.add(TELNET).setIcon(android.R.drawable.ic_menu_upload);

      return true;
   }



   public void toast(final String message, Context c) { 

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

   private static Vector<String> tipsShown = new Vector();
   public void tip(String tip) {
      if (!tipsShown.contains(tip)) {
         tipsShown.add(tip);
         toast(tip, null);
      }
   }

    
/** 
 * Quick popup message
 *
 */
   public void popupMessage(final String title, final String message, final boolean finishOnClose) {
      handler.post(new Runnable() { public void run() {
         try {
            final AlertDialog adialog = new AlertDialog.Builder(PluginActivity.this).create();

            adialog.setButton("OK", new OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {

                  adialog.dismiss();
                  if (finishOnClose) {
                     finish();
                  }

               }
            });


            ScrollView svMessage = new ScrollView(PluginActivity.this); 
            TextView tvMessage = new TextView(PluginActivity.this);

            SpannableString spanText = new SpannableString(message);

            Linkify.addLinks(spanText, Linkify.ALL);
            tvMessage.setText(spanText);
            tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16f);
            tvMessage.setMovementMethod(LinkMovementMethod.getInstance());

            svMessage.setPadding(14, 2, 10, 12);
            svMessage.addView(tvMessage);



            adialog.setTitle(title);
            adialog.setView(svMessage);

            adialog.show();
         } catch(Throwable e) {
            DebugConfig.debug(e);
         }
      }});
   }

   /**
    * Bits of service code. You usually won't need to change this.
    */
   private ServiceConnection connection = new ServiceConnection() {
      public void onServiceConnected(ComponentName arg0, IBinder service) {
         torqueService = ITorqueService.Stub.asInterface(service);

         try {
            if (torqueService.getVersion() < 19) {
               popupMessage("Incorrect version", "You are using an old version of Torque with this plugin.\n\nThe plugin needs the latest version of Torque to run correctly.\n\nPlease upgrade to the latest version of Torque from Google Play", true);
               return;
            }
         } catch(RemoteException e) { 

         }


         setupList();
         setContentView(list);


      };
      public void onServiceDisconnected(ComponentName name) {
         torqueService = null;
      };
   };
}