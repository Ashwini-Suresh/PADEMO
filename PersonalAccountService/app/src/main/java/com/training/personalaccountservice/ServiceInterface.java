package com.training.personalaccountservice;

import android.content.Context;

import java.util.List;

import common.IPersonalAccount;

public class ServiceInterface extends IPersonalAccount.Stub {

    PAServiceManager manager;

    public ServiceInterface(Context context) {
        manager=PAServiceManager.getInstance(context);

    }


    @Override
    public List<ProfileData> getAllProfile() {
        return manager.storeDataInArray();
    }

    @Override
    public void addProfile(String pName, String avatar) {
        manager.addToDataBase(pName,avatar);
    }

    @Override
    public void changeActiveProfile(int pId) {
        manager.newActiveProfile(pId);
    }
}
