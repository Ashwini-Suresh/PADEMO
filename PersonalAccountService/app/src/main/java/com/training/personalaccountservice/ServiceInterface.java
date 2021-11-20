package com.training.personalaccountservice;

import android.content.Context;
import android.os.RemoteException;

import java.util.List;

import common.IPersonalAccount;

public class ServiceInterface extends IPersonalAccount.Stub {
    Context context ;

    public ServiceInterface(Context context) {
        this.context=context;

    }


    @Override
    public List<ProfileData> getAllProfile() throws RemoteException {
        PAServiceManager manager=new PAServiceManager(context);
        return manager.storeDataInArray();
    }

    @Override
    public void addProfile(String pName, String avatar) throws RemoteException {
        PAServiceManager manager=new PAServiceManager(context);
        manager.addToDataBase(pName,avatar,null);

    }

}
