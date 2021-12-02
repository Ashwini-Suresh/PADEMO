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

    /**
     * Creating an Instance for the  BindingToService class
     */
    private static volatile BindingToService INSTANCE = null;

    /**
     * Creating an object for Context
     */
    private final Context context;

    /**
     * Creating an object for the AIDL Interface
     */
    public IPersonalAccount mIPersonalAccount;


    /**
     * @brief: Instance class of the BindingToService
     * @param context :
     * @return :Returns an Instance
     */
    public static BindingToService getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SettingsProvider.class) {
                if (INSTANCE == null)
                    INSTANCE = new BindingToService(context);
            }

        }
        return INSTANCE;

    }

    /**
     * @brief: Constructor of the BindingToService class
     * @param context
     */
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

    /**
     * @brief: One of the method of binding sevice and here establishes a connection with the service application
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPersonalAccount = IPersonalAccount.Stub.asInterface(service);


        }

        /**
         * @brief: Another method of Binding service this is for to Disconnection.
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
