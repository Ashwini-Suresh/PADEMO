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
import java.util.Arrays;
import java.util.List;

/**
 * @brief: PAServiceManager manages the functionalities of service.
 */
public class PAServiceManager {

    /**
     * Declaring object of PASDataBaseManager
     */
    private PASDataBaseManager mPASDBManager;

    /**
     * Declaring object of PAServicePreference
     */
    private PAServicePreference mActiveList;

    /**
     * new arrayList for adding profile object and return it to ServiceInterface
     */
    private List<ProfileData> mProfileDataList =new ArrayList<>();

    /**
     * profile id for newly added profiles this will addToPreference to database and sharedPreference
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
        mPASDBManager = PASDataBaseManager.getInstance(context);
        //Initialising new profile id as 2 for first time because of already one profile is
        //present in database
        mNewProfileId=2;
    }

    /**
     * @brief: get profile data from database and addToPreference this to profileList for
     * showing all profiles present in ui of client
     * @return : returns List of profileData
     */
    public List<ProfileData> getProfileListFromDatabase() {

        int aId = mActiveList.getActiveId();
        mProfileDataList.clear();
        Cursor cursor = mPASDBManager.readAllData();

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
     * @brief: addToPreference new profile to database and id in shared preference
     * @param pName : profile name of new profile
     * @param pAvatar : profile avatar of new profile
     */
    public void addNewProfileToDataBase(String pName, String pAvatar){
        mPASDBManager.addProfile(mNewProfileId,pName,pAvatar);
        mActiveList.addToPreference(mNewProfileId);
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
            mActiveList.addToPreference(activeProfileId);
            Log.i("ActiveProfile","After changing "+ mActiveList.getActiveId());
        }
    }

    /**
     * @brief: getting settings column of active profile id and returning same
     * @return :returns settings, TYPE:Cursor
     */
    public Cursor readActiveProfileSettings() {
        Cursor cursor;
        cursor= mPASDBManager.readActiveProfileSettings();
        return cursor;
    }

    /**
     * @brief: updating settings column of active profile
     * @param contentValues : settings column name and settings value
     * @return : update count, return type:integer
     */
    public int updateActiveProfileSettings(ContentValues contentValues) {
        return mPASDBManager.updateActiveProfileSettings(contentValues);
    }

    public List<String> getAvailableAvatar() {
        List<String> avatarList = Arrays.asList( "avatar1", "avatar2", "avatar3", "avatar4", "avatar5", "avatar6", "avatar7", "avatar8");
        Cursor cursor = mPASDBManager.readAllData();
        while (cursor.moveToNext()) {
            avatarList.remove(cursor.getString(2));
        }
        return avatarList;
    }
}

