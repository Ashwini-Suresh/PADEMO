package com.example.personalaccounthmi.util;

import android.os.RemoteException;

public interface IPersonalAccountNotificationObserver {
    void notifyPersonalAccountChange(int propertyType, int data) throws RemoteException;
}
