package org.prowl.torquescan;

import android.util.Log;

public class DebugConfig
{
	/** Whether or not to include logging statements in the application. */
	//public final static boolean LOGGING = ${config.logging};
   public final static boolean DEBUG = false;

   public final static boolean LICENCE_ACTIVE = false;
   public static final String VERSION = "1.6.5";
   
   public final static boolean COMMS_DEBUG_LOG = true;// log to debug logs
   public final static boolean COMMS_ECHO = false; //echo to adb
   public final static boolean TEST = false; // Dial tests
   public static final void debug(Throwable e) {
      if (DEBUG)
         Log.e(PluginActivity.class.getCanonicalName(),e.getMessage(),e);
   } 
}
