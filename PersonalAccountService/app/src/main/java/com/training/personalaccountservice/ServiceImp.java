package com.training.personalaccountservice;

import android.os.RemoteException;

import common.IPersonalAccount;

public class ServiceImp extends IPersonalAccount.Stub {
    @Override
    public ProfileData getProfile() throws RemoteException {
        ProfileData profileData = new ProfileData();
        return profileData;
    }
}
