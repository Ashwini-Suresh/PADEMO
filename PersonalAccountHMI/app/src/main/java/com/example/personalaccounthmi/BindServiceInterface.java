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
import android.widget.Toast;

import com.example.personalaccounthmi.util.PersonalAccountNotificationManager;

import java.util.ArrayList;
import java.util.List;

import common.IPersonalAccount;
import common.IPersonalAccountListener;

public class BindServiceInterface {
    private static volatile BindServiceInterface INSTANCE = null;
    private final Context context;


    public IPersonalAccount mIPersonalAccount;


    public static BindServiceInterface getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BindServiceInterface.class) {
                if (INSTANCE == null)
                    INSTANCE = new BindServiceInterface(context);
            }
        }
        return INSTANCE;
    }


    private BindServiceInterface(Context context) {
        this.context = context;
        bindTOAIDLService();

    }


    public void bindTOAIDLService() {
        Intent intent = new Intent();
        intent.setClassName("com.training.personalaccountservice", "com.training.personalaccountservice.PAService");
        if (context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)){
            Toast.makeText(context, "Service connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Service not Connected", Toast.LENGTH_SHORT).show();
        }

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

    public void updateProfileName(String newName){
        try {
            mIPersonalAccount.updateProfileName(newName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public void updateProfileAvatar(String newAvatar){
        try {
            mIPersonalAccount.updateProfileAvatar(newAvatar);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public List<String> getAvatarList(){
        List<String> list = new ArrayList<>();
        try {
            list=mIPersonalAccount.getAvailableAvatarList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ProfileData activeProfile() throws RemoteException {
        return mIPersonalAccount.activeProfileData();
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

    public long getProfileCount() {
        long count=0;
        try {
            count = mIPersonalAccount.profileCount();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deleteProfile(){
        try {
            mIPersonalAccount.deleteProfile();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

class PersonalAccountListener extends IPersonalAccountListener.Stub {


    @Override
    public void notifyChange(int status) throws RemoteException {
        PersonalAccountNotificationManager.getInstance().notifyObservers(status,0);
    }

}
