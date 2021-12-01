/**
 * @file MainActivityInterface.java
 * @brief This interface contains all major actions related to the PersonalAccountHMI Application
 * @author Karthika V T
 */
package com.example.personalaccounthmi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.personalaccounthmi.util.PersonalAccountNotificationManager;

import java.util.ArrayList;
import java.util.List;

import common.IPersonalAccount;
import common.IPersonalAccountListener;

public class MainActivityInterface {
    private static volatile MainActivityInterface INSTANCE = null;
    private final Context context;


    public IPersonalAccount mIPersonalAccount;


    public static MainActivityInterface getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MainActivityInterface.class) {
                if (INSTANCE == null)
                    INSTANCE = new MainActivityInterface(context);
            }


        }
        return INSTANCE;
    }


    private MainActivityInterface(Context context) {
        this.context = context;
        bindTOAIDLService();

    }


    public void bindTOAIDLService() {
        Intent intent = new Intent();
        intent.setClassName("com.training.personalaccountservice", "com.training.personalaccountservice.PAService");
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    public List<ProfileData> getAllProfile() throws RemoteException {
        return mIPersonalAccount.getAllProfile();
    }


    public void addProfile(String profileName, String profileAvatar) {
        try {
            mIPersonalAccount.addProfile(profileName, profileAvatar);
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

    public List<String> getAvatarList(){
        List<String> list = new ArrayList<>();
        try {
            list=mIPersonalAccount.getAvailableAvatarList();
            Log.i("avatar list ",""+list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }



    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPersonalAccount = IPersonalAccount.Stub.asInterface(service);
            try {
                mIPersonalAccount.registerCallback(new PersonalAccountListener());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}

class PersonalAccountListener extends IPersonalAccountListener.Stub {


    @Override
    public void notifyChange(int status) throws RemoteException {
        Log.i("notifyChange called", "" + status);
        PersonalAccountNotificationManager.getInstance().notifyObservers(status,0);

    }


}

