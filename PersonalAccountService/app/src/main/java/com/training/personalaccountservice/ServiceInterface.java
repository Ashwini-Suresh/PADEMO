/**
 * @file        ServiceInterface.java
 * @brief       serviceInterface implements aidl functions.
 *
 * @author      Akshay K B
 */
package com.training.personalaccountservice;

import android.content.Context;
import android.os.RemoteException;

import java.util.List;

import common.IPersonalAccount;

/**
 * @brief: serviceInterface implements aidl functions.
 */
public class ServiceInterface extends IPersonalAccount.Stub {


    /**
     * Declaring PAServiceManager object.
     */
    private PAServiceManager mPASManager;

    /**
     * @param context: To give application context from service to PAServiceManager.
     */
    public ServiceInterface(Context context) {
        mPASManager =PAServiceManager.getInstance(context);

    }


    /**
     * @brief: To get profile data stored in database.
     * @return : Return list of profile data from PAServiceManager.
     */
    @Override
    public List<ProfileData> getAllProfile() {
        return mPASManager.getProfileListFromDatabase();
    }

    /**
     * @brief: For adding new profile to database.
     * @param pName: Profile name of newly created profile.
     * @param avatar:Avatar of newly created profile.
     */
    @Override
    public void addProfile(String pName, String avatar) {
        mPASManager.addNewProfileToDataBase(pName,avatar);
    }

    /**
     * @brief: To change which is active profile in sharedPreference.
     * @param pId:Adding this profile id to sharedPreference.
     */
    @Override
    public void changeActiveProfile(int pId) {
        mPASManager.switchProfile(pId);
    }

    /**
     * @brief: To get available avatar to select from.
     * @return : List of avatar.
     */
    @Override
    public List<String> getAvailableAvatarList() {
        return mPASManager.getAvailableAvatar();
    }
}
