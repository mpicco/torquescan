package org.prowl.torquescan.utils;
import java.util.Comparator;


   public class PIDComparator implements Comparator {

      @Override
      public int compare(Object object1, Object object2) {
         PID p1 = (PID)object1;
         PID p2 = (PID)object2;

         String n1 = "";
         String n2 = "";
         if (p1 != null && p1.getFullName() != null)
            n1 = p1.getFullName();
         if (p2 != null && p2.getFullName() != null)
            n2 = p2.getFullName();     
         
         
            return n1.compareToIgnoreCase(n2);
        
      } 



   }