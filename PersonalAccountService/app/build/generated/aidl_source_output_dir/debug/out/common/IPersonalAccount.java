/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package common;
public interface IPersonalAccount extends android.os.IInterface
{
  /** Default implementation for IPersonalAccount. */
  public static class Default implements common.IPersonalAccount
  {
    @Override public java.util.List<com.training.personalaccountservice.ProfileData> getAllProfile() throws android.os.RemoteException
    {
      return null;
    }
    @Override public void addProfile(java.lang.String profileName, java.lang.String profileAvatar) throws android.os.RemoteException
    {
    }
    @Override public void changeActiveProfile(int pId) throws android.os.RemoteException
    {
    }
    @Override public java.util.List<java.lang.String> getAvailableAvatarList() throws android.os.RemoteException
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
        case TRANSACTION_getAllProfile:
        {
          data.enforceInterface(descriptor);
          java.util.List<com.training.personalaccountservice.ProfileData> _result = this.getAllProfile();
          reply.writeNoException();
          reply.writeTypedList(_result);
          return true;
        }
        case TRANSACTION_addProfile:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.addProfile(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_changeActiveProfile:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          this.changeActiveProfile(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_getAvailableAvatarList:
        {
          data.enforceInterface(descriptor);
          java.util.List<java.lang.String> _result = this.getAvailableAvatarList();
          reply.writeNoException();
          reply.writeStringList(_result);
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
      @Override public java.util.List<com.training.personalaccountservice.ProfileData> getAllProfile() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<com.training.personalaccountservice.ProfileData> _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getAllProfile, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getAllProfile();
          }
          _reply.readException();
          _result = _reply.createTypedArrayList(com.training.personalaccountservice.ProfileData.CREATOR);
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void addProfile(java.lang.String profileName, java.lang.String profileAvatar) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(profileName);
          _data.writeString(profileAvatar);
          boolean _status = mRemote.transact(Stub.TRANSACTION_addProfile, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().addProfile(profileName, profileAvatar);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void changeActiveProfile(int pId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(pId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_changeActiveProfile, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().changeActiveProfile(pId);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public java.util.List<java.lang.String> getAvailableAvatarList() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<java.lang.String> _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getAvailableAvatarList, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getAvailableAvatarList();
          }
          _reply.readException();
          _result = _reply.createStringArrayList();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static common.IPersonalAccount sDefaultImpl;
    }
    static final int TRANSACTION_getAllProfile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addProfile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_changeActiveProfile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_getAvailableAvatarList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
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
  public java.util.List<com.training.personalaccountservice.ProfileData> getAllProfile() throws android.os.RemoteException;
  public void addProfile(java.lang.String profileName, java.lang.String profileAvatar) throws android.os.RemoteException;
  public void changeActiveProfile(int pId) throws android.os.RemoteException;
  public java.util.List<java.lang.String> getAvailableAvatarList() throws android.os.RemoteException;
}
