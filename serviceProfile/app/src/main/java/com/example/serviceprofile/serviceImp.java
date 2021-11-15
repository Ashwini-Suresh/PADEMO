package com.example.serviceprofile;

import android.os.RemoteException;

import same.ProfileService;

public class serviceImp extends ProfileService.Stub {
    @Override
    public Profile getProfile() throws RemoteException {
        Profile p=new Profile();
        return p;
    }
}
