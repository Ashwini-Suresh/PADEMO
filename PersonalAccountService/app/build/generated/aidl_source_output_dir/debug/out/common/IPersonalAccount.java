/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package common;
public interface IPersonalAccount extends android.os.IInterface
{
  /** Default implementation for IPersonalAccount. */
  public static class Default implements common.IPersonalAccount
  {
    @Override public com.training.personalaccountservice.ProfileData getProfile() throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements common.IPersonalAccount
  {
    private static final java.lang.String DESCRIPTOR = "common.IPersonalAccount";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an common.IPersonalAccount interface,
     * generating a proxy if needed.
     */
    public static common.IPersonalAccount asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof common.IPersonalAccount))) {
        return ((common.IPersonalAccount)iin);
      }
      return new common.IPersonalAccount.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_getProfile:
        {
          data.enforceInterface(descriptor);
          com.training.personalaccountservice.ProfileData _result = this.getProfile();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements common.IPersonalAccount
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
      @Override public com.training.personalaccountservice.ProfileData getProfile() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        com.training.personalaccountservice.ProfileData _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getProfile, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getProfile();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = com.training.personalaccountservice.ProfileData.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static common.IPersonalAccount sDefaultImpl;
    }
    static final int TRANSACTION_getProfile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(common.IPersonalAccount impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static common.IPersonalAccount getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public com.training.personalaccountservice.ProfileData getProfile() throws android.os.RemoteException;
}
