package com.example.personalaccounthmi.util;

import android.os.RemoteException;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonalAccountNotificationManager {

    private List<IPersonalAccountNotificationObserver> mObservers =  new ArrayList<>(); ;

    private static PersonalAccountNotificationManager sInstance;


    public static PersonalAccountNotificationManager getInstance() {

        if (sInstance == null) {
            sInstance = new PersonalAccountNotificationManager();
        }
        return sInstance;
    }

    private PersonalAccountNotificationManager() {

    }



    public boolean registerObserver(IPersonalAccountNotificationObserver observer) {
        boolean status;
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public boolean unRegisterObserver(IPersonalAccountNotificationObserver observer) {
        boolean status;
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public void notifyObservers(int property, int data ) throws RemoteException {
        for (IPersonalAccountNotificationObserver value : mObservers) {
            if (value != null) {
                value.notifyPersonalAccountChange(property, data);
            }
        }
    }
}
