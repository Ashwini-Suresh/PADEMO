package com.example.settingsapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.settingsapplication.ContentProvider.SettingsProvider;

import common.IPersonalAccount;

public class BindingToService {

    private static volatile BindingToService INSTANCE = null;
    private final Context context;

    public IPersonalAccount mIPersonalAccount;




    public static BindingToService getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SettingsProvider.class) {
                if (INSTANCE == null)
                    INSTANCE = new BindingToService(context);
            }

        }
        return INSTANCE;

    }

    private BindingToService(Context context){
        this.context = context;
        bindTOAIDLService();

    }

    /**
     * @brief the function is called to bind the application with the service application
     */
    public void bindTOAIDLService() {
        Intent intent = new Intent();
        intent.setClassName("com.training.personalaccountservice","com.training.personalaccountservice.PAService");
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
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
