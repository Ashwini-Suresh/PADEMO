package com.training.personalaccountservice;

import android.content.Context;

import java.util.List;

import common.IPersonalAccount;

/**
 * @brief: serviceInterface implements aidl functions
 */
public class ServiceInterface extends IPersonalAccount.Stub {


    /**
     * declaring PAServiceManager object
     */
    PAServiceManager manager;

    /**
     *
     * @param context: to give application context from service to PAServiceManager
     */
    public ServiceInterface(Context context) {
        manager=PAServiceManager.getInstance(context);

    }


    /**
     * @brief: to get profile data stored in database
     * @return: return list of profile data from PAServiceManager
     */
    @Override
    public List<ProfileData> getAllProfile() {
        return manager.storeDataInArray();
    }

    /**
     * @brief: for adding new profile to database
     * @param pName: profile name of newly created profile
     * @param avatar:avatar of newly created profile
     */
    @Override
    public void addProfile(String pName, String avatar) {
        manager.addToDataBase(pName,avatar);
    }

    /**
     * @brief: to change which is active profile in sharedPreference
     * @param pId:adding this profile id to sharedPreference
     */
    @Override
    public void changeActiveProfile(int pId) {
        manager.newActiveProfile(pId);
    }
}
