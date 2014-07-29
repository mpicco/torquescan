package org.prowl.torquescan.utils;

import java.io.Serializable;

public class PID implements Serializable {
   
   private String pid = null;
   private String fullName;
   private Class varType;
   private String shortName;
   private float min;
   private float max;
   private String unit;
   private float scale;
   private Float userPidValue;
   private String equation;
   private int mode;
   private boolean isUserPid = true;
   private String header = "";
   private long userPidValueUpdateTime = 0;
   private long userPidValueUpdatedTime;
   private int tries = 0;
   private boolean isEditable = true;
   private static final int GROUP_CUSTOM = 0x9000;
   
   
   
   @Override
   public boolean equals(Object o) {
      // TODO Auto-generated method stub
      return fullName.equals(((PID)o).getFullName());
   }


   @Override
   public int hashCode() {
      // TODO Auto-generated method stub
      return (pid+"|"+fullName+"|"+varType+"|"+shortName+"|"+min+"|"+max+"|"+unit+"|"+scale+"|"+equation+"|"+header).hashCode();
   }


   @Override
   public String toString() {
      // TODO Auto-generated method stub
      return fullName;
   }


   public PID(String pid) {
      this.pid = pid;
   }


   public String getPid() {
      return pid;
   }


   public void setPid(String pid) {
      this.pid = pid;
   }


   public String getFullName() {
      return fullName;
   }


   public void setFullName(String fullName) {
      this.fullName = fullName;
   }


   public Class getVarType() {
      return varType;
   }


   public void setVarType(Class varType) {
      this.varType = varType;
   }


   public String getShortName() {
      return shortName;
   }


   public void setShortName(String shortName) {
      this.shortName = shortName;
   }


   public float getMin() {
      return min;
   }


   public void setMin(float min) {
      this.min = min;
   }


   public float getMax() {
      return max;
   }


   public void setMax(float max) {
      this.max = max;
   }


   public String getUnit() {
      return unit;
   }


   public void setUnit(String unit) {
      this.unit = unit;
   }


   public float getScale() {
      return scale;
   }


   public void setScale(float scale) {
      this.scale = scale;
   }


   public String getEquation() {
      return equation;
   }


   public void setEquation(String equation) {
      this.equation = equation;
   }


   public int getMode() {
      return mode;
   }


   public void setMode(int mode) {
      this.mode = mode;
   }


   public boolean isUserPid() {
      return isUserPid;
   }


   public void setUserPid(boolean isUserPid) {
      this.isUserPid = isUserPid;
   }


   public String getHeader() {
      return header;
   }

   

   public boolean isEditable() {
      return isEditable;
   }


   public void setEditable(boolean editable) {
      this.isEditable = editable;
   }


   public void setHeader(String header) {
      this.header = header;
   }

   public Float getUserPidValue(boolean triggersRequest) {
      if (triggersRequest) {
         userPidValueUpdateTime = System.currentTimeMillis();
      }
      return userPidValue;
   }

   public boolean isUserPidRequestRequired() {
      if (userPidValueUpdateTime+3000 > System.currentTimeMillis())
         return true;
      return false;
   }


   public boolean isUserPidUpdatedRecently() {
      if (userPidValueUpdatedTime+5000 > System.currentTimeMillis())
         return true;
      return false;
   }

   
   public long getValueLastUpdatedTime() {
      return userPidValueUpdatedTime;
   }
   
   public void setUserPidValue(Float userPidValue) {
      this.userPidValue = userPidValue;
      this.userPidValueUpdatedTime = System.currentTimeMillis();
   }
   
   public void incrementTries() {
      
   }
   
   public void reset() {
      tries = 0;
      userPidValue = null;
      userPidValueUpdatedTime = 0;
   }

   public boolean isUserPidSupported() {
      
      if (tries < 3) {
         return true;
      }
      
      return getUserPidValue(false) != null;
   }
   
   
}