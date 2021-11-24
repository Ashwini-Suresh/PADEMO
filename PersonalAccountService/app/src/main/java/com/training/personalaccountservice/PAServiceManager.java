/**
 * @file        PAServiceManager.java
 * @brief       PAServiceManager manages the functionalities of service.
 *
 * @author      Akshay K B
 */
package com.training.personalaccountservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief: PAServiceManager manages the functionalities of service.
 */
public class PAServiceManager {

    /**
     * Declaring object of PADataBaseManager to manipulate data in database
     */
    private PADataBaseManager mPADBManager;

    /**
     * Declaring object of PAServicePreference to manipulate data in sharedPreference
     */
    private PAServicePreference mActiveList;

    /**
     * new arrayList for adding profile object and return it to ServiceInterface
     */
    private List<ProfileData> mProfileDataList =new ArrayList<>();

    /**
     * profile id for newly added profiles this will add to database and sharedPreference
     */
    private int mNewProfileId;

    /**
     * variable that hold object of PAServiceManager
     */
    private static volatile PAServiceManager INSTANCE=null;

    /**
     * @brief: singleton instance for PAServiceManager
     * @param context:this context is required for calling database and sharedPreference Instance
     * @return : return current instance
     */
    public static PAServiceManager getInstance(Context context){
        if (INSTANCE==null){
            synchronized (PAServiceManager.class){
                if(INSTANCE==null)
                    INSTANCE=new PAServiceManager(context);
            }

        }
        return INSTANCE;
    }

    /**
     * @brief: private constructor to make this class singleton
     * @param context:this context is required for calling database and sharedPreference Instance
     */
    private PAServiceManager(Context context){
        mActiveList = PAServicePreference.getInstance(context);
        mPADBManager=PADataBaseManager.getInstance(context);
        //Initialising new profile id as 2 for first time because of already one profile is
        //present in database
        mNewProfileId=2;
    }

    /**
     * @brief: get profile data from database and add this to profileList for
     * showing all profiles present in ui of client
     * @return : returns List of profileData
     */
    public List<ProfileData> getProfileListFromDatabase() {

        int aId = mActiveList.getActiveId();
        mProfileDataList.clear();
        Cursor cursor = mPADBManager.readAllData();

        while (cursor.moveToNext()) {

            int mProfileId = Integer.parseInt(cursor.getString(0));
            String mProfileName = cursor.getString(1);
            String mProfileAvatar = cursor.getString(2);
            boolean misActive = mProfileId == aId;

            ProfileData p = new ProfileData(mProfileId, mProfileName, mProfileAvatar, misActive);
            mProfileDataList.add(p);
        }

        return mProfileDataList;
    }


    /**
     * @brief: add new profile to database and id in shared preference
     * @param pName : profile name of new profile
     * @param pAvatar : profile avatar of new profile
     */
    public void addNewProfileToDataBase(String pName, String pAvatar){
        mPADBManager.addProfile(mNewProfileId,pName,pAvatar);
        mActiveList.add(mNewProfileId);
        mNewProfileId++;
    }


    /**
     * @brief: adding given profile id to shared preference where we store profile id of active profile
     * @param activeProfileId: profile id of selected profile
     */
    public void switchProfile(int activeProfileId) {
        Log.i("ChangeActiveProfile","Profile id " +activeProfileId);
        Log.i("ActiveProfile","Before changing "+ mActiveList.getActiveId());
        if(mActiveList.getActiveId()!=activeProfileId){
            mActiveList.add(activeProfileId);
            Log.i("ActiveProfile","After changing "+ mActiveList.getActiveId());
        }
    }

    /**
     * @brief: getting settings column of active profile id and returning same
     * @return :returns settings, TYPE:Cursor
     */
    public Cursor readActiveProfileSettings() {
        Cursor cursor;
        cursor= mPADBManager.readActiveProfileSettings();
        return cursor;
    }

    /**
     * @brief: updating settings column of active profile
     * @param contentValues : settings column name and settings value
     * @return : update count, return type:integer
     */
    public int updateActiveProfileSettings(ContentValues contentValues) {
        return mPADBManager.updateActiveProfileSettings(contentValues);
    }
}
