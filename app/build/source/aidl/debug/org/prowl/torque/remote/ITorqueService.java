/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/mpicco/Development/Glass/torquescan/app/src/main/aidl/org/prowl/torque/remote/ITorqueService.aidl
 */
package org.prowl.torque.remote;
public interface ITorqueService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.prowl.torque.remote.ITorqueService
{
private static final java.lang.String DESCRIPTOR = "org.prowl.torque.remote.ITorqueService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.prowl.torque.remote.ITorqueService interface,
 * generating a proxy if needed.
 */
public static org.prowl.torque.remote.ITorqueService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.prowl.torque.remote.ITorqueService))) {
return ((org.prowl.torque.remote.ITorqueService)iin);
}
return new org.prowl.torque.remote.ITorqueService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getVersion:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getVersion();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getValueForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
boolean _arg1;
_arg1 = (0!=data.readInt());
float _result = this.getValueForPid(_arg0, _arg1);
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_getDescriptionForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.lang.String _result = this.getDescriptionForPid(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getShortNameForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.lang.String _result = this.getShortNameForPid(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getUnitForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.lang.String _result = this.getUnitForPid(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getMinValueForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
float _result = this.getMinValueForPid(_arg0);
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_getMaxValueForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
float _result = this.getMaxValueForPid(_arg0);
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_getListOfActivePids:
{
data.enforceInterface(DESCRIPTOR);
long[] _result = this.getListOfActivePids();
reply.writeNoException();
reply.writeLongArray(_result);
return true;
}
case TRANSACTION_getListOfECUSupportedPids:
{
data.enforceInterface(DESCRIPTOR);
long[] _result = this.getListOfECUSupportedPids();
reply.writeNoException();
reply.writeLongArray(_result);
return true;
}
case TRANSACTION_getListOfAllPids:
{
data.enforceInterface(DESCRIPTOR);
long[] _result = this.getListOfAllPids();
reply.writeNoException();
reply.writeLongArray(_result);
return true;
}
case TRANSACTION_hasFullPermissions:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.hasFullPermissions();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_sendCommandGetResponse:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String[] _result = this.sendCommandGetResponse(_arg0, _arg1);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getPreferredUnit:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.getPreferredUnit(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_setPIDData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
float _arg3;
_arg3 = data.readFloat();
float _arg4;
_arg4 = data.readFloat();
float _arg5;
_arg5 = data.readFloat();
boolean _result = this.setPIDData(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isConnectedToECU:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isConnectedToECU();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setDebugTestMode:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
boolean _result = this.setDebugTestMode(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getVehicleProfileInformation:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.getVehicleProfileInformation();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_storeInProfile:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _arg2;
_arg2 = (0!=data.readInt());
int _result = this.storeInProfile(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_retrieveProfileData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.retrieveProfileData(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getDataErrorCount:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getDataErrorCount();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getPIDReadSpeed:
{
data.enforceInterface(DESCRIPTOR);
double _result = this.getPIDReadSpeed();
reply.writeNoException();
reply.writeDouble(_result);
return true;
}
case TRANSACTION_getConfiguredSpeed:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getConfiguredSpeed();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_isFileLoggingEnabled:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isFileLoggingEnabled();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isWebLoggingEnabled:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isWebLoggingEnabled();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getNumberOfLoggedItems:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getNumberOfLoggedItems();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getUpdateTimeForPID:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
long _result = this.getUpdateTimeForPID(_arg0);
reply.writeNoException();
reply.writeLong(_result);
return true;
}
case TRANSACTION_getScaleForPid:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
float _result = this.getScaleForPid(_arg0);
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_translate:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.translate(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_sendPIDData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
_arg1 = data.createStringArray();
java.lang.String[] _arg2;
_arg2 = data.createStringArray();
java.lang.String[] _arg3;
_arg3 = data.createStringArray();
java.lang.String[] _arg4;
_arg4 = data.createStringArray();
float[] _arg5;
_arg5 = data.createFloatArray();
float[] _arg6;
_arg6 = data.createFloatArray();
java.lang.String[] _arg7;
_arg7 = data.createStringArray();
java.lang.String[] _arg8;
_arg8 = data.createStringArray();
boolean _result = this.sendPIDData(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setPIDInformation:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
float _arg3;
_arg3 = data.readFloat();
float _arg4;
_arg4 = data.readFloat();
float _arg5;
_arg5 = data.readFloat();
java.lang.String _arg6;
_arg6 = data.readString();
boolean _result = this.setPIDInformation(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_listAllPIDs:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.listAllPIDs();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_listActivePIDs:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.listActivePIDs();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_listECUSupportedPIDs:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.listECUSupportedPIDs();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getPIDValues:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _arg0;
_arg0 = data.createStringArray();
float[] _result = this.getPIDValues(_arg0);
reply.writeNoException();
reply.writeFloatArray(_result);
return true;
}
case TRANSACTION_getPIDInformation:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _arg0;
_arg0 = data.createStringArray();
java.lang.String[] _result = this.getPIDInformation(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getPIDUpdateTime:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _arg0;
_arg0 = data.createStringArray();
long[] _result = this.getPIDUpdateTime(_arg0);
reply.writeNoException();
reply.writeLongArray(_result);
return true;
}
case TRANSACTION_getValueForPids:
{
data.enforceInterface(DESCRIPTOR);
long[] _arg0;
_arg0 = data.createLongArray();
float[] _result = this.getValueForPids(_arg0);
reply.writeNoException();
reply.writeFloatArray(_result);
return true;
}
case TRANSACTION_sendPIDDataPrivate:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
_arg1 = data.createStringArray();
java.lang.String[] _arg2;
_arg2 = data.createStringArray();
java.lang.String[] _arg3;
_arg3 = data.createStringArray();
java.lang.String[] _arg4;
_arg4 = data.createStringArray();
float[] _arg5;
_arg5 = data.createFloatArray();
float[] _arg6;
_arg6 = data.createFloatArray();
java.lang.String[] _arg7;
_arg7 = data.createStringArray();
java.lang.String[] _arg8;
_arg8 = data.createStringArray();
boolean _result = this.sendPIDDataPrivate(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_requestExclusiveLock:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.requestExclusiveLock(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_releaseExclusiveLock:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _arg1;
_arg1 = (0!=data.readInt());
boolean _result = this.releaseExclusiveLock(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_sendPIDDataV2:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
_arg1 = data.createStringArray();
java.lang.String[] _arg2;
_arg2 = data.createStringArray();
java.lang.String[] _arg3;
_arg3 = data.createStringArray();
java.lang.String[] _arg4;
_arg4 = data.createStringArray();
float[] _arg5;
_arg5 = data.createFloatArray();
float[] _arg6;
_arg6 = data.createFloatArray();
java.lang.String[] _arg7;
_arg7 = data.createStringArray();
java.lang.String[] _arg8;
_arg8 = data.createStringArray();
java.lang.String[] _arg9;
_arg9 = data.createStringArray();
java.lang.String[] _arg10;
_arg10 = data.createStringArray();
boolean _result = this.sendPIDDataV2(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_sendPIDDataPrivateV2:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
_arg1 = data.createStringArray();
java.lang.String[] _arg2;
_arg2 = data.createStringArray();
java.lang.String[] _arg3;
_arg3 = data.createStringArray();
java.lang.String[] _arg4;
_arg4 = data.createStringArray();
float[] _arg5;
_arg5 = data.createFloatArray();
float[] _arg6;
_arg6 = data.createFloatArray();
java.lang.String[] _arg7;
_arg7 = data.createStringArray();
java.lang.String[] _arg8;
_arg8 = data.createStringArray();
java.lang.String[] _arg9;
_arg9 = data.createStringArray();
java.lang.String[] _arg10;
_arg10 = data.createStringArray();
boolean _result = this.sendPIDDataPrivateV2(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getPIDRawResponse:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _result = this.getPIDRawResponse(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getProtocolNumber:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getProtocolNumber();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getProtocolName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getProtocolName();
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.prowl.torque.remote.ITorqueService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
    * Get the API version. The version will increment each time the API is revised.
    */
@Override public int getVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the most recent value stored for the given PID.  This will return immediately whether or not data exists.
    * @param triggersDataRefresh Cause the data to be re-requested from the ECU
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public float getValueForPid(long pid, boolean triggersDataRefresh) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
_data.writeInt(((triggersDataRefresh)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_getValueForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get a textual, long description regarding the PID, already translated (when translation is implemented)
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public java.lang.String getDescriptionForPid(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getDescriptionForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the shortname of the PID
    * @note If the PID returns multiple values, then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public java.lang.String getShortNameForPid(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getShortNameForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the Si unit in string form for the PID, if no Si unit is available, a textual-description is returned instead.
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public java.lang.String getUnitForPid(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getUnitForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the minimum value expected for this PID
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public float getMinValueForPid(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getMinValueForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the maximum value expected for this PId
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public float getMaxValueForPid(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getMaxValueForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Returns a list of currently 'active' PIDs. This list will change often. Try not to call this method too frequently.
    *
    * PIDs are stored as hex values, so '0x01, 0x0D' (vehicle speed) is the equivalent:  Integer.parseInt("010D",16);
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public long[] getListOfActivePids() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getListOfActivePids, _data, _reply, 0);
_reply.readException();
_result = _reply.createLongArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Returns a list of PIDs that have been reported by the ECU as supported.
    *
    * PIDs are stored as hex values, so '0x01, 0x0D' (vehicle speed) is the equivalent:  Integer.parseInt("010D",16);
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public long[] getListOfECUSupportedPids() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getListOfECUSupportedPids, _data, _reply, 0);
_reply.readException();
_result = _reply.createLongArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Returns a list of all the known available sensors, active or inactive.
    * 
    * Try not to call this method too frequently.
    *
    * PIDs are stored as hex values, so '0x01, 0x0D' (vehicle speed) is the equivalent:  Integer.parseInt("010D",16);
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), getPIDValues(...)  and listAllPIDs() which replaces this call and can handle sensors with the same 'PID'
    */
@Override public long[] getListOfAllPids() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getListOfAllPids, _data, _reply, 0);
_reply.readException();
_result = _reply.createLongArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * True if the user has granted full permissions to the plugin (to send anything that it wants)
    */
@Override public boolean hasFullPermissions() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_hasFullPermissions, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Send a specific request over the OBD bus and return the response as a string
    *
    * This is currently limited by the 'Allow full permissions' option in the settings.
    */
@Override public java.lang.String[] sendCommandGetResponse(java.lang.String header, java.lang.String command) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(header);
_data.writeString(command);
mRemote.transact(Stub.TRANSACTION_sendCommandGetResponse, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Given this unit, get the users preferred units
   */
@Override public java.lang.String getPreferredUnit(java.lang.String unit) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(unit);
mRemote.transact(Stub.TRANSACTION_getPreferredUnit, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Add / Update a PID from your plugin to the main app, Your PID is keyed by name.
    *
    * @deprecated - (see below)superceded by setPIDInformation(String name, String shortName, String unit, float max, float min, float value, String stringValue);
    */
@Override public boolean setPIDData(java.lang.String name, java.lang.String shortName, java.lang.String unit, float max, float min, float value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeString(shortName);
_data.writeString(unit);
_data.writeFloat(max);
_data.writeFloat(min);
_data.writeFloat(value);
mRemote.transact(Stub.TRANSACTION_setPIDData, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get connection state to ECU
    * @return true if connected to the ECU, false if the app has not yet started retrieving data from the ECU 
    */
@Override public boolean isConnectedToECU() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isConnectedToECU, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Turn on or off test mode. This is for debugging only and simulates readings to some of the sensors.
    */
@Override public boolean setDebugTestMode(boolean activateTestMode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((activateTestMode)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setDebugTestMode, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Returns a string array containing the vehicle profile information:
    *
    *  Array:
    *     [0] Profile name
    *     [1] Engine Displacement (L)
    *     [2] Weight in kilogrammes
    *     [3] Fuel type (0 = Petrol, 1 = Diesel, 2 = E85 (Ethanol/Petrol)    *     [4] Boost adjustment
    *     [5] Max RPM setting
    *     [6] Volumetric efficiency (%)
    *     [7] Accumulated distance travelled
    */
@Override public java.lang.String[] getVehicleProfileInformation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVehicleProfileInformation, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Store some information into the vehicle profile. 
    *
    * @param key  Prefix the 'key' with your apps classpath (to avoid conflicts). eg: "com.company.pluginname.SOME_KEY_NAME" is nice and clear  (Don't use any characters other than A-Za-z0-9 . and _)
    * @param The value to store.
    * @param saveToFile Set this to true (on your last 'set') to commit the information to disk.
    * @return 0 if successful.
    */
@Override public int storeInProfile(java.lang.String key, java.lang.String value, boolean saveToFileNow) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeString(value);
_data.writeInt(((saveToFileNow)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_storeInProfile, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Retrieve some information from the vehicle profile.
    *
    * @param Prefix the 'key' with your apps classpath (to avoid conflicts). 
    */
@Override public java.lang.String retrieveProfileData(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_retrieveProfileData, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Retrieve the number of errors that Torque has seen happen at the adapter
     *
     * Generally speaking, if this is above 0, then you have problems at the adapter side.
     */
@Override public int getDataErrorCount() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDataErrorCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Get max PID read speed in PIDs read per second
     *
     * This is reset each connection to the adpater.
     */
@Override public double getPIDReadSpeed() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
double _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPIDReadSpeed, _data, _reply, 0);
_reply.readException();
_result = _reply.readDouble();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Gets the configured communications speed
     *
     *  1 == slowest (for problematic adapters)
     *  2 == standard (The app default)
     *  3 == fast mode (selectable in the OBD2 adapter settings)
     * 
     * Fast mode provides a significant speed increase on some vehicles for the read speed of sensor information
     */
@Override public int getConfiguredSpeed() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getConfiguredSpeed, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * returns true if the user is currently logging data to the file
     */
@Override public boolean isFileLoggingEnabled() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isFileLoggingEnabled, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * returns true if the user is currently logging data to web
     */
@Override public boolean isWebLoggingEnabled() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isWebLoggingEnabled, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * returns the number of items that are configured to be logged to file. The more items configured to log (and with logging enabled), the slower the refresh rate of individual PIDs.
     */
@Override public int getNumberOfLoggedItems() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getNumberOfLoggedItems, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the time that the PID was last updated.
    * 
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID    
    */
@Override public long getUpdateTimeForPID(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getUpdateTimeForPID, _data, _reply, 0);
_reply.readException();
_result = _reply.readLong();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Returns the scale of the requested PID
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID      
    */
@Override public float getScaleForPid(long pid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pid);
mRemote.transact(Stub.TRANSACTION_getScaleForPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Translate a string that *might* be in translation file in the main Torque app. Do not call this method repeatedly for the same text!
    */
@Override public java.lang.String translate(java.lang.String originalText) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(originalText);
mRemote.transact(Stub.TRANSACTION_translate, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Method to send PID data in raw form. 
    *  Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header
    * @deprecated - see sendPIDDataV2 (which allows diagnostic headers to be set, or left as null)   
    */
@Override public boolean sendPIDData(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pluginName);
_data.writeStringArray(name);
_data.writeStringArray(shortName);
_data.writeStringArray(modeAndPID);
_data.writeStringArray(equation);
_data.writeFloatArray(minValue);
_data.writeFloatArray(maxValue);
_data.writeStringArray(units);
_data.writeStringArray(header);
mRemote.transact(Stub.TRANSACTION_sendPIDData, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Add / Update a PID from your plugin to the main app, Your PID is keyed by name.  This can be used to set a value on a PID in Torque.
    *
    * @param value       The value to be shown in the display 
    * @param stringValue A string value to be shown in the display - overrides 'value' when not null - note(try not to use numeric values here as they won't be unit converted for you).  Set to null to revert to using the 'float value'
    */
@Override public boolean setPIDInformation(java.lang.String name, java.lang.String shortName, java.lang.String unit, float max, float min, float value, java.lang.String stringValue) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeString(shortName);
_data.writeString(unit);
_data.writeFloat(max);
_data.writeFloat(min);
_data.writeFloat(value);
_data.writeString(stringValue);
mRemote.transact(Stub.TRANSACTION_setPIDInformation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get a list of all PIDs in torque, active or inactive. 
    *
    * The returned string is the ID of the PID to request over the aidl script.  To be helpful the first part of this string is the actual PID 'id'.
    * @note (don't call this too often as it is computationally expensive!)    
    */
@Override public java.lang.String[] listAllPIDs() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_listAllPIDs, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * List all only the active PIDs
    *
    * The returned string is the ID of the PID to request over the aidl script.  To be helpful the first part of this string is the actual PID 'id'. 
    * @note (don't call this too often as it is computationally expensive!)  
    */
@Override public java.lang.String[] listActivePIDs() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_listActivePIDs, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * List all only the active PIDs
    *
    * The returned string is the ID of the PID to request over the aidl script.  To be helpful the first part of this string is the actual PID 'id'. 
    * @note (don't call this too often as it is computationally expensive!)  
    */
@Override public java.lang.String[] listECUSupportedPIDs() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_listECUSupportedPIDs, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Return the current value only for the PID (or PIDs if you pass more than one)
     *
     * Use this method to frequently get the value of PID(s).  Be aware that the more PIDs you request, the slower the update speed (as the OBD2 adapter will require to update more PIDs and this is the choke-point)
     * This method is asynchronous updates via the adapter
     */
@Override public float[] getPIDValues(java.lang.String[] pidsToRetrieve) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringArray(pidsToRetrieve);
mRemote.transact(Stub.TRANSACTION_getPIDValues, _data, _reply, 0);
_reply.readException();
_result = _reply.createFloatArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Return all the PID information in a single transaction. This is the new preferred method to get data from PIDs
    *
    * The unit returned is the raw unit (used for getPIDValues) - you can get the users preferred unit by using the relevant API call 
    *
    * Format of returned string array:
    *  String[] {
    *       String "pid information in csv format"
    *       String "pid information in csv format"
    *       ...(etc)
    *    }
    *  
    *  Example:
    *
    *   String[] {
    *      "<longName>,<shortName>,<unit>,<maxValue>,<minValue>,<scale>",
    *      "Intake Manifold Pressure,Intake,kPa,255,0,1",
    *      "Accelerator Pedal Position E,Accel(E),%,100,0,1"
    *   }
    */
@Override public java.lang.String[] getPIDInformation(java.lang.String[] pidIDs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringArray(pidIDs);
mRemote.transact(Stub.TRANSACTION_getPIDInformation, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Get the time that the PID was last updated.
    *
    * @returns time when the PID value was retrieved via OBD/etc in milliseconds
    */
@Override public long[] getPIDUpdateTime(java.lang.String[] pidIDs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringArray(pidIDs);
mRemote.transact(Stub.TRANSACTION_getPIDUpdateTime, _data, _reply, 0);
_reply.readException();
_result = _reply.createLongArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Retrieves a list of PID values
    * 
    * @deprecated See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
@Override public float[] getValueForPids(long[] pids) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLongArray(pids);
mRemote.transact(Stub.TRANSACTION_getValueForPids, _data, _reply, 0);
_reply.readException();
_result = _reply.createFloatArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Method to send PID data in raw form - this is for when pid plugin vendors want to protect their hard work when they have decyphed or licensed PIDs
    * Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header
    * @deprecated - see sendPIDDataPrivateV2 (which allows diagnostic headers to be set, or left as null)
    */
@Override public boolean sendPIDDataPrivate(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pluginName);
_data.writeStringArray(name);
_data.writeStringArray(shortName);
_data.writeStringArray(modeAndPID);
_data.writeStringArray(equation);
_data.writeFloatArray(minValue);
_data.writeFloatArray(maxValue);
_data.writeStringArray(units);
_data.writeStringArray(header);
mRemote.transact(Stub.TRANSACTION_sendPIDDataPrivate, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Cause Torque to stop communicating with the adpter so external plugins can take full control
    *
    * This is when you want to do special things like reconfigure the adpater to talk to special units (ABS, SRS, Body control, etc) 
    * which may require torque to stay away from asking information from the adpater
    *
    * @return an int depicting the state of the lock
    * 
    *   0 = Lock gained OK - you now have exclusive access
    *  -1 = Lock failed due to unknown reason
    *  -2 = Lock failed due to another lock already being present (from your, or another plugin)
    *  -3 = Lock failed due to not being connected to adapter
    *  -4 = reserved
    *  -5 = No permissions to access in this manner (enable the ticky-box in the plugin settings for 'full access')
    *  -6 = Lock failed due to timeout (10 second limit hit)
    */
@Override public int requestExclusiveLock(java.lang.String pluginName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pluginName);
mRemote.transact(Stub.TRANSACTION_requestExclusiveLock, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Release the exclusive lock the plugin has with the adapter.
    *
    * @param torqueMustReInitializeTheAdapter set this to TRUE if torque must reinit comms with the vehicle ECU to continue communicating - if it is set to FALSE, then torque will simply continue trying to talk to the ECU. Take special care to ensure the adpater is in the proper state to do this
    *
    */
@Override public boolean releaseExclusiveLock(java.lang.String pluginName, boolean torqueMustReInitializeTheAdapter) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pluginName);
_data.writeInt(((torqueMustReInitializeTheAdapter)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_releaseExclusiveLock, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Method to send PID data in raw form. 
    * This updated method allows diagnostic headers to be set if required (for special commands) or left as null. 
    *  Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header,Start diagnostic command, Stop diagnostic command
    */
@Override public boolean sendPIDDataV2(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header, java.lang.String[] startDiagnostic, java.lang.String[] stopDiagnostic) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pluginName);
_data.writeStringArray(name);
_data.writeStringArray(shortName);
_data.writeStringArray(modeAndPID);
_data.writeStringArray(equation);
_data.writeFloatArray(minValue);
_data.writeFloatArray(maxValue);
_data.writeStringArray(units);
_data.writeStringArray(header);
_data.writeStringArray(startDiagnostic);
_data.writeStringArray(stopDiagnostic);
mRemote.transact(Stub.TRANSACTION_sendPIDDataV2, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * Method to send PID data in raw form - this is for when pid plugin vendors want to protect their hard work when they have decyphed or licensed PIDs
    * This updated method allows diagnostic headers to be set if required (for special commands) or left as null. 
    * Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header, Start diagnostic command, Stop diagnostic command
    */
@Override public boolean sendPIDDataPrivateV2(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header, java.lang.String[] startDiagnostic, java.lang.String[] stopDiagnostic) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pluginName);
_data.writeStringArray(name);
_data.writeStringArray(shortName);
_data.writeStringArray(modeAndPID);
_data.writeStringArray(equation);
_data.writeFloatArray(minValue);
_data.writeFloatArray(maxValue);
_data.writeStringArray(units);
_data.writeStringArray(header);
_data.writeStringArray(startDiagnostic);
_data.writeStringArray(stopDiagnostic);
mRemote.transact(Stub.TRANSACTION_sendPIDDataPrivateV2, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    * This retrieves the last PID that torque read from the adapter in RAW form (as the adapter sent it) 'NOT READY' may be returned 
    * if the adapter has not yet retrieved that PID. The OBDCommand should be as sent to the adpater (no spaces - eg: '010D')
    *
    * Despite the name, this cannot be used to send commands directly to the adapter, use the sendCommandGetResponse(...) method if
    * you require direct access, with the exclusive lock command if you will be sending several commands or changing the adapter mode
    * 
    * This method is currently subject to change (21 Feb 2014)
    */
@Override public java.lang.String[] getPIDRawResponse(java.lang.String OBDCommand) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(OBDCommand);
mRemote.transact(Stub.TRANSACTION_getPIDRawResponse, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Get the protocol being used (or 0 if not currently connected) - the protocol number is as per the ELM327 documentation
     */
@Override public int getProtocolNumber() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getProtocolNumber, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Get the protocol name being used (or AUTO if not currently connected) 
     */
@Override public java.lang.String getProtocolName() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getProtocolName, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getValueForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getDescriptionForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getShortNameForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getUnitForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getMinValueForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getMaxValueForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getListOfActivePids = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getListOfECUSupportedPids = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getListOfAllPids = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_hasFullPermissions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_sendCommandGetResponse = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getPreferredUnit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_setPIDData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_isConnectedToECU = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_setDebugTestMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_getVehicleProfileInformation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_storeInProfile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_retrieveProfileData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_getDataErrorCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_getPIDReadSpeed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_getConfiguredSpeed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_isFileLoggingEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_isWebLoggingEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_getNumberOfLoggedItems = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_getUpdateTimeForPID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_getScaleForPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_translate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_sendPIDData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_setPIDInformation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_listAllPIDs = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_listActivePIDs = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_listECUSupportedPIDs = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_getPIDValues = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_getPIDInformation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
static final int TRANSACTION_getPIDUpdateTime = (android.os.IBinder.FIRST_CALL_TRANSACTION + 35);
static final int TRANSACTION_getValueForPids = (android.os.IBinder.FIRST_CALL_TRANSACTION + 36);
static final int TRANSACTION_sendPIDDataPrivate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 37);
static final int TRANSACTION_requestExclusiveLock = (android.os.IBinder.FIRST_CALL_TRANSACTION + 38);
static final int TRANSACTION_releaseExclusiveLock = (android.os.IBinder.FIRST_CALL_TRANSACTION + 39);
static final int TRANSACTION_sendPIDDataV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 40);
static final int TRANSACTION_sendPIDDataPrivateV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 41);
static final int TRANSACTION_getPIDRawResponse = (android.os.IBinder.FIRST_CALL_TRANSACTION + 42);
static final int TRANSACTION_getProtocolNumber = (android.os.IBinder.FIRST_CALL_TRANSACTION + 43);
static final int TRANSACTION_getProtocolName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 44);
}
/**
    * Get the API version. The version will increment each time the API is revised.
    */
public int getVersion() throws android.os.RemoteException;
/**
    * Get the most recent value stored for the given PID.  This will return immediately whether or not data exists.
    * @param triggersDataRefresh Cause the data to be re-requested from the ECU
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public float getValueForPid(long pid, boolean triggersDataRefresh) throws android.os.RemoteException;
/**
    * Get a textual, long description regarding the PID, already translated (when translation is implemented)
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public java.lang.String getDescriptionForPid(long pid) throws android.os.RemoteException;
/**
    * Get the shortname of the PID
    * @note If the PID returns multiple values, then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public java.lang.String getShortNameForPid(long pid) throws android.os.RemoteException;
/**
    * Get the Si unit in string form for the PID, if no Si unit is available, a textual-description is returned instead.
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public java.lang.String getUnitForPid(long pid) throws android.os.RemoteException;
/**
    * Get the minimum value expected for this PID
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public float getMinValueForPid(long pid) throws android.os.RemoteException;
/**
    * Get the maximum value expected for this PId
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public float getMaxValueForPid(long pid) throws android.os.RemoteException;
/**
    * Returns a list of currently 'active' PIDs. This list will change often. Try not to call this method too frequently.
    *
    * PIDs are stored as hex values, so '0x01, 0x0D' (vehicle speed) is the equivalent:  Integer.parseInt("010D",16);
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public long[] getListOfActivePids() throws android.os.RemoteException;
/**
    * Returns a list of PIDs that have been reported by the ECU as supported.
    *
    * PIDs are stored as hex values, so '0x01, 0x0D' (vehicle speed) is the equivalent:  Integer.parseInt("010D",16);
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public long[] getListOfECUSupportedPids() throws android.os.RemoteException;
/**
    * Returns a list of all the known available sensors, active or inactive.
    * 
    * Try not to call this method too frequently.
    *
    * PIDs are stored as hex values, so '0x01, 0x0D' (vehicle speed) is the equivalent:  Integer.parseInt("010D",16);
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID
    * @deprecated  See getPIDInformation(...), getPIDValues(...)  and listAllPIDs() which replaces this call and can handle sensors with the same 'PID'
    */
public long[] getListOfAllPids() throws android.os.RemoteException;
/**
    * True if the user has granted full permissions to the plugin (to send anything that it wants)
    */
public boolean hasFullPermissions() throws android.os.RemoteException;
/**
    * Send a specific request over the OBD bus and return the response as a string
    *
    * This is currently limited by the 'Allow full permissions' option in the settings.
    */
public java.lang.String[] sendCommandGetResponse(java.lang.String header, java.lang.String command) throws android.os.RemoteException;
/**
   * Given this unit, get the users preferred units
   */
public java.lang.String getPreferredUnit(java.lang.String unit) throws android.os.RemoteException;
/**
    * Add / Update a PID from your plugin to the main app, Your PID is keyed by name.
    *
    * @deprecated - (see below)superceded by setPIDInformation(String name, String shortName, String unit, float max, float min, float value, String stringValue);
    */
public boolean setPIDData(java.lang.String name, java.lang.String shortName, java.lang.String unit, float max, float min, float value) throws android.os.RemoteException;
/**
    * Get connection state to ECU
    * @return true if connected to the ECU, false if the app has not yet started retrieving data from the ECU 
    */
public boolean isConnectedToECU() throws android.os.RemoteException;
/**
    * Turn on or off test mode. This is for debugging only and simulates readings to some of the sensors.
    */
public boolean setDebugTestMode(boolean activateTestMode) throws android.os.RemoteException;
/**
    * Returns a string array containing the vehicle profile information:
    *
    *  Array:
    *     [0] Profile name
    *     [1] Engine Displacement (L)
    *     [2] Weight in kilogrammes
    *     [3] Fuel type (0 = Petrol, 1 = Diesel, 2 = E85 (Ethanol/Petrol)    *     [4] Boost adjustment
    *     [5] Max RPM setting
    *     [6] Volumetric efficiency (%)
    *     [7] Accumulated distance travelled
    */
public java.lang.String[] getVehicleProfileInformation() throws android.os.RemoteException;
/**
    * Store some information into the vehicle profile. 
    *
    * @param key  Prefix the 'key' with your apps classpath (to avoid conflicts). eg: "com.company.pluginname.SOME_KEY_NAME" is nice and clear  (Don't use any characters other than A-Za-z0-9 . and _)
    * @param The value to store.
    * @param saveToFile Set this to true (on your last 'set') to commit the information to disk.
    * @return 0 if successful.
    */
public int storeInProfile(java.lang.String key, java.lang.String value, boolean saveToFileNow) throws android.os.RemoteException;
/**
    * Retrieve some information from the vehicle profile.
    *
    * @param Prefix the 'key' with your apps classpath (to avoid conflicts). 
    */
public java.lang.String retrieveProfileData(java.lang.String key) throws android.os.RemoteException;
/**
     * Retrieve the number of errors that Torque has seen happen at the adapter
     *
     * Generally speaking, if this is above 0, then you have problems at the adapter side.
     */
public int getDataErrorCount() throws android.os.RemoteException;
/**
     * Get max PID read speed in PIDs read per second
     *
     * This is reset each connection to the adpater.
     */
public double getPIDReadSpeed() throws android.os.RemoteException;
/**
     * Gets the configured communications speed
     *
     *  1 == slowest (for problematic adapters)
     *  2 == standard (The app default)
     *  3 == fast mode (selectable in the OBD2 adapter settings)
     * 
     * Fast mode provides a significant speed increase on some vehicles for the read speed of sensor information
     */
public int getConfiguredSpeed() throws android.os.RemoteException;
/**
     * returns true if the user is currently logging data to the file
     */
public boolean isFileLoggingEnabled() throws android.os.RemoteException;
/**
     * returns true if the user is currently logging data to web
     */
public boolean isWebLoggingEnabled() throws android.os.RemoteException;
/**
     * returns the number of items that are configured to be logged to file. The more items configured to log (and with logging enabled), the slower the refresh rate of individual PIDs.
     */
public int getNumberOfLoggedItems() throws android.os.RemoteException;
/**
    * Get the time that the PID was last updated.
    * 
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID    
    */
public long getUpdateTimeForPID(long pid) throws android.os.RemoteException;
/**
    * Returns the scale of the requested PID
    * @note If the PID returns multiple values(has multiple displays setup for the same PID), then this call will return the data for the first matching PID      
    */
public float getScaleForPid(long pid) throws android.os.RemoteException;
/**
    * Translate a string that *might* be in translation file in the main Torque app. Do not call this method repeatedly for the same text!
    */
public java.lang.String translate(java.lang.String originalText) throws android.os.RemoteException;
/**
    * Method to send PID data in raw form. 
    *  Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header
    * @deprecated - see sendPIDDataV2 (which allows diagnostic headers to be set, or left as null)   
    */
public boolean sendPIDData(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header) throws android.os.RemoteException;
/**
    * Add / Update a PID from your plugin to the main app, Your PID is keyed by name.  This can be used to set a value on a PID in Torque.
    *
    * @param value       The value to be shown in the display 
    * @param stringValue A string value to be shown in the display - overrides 'value' when not null - note(try not to use numeric values here as they won't be unit converted for you).  Set to null to revert to using the 'float value'
    */
public boolean setPIDInformation(java.lang.String name, java.lang.String shortName, java.lang.String unit, float max, float min, float value, java.lang.String stringValue) throws android.os.RemoteException;
/**
    * Get a list of all PIDs in torque, active or inactive. 
    *
    * The returned string is the ID of the PID to request over the aidl script.  To be helpful the first part of this string is the actual PID 'id'.
    * @note (don't call this too often as it is computationally expensive!)    
    */
public java.lang.String[] listAllPIDs() throws android.os.RemoteException;
/**
    * List all only the active PIDs
    *
    * The returned string is the ID of the PID to request over the aidl script.  To be helpful the first part of this string is the actual PID 'id'. 
    * @note (don't call this too often as it is computationally expensive!)  
    */
public java.lang.String[] listActivePIDs() throws android.os.RemoteException;
/**
    * List all only the active PIDs
    *
    * The returned string is the ID of the PID to request over the aidl script.  To be helpful the first part of this string is the actual PID 'id'. 
    * @note (don't call this too often as it is computationally expensive!)  
    */
public java.lang.String[] listECUSupportedPIDs() throws android.os.RemoteException;
/**
     * Return the current value only for the PID (or PIDs if you pass more than one)
     *
     * Use this method to frequently get the value of PID(s).  Be aware that the more PIDs you request, the slower the update speed (as the OBD2 adapter will require to update more PIDs and this is the choke-point)
     * This method is asynchronous updates via the adapter
     */
public float[] getPIDValues(java.lang.String[] pidsToRetrieve) throws android.os.RemoteException;
/**
    * Return all the PID information in a single transaction. This is the new preferred method to get data from PIDs
    *
    * The unit returned is the raw unit (used for getPIDValues) - you can get the users preferred unit by using the relevant API call 
    *
    * Format of returned string array:
    *  String[] {
    *       String "pid information in csv format"
    *       String "pid information in csv format"
    *       ...(etc)
    *    }
    *  
    *  Example:
    *
    *   String[] {
    *      "<longName>,<shortName>,<unit>,<maxValue>,<minValue>,<scale>",
    *      "Intake Manifold Pressure,Intake,kPa,255,0,1",
    *      "Accelerator Pedal Position E,Accel(E),%,100,0,1"
    *   }
    */
public java.lang.String[] getPIDInformation(java.lang.String[] pidIDs) throws android.os.RemoteException;
/**
    * Get the time that the PID was last updated.
    *
    * @returns time when the PID value was retrieved via OBD/etc in milliseconds
    */
public long[] getPIDUpdateTime(java.lang.String[] pidIDs) throws android.os.RemoteException;
/**
    * Retrieves a list of PID values
    * 
    * @deprecated See getPIDInformation(...), listAllPIDs(), getPIDValues(...) and listActivePIDs() which replaces this call and can handle sensors with the same 'PID'   
    */
public float[] getValueForPids(long[] pids) throws android.os.RemoteException;
/**
    * Method to send PID data in raw form - this is for when pid plugin vendors want to protect their hard work when they have decyphed or licensed PIDs
    * Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header
    * @deprecated - see sendPIDDataPrivateV2 (which allows diagnostic headers to be set, or left as null)
    */
public boolean sendPIDDataPrivate(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header) throws android.os.RemoteException;
/**
    * Cause Torque to stop communicating with the adpter so external plugins can take full control
    *
    * This is when you want to do special things like reconfigure the adpater to talk to special units (ABS, SRS, Body control, etc) 
    * which may require torque to stay away from asking information from the adpater
    *
    * @return an int depicting the state of the lock
    * 
    *   0 = Lock gained OK - you now have exclusive access
    *  -1 = Lock failed due to unknown reason
    *  -2 = Lock failed due to another lock already being present (from your, or another plugin)
    *  -3 = Lock failed due to not being connected to adapter
    *  -4 = reserved
    *  -5 = No permissions to access in this manner (enable the ticky-box in the plugin settings for 'full access')
    *  -6 = Lock failed due to timeout (10 second limit hit)
    */
public int requestExclusiveLock(java.lang.String pluginName) throws android.os.RemoteException;
/**
    * Release the exclusive lock the plugin has with the adapter.
    *
    * @param torqueMustReInitializeTheAdapter set this to TRUE if torque must reinit comms with the vehicle ECU to continue communicating - if it is set to FALSE, then torque will simply continue trying to talk to the ECU. Take special care to ensure the adpater is in the proper state to do this
    *
    */
public boolean releaseExclusiveLock(java.lang.String pluginName, boolean torqueMustReInitializeTheAdapter) throws android.os.RemoteException;
/**
    * Method to send PID data in raw form. 
    * This updated method allows diagnostic headers to be set if required (for special commands) or left as null. 
    *  Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header,Start diagnostic command, Stop diagnostic command
    */
public boolean sendPIDDataV2(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header, java.lang.String[] startDiagnostic, java.lang.String[] stopDiagnostic) throws android.os.RemoteException;
/**
    * Method to send PID data in raw form - this is for when pid plugin vendors want to protect their hard work when they have decyphed or licensed PIDs
    * This updated method allows diagnostic headers to be set if required (for special commands) or left as null. 
    * Name, ShortName, ModeAndPID, Equation, Min, Max, Units, Header, Start diagnostic command, Stop diagnostic command
    */
public boolean sendPIDDataPrivateV2(java.lang.String pluginName, java.lang.String[] name, java.lang.String[] shortName, java.lang.String[] modeAndPID, java.lang.String[] equation, float[] minValue, float[] maxValue, java.lang.String[] units, java.lang.String[] header, java.lang.String[] startDiagnostic, java.lang.String[] stopDiagnostic) throws android.os.RemoteException;
/**
    * This retrieves the last PID that torque read from the adapter in RAW form (as the adapter sent it) 'NOT READY' may be returned 
    * if the adapter has not yet retrieved that PID. The OBDCommand should be as sent to the adpater (no spaces - eg: '010D')
    *
    * Despite the name, this cannot be used to send commands directly to the adapter, use the sendCommandGetResponse(...) method if
    * you require direct access, with the exclusive lock command if you will be sending several commands or changing the adapter mode
    * 
    * This method is currently subject to change (21 Feb 2014)
    */
public java.lang.String[] getPIDRawResponse(java.lang.String OBDCommand) throws android.os.RemoteException;
/**
     * Get the protocol being used (or 0 if not currently connected) - the protocol number is as per the ELM327 documentation
     */
public int getProtocolNumber() throws android.os.RemoteException;
/**
     * Get the protocol name being used (or AUTO if not currently connected) 
     */
public java.lang.String getProtocolName() throws android.os.RemoteException;
}
