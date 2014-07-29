package org.prowl.torquescan.utils;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.prowl.torque.remote.ITorqueService;
import org.prowl.torquescan.DebugConfig;
import org.prowl.torquescan.PluginActivity;
import org.prowl.torquescan.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PIDAdapter extends BaseAdapter {

   Vector<PID> pids;

   private LayoutInflater mInflater;

   private Context context;


   private int dkRed = Color.argb(255,60,0,0);
   private int dkYellow = Color.argb(255,60,51,0);
   private int dkGreen = Color.argb(255, 0,96,0);
   private int dkGreenB = Color.argb(255, 0,50,0);



   private NumberFormat nf = NumberFormat.getInstance();
   public static final String NONE = "";

   public PIDAdapter(Context context, Vector<PID> pids) {

      this.context = context;

      // Cache the LayoutInflate to avoid asking for a new one each time.
      mInflater = LayoutInflater.from(context);

      this.pids = pids;
      nf.setMaximumFractionDigits(2);
    

   }

   public boolean contains(PID spid) {
      synchronized(pids) {
         for (PID pid: pids) {
            if (pid.getFullName().equals(spid.getFullName())) {
               return true;
            }
         }
      }
      return false;
   }



   public void addPID(PID pid, boolean checkForDuplicates) {
      if (checkForDuplicates && !pids.contains(pid)) {
         pids.add(pid);
         Collections.sort(pids, new PIDComparator());

         notifyDataSetChanged();

      }
      if (!checkForDuplicates) {
         pids.add(pid);
         Collections.sort(pids, new PIDComparator());

         notifyDataSetChanged();
      }
   }

   public Vector<PID> getPIDs() {
      return pids;
   }

   public void removePID(PID pid) {
      pids.remove(pid);
      notifyDataSetChanged();
   }

   public void refresh() {
      notifyDataSetChanged();
   }

   public void clear() {
      //hosts.clear();
      pids = new Vector();
      notifyDataSetChanged();
   }

   @Override
   public int getCount() {
      return pids.size();
   }

   @Override
   public Object getItem(int location) {
      return pids.elementAt(location);
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   Timer updateTimer = new Timer("OBD2 Browse updater");
   private Handler handler;
   public View getView(int position, View convertView, final ViewGroup parent) {
      ViewHolder holder;

      // When convertView is not null, we can reuse it directly, there is no need
      // to reinflate it. We only inflate a new View when the convertView supplied
      // by ListView is null.
      if (handler == null)
         handler = new Handler();
      if (convertView == null) {
         convertView = mInflater.inflate(R.layout.valuelayout, null);

         // Creates a ViewHolder and store references to the two children views
         // we want to bind data to.
         holder = new ViewHolder();
         holder.firstLine = (TextView) convertView.findViewById(R.id.afirstLine);
         holder.secondLine = (TextView) convertView.findViewById(R.id.asecondLine);
         holder.thirdLine = (TextView) convertView.findViewById(R.id.athirdLine);
         holder.icon = (ImageView) convertView.findViewById(R.id.aicon);
         holder.secondLine.setVisibility(TextView.GONE);
         convertView.setTag(holder);

         final View mv = convertView;
         final ViewHolder mh = holder;
         try {
            if (updateTimer == null)
               updateTimer = new Timer();
            updateTimer.schedule(new TimerTask() { public void run() {

               try {
                  if (!parent.isShown()) {
                     Log.d("Torque","Cancelling timer");
                     updateTimer.cancel();
                     updateTimer = null;
                  }

                  handler.post(new Runnable() { public void run() {updateHolder(mv, mh); }});
               } catch(Throwable e) {
                  DebugConfig.debug(e);
                  try {
                     updateTimer.cancel();
                     updateTimer = null;
                  } catch(Throwable ex) { }
               }
            }}, 700, 800);
         } catch(Throwable exc) {
            DebugConfig.debug(exc);
         }

      } else {
         // Get the ViewHolder back to get fast access to the TextView
         // and the ImageView.
         holder = (ViewHolder) convertView.getTag();

      }
      holder.position = position;
      updateHolder(convertView, holder);
      return convertView;
   }

   public void updateHolder(View convertView, ViewHolder holder) {
      try {
         int position = holder.position;
         PID pid = pids.elementAt(position);
         String pidName =  pid.getFullName();
         if (pidName == null || pidName.length() == 0) {
            pidName = "[Unnamed]";
         }

         /*String pidHex = Long.toString(pid.getPid(),16);
         if (pidHex.length() % 2 == 1) {
            pidHex="0"+pidHex;
         }*/

         String latestVal = "";

         
         ITorqueService torqueService = PluginActivity.getInstance().getTorqueService();
         
      
      //   long[] mpids = torqueService.getListOfActivePids();
      //   long[] spids = torqueService.getListOfECUSupportedPids();

         String[] mpids = torqueService.listActivePIDs();
         String[] spids = torqueService.listECUSupportedPIDs();

         
         
         float result = torqueService.getPIDValues(new String[] {pid.getPid()})[0];
         //if (pid.isUserPid()) {
         //   result = pid.getUserPidValue(true);
        // }
            
         String unit = "";
         if (pid.getUnit() != null)
            unit = pid.getUnit();

        

         int color = Color.BLACK;
         Bitmap icon = null;

         
         if (containsPID(mpids, pid.getPid())) {
            color = dkGreen;
           // if (result instanceof Number){
               latestVal = "Latest raw value: "+nf.format((Number)result)+unit;
           // } else {
           //    if (result != null) {
          //        latestVal = String.valueOf(result);
          //     } else {
          //        latestVal = "No data received";
          //     }
          //  }
         } else if (containsPID(spids, pid.getPid())) {
            color = dkGreenB;
            latestVal = "Waiting for data";
         } else {
            latestVal = "No data received";
         }
         
         String left = pid.getPid().substring(0,pid.getPid().indexOf(","));
         // Bind the data efficiently with the holder.
         holder.firstLine.setText(pidName+" ["+left+"]");
         holder.thirdLine.setText(latestVal);//"PID: "+pidHex+"  Unit:"+pid.getUnit()+" Max/Min:"+pid.getMax()+"/"+pid.getMin());
         holder.secondLine.setText("");//Equation: " +pid.getEquation());
         
         
         //if (CurrentStatistics.hasStatRAW(pid.getPid()) || (pid.isUserPid() && pid.isUserPidUpdatedRecently())) {
            // icon = notok;
         //} else if (FrontPage.getInstance().getObd().isSensorSupported(pid.getPid())) {
         //   color = dkGreenB;
         //}
         
         

         convertView.setBackgroundColor(color);





         holder.icon.setImageBitmap(null);

         /*if (pidHex.startsWith("01")) {
         holder.icon.setImageBitmap(current);
      } else if (pidHex.startsWith("02")) {
          holder.icon.setImageBitmap(freeze);
      } else if (pidHex.startsWith("05") || pidHex.startsWith("06")) {
         holder.icon.setImageBitmap(test);
      } else {
         holder.icon.setImageBitmap(defaultIcon);
      }*/

      } catch(Throwable e) {
         DebugConfig.debug(e);
         //updateTimer.cancel();
      }
   }
   
   public static final HashMap<Long, String> toHexCache = new HashMap();
   public static String toHex(long i) {
      String pidHex = toHexCache.get(i);
      if (pidHex != null)
         return pidHex;

      pidHex = Long.toString(i,16);
      if (pidHex.length() % 2 == 1) {
         pidHex="0"+pidHex;
      }
      toHexCache.put(i, pidHex);
      return pidHex;
   }

   public final boolean containsPID(final String[] pids, final String pid) {
      for (final String p: pids) {
         if (pid.equals(p))
            return true;
      }
      return false;
   }

   static class ViewHolder {
      int position;
      TextView firstLine;
      TextView secondLine;
      TextView thirdLine;
      ImageView icon;
   }




}