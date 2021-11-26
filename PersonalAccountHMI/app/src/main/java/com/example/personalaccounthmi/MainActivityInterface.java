package com.example.personalaccounthmi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

import common.IPersonalAccount;

public class MainActivityInterface {
        private static volatile MainActivityInterface INSTANCE=null;
        private Context context;
        public IPersonalAccount mIPersonalAccount;

        /**
         * @brief: singleton instance for PAServiceManager
         * @param context:this context is required for calling database and sharedPreference Instance
         * @return : return current instance
         */
        public static MainActivityInterface getInstance(Context context){
            if (INSTANCE==null){
                synchronized (MainActivityInterface.class){
                    if(INSTANCE==null)
                        INSTANCE=new MainActivityInterface(context);
                }

            }
            return INSTANCE;
        }

        /**
         * @brief: private constructor to make this class singleton
         * @param context:this context is required for calling database and sharedPreference Instance
         */
    private MainActivityInterface(Context context){
        this.context = context;
        bindTOAIDLService();

        }
    public void bindTOAIDLService() {
        Intent intent = new Intent();
        intent.setClassName("com.training.personalaccountservice","com.training.personalaccountservice.PAService");
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }
    public List<ProfileData> getAllProfile() throws RemoteException {
        return mIPersonalAccount.getAllProfile();
    }
    public void addProfile(String profileName, String profileAvatar){
        try {
            mIPersonalAccount.addProfile(profileName,profileAvatar);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public void changeActiveProfile(int pId) {
        try {
            mIPersonalAccount.changeActiveProfile(pId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPersonalAccount = IPersonalAccount.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



}

