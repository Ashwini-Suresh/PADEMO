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
import android.os.RemoteCallbackList;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.IPersonalAccountListener;

/**
 * @brief: PAServiceManager manages the functionalities of service.
 */
public class PAServiceManager {

    /**
     * Declaring object of PASDataBaseManager.
     */
    private final PASDataBaseManager mPASDBManager;

    /**
     * Declaring object of PAServicePreference.
     */
    private final PAServicePreference mActiveList;

    /**
     * New arrayList for adding profile object and return it to ServiceInterface.
     */
    private  List<ProfileData> mProfileDataList =new ArrayList<>();

    /**
     * Profile id for newly added profiles this will addToPreference to database and sharedPreference.
     */
    private int mNewProfileId;

    /**
     * Declaring RemoteCallbackList of IPersonalAccountListener.
     */
    private final RemoteCallbackList<IPersonalAccountListener> mCallback=new RemoteCallbackList<>();

    /**
     * Initialising value for notifying Profile change.
     */
    private static final int PROFILE_CHANGE=7;
    /**
     * Initialising value for notifying Profile data change.
     */
    private static final int PROFILE_DATA_CHANGE=11;
    /**
     * Initialising value for notifying Profile settings change.
     */
    private static final int PROFILE_SETTINGS_CHANGE=10;
    /**
     * Variable that hold object of PAServiceManager.
     */
    private static volatile PAServiceManager INSTANCE=null;

    /**
     * @brief: Singleton instance for PAServiceManager.
     * @param context:This context is required for calling database and sharedPreference Instance.
     * @return : Return current instance.
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
     * @brief: Private constructor to make this class singleton.
     * @param context:This context is required for calling database and sharedPreference Instance.
     */
    private PAServiceManager(Context context){
        mActiveList = PAServicePreference.getInstance(context);
        mPASDBManager = PASDataBaseManager.getInstance(context);
        //Initialising new profile id as 2 for first time because of already one profile is
        //present in database
        mNewProfileId=2;
    }

    /**
     * @brief: Get profile data from database and addToPreference this to profileList for
     * showing all profiles present in ui of client.
     * @return : Returns List of profileData.
     */
    public List<ProfileData> getProfileListFromDatabase() {

        int aId = getActiveId();
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
     * @brief: To get active profile Id from sharedPreference.
     * @return : Return active profile Id.
     */
    private int getActiveId() {
        return mActiveList.getActiveId();
    }


    /**
     * @brief: AddToPreference new profile to database and id in shared preference.
     * @param pName : Profile name of new profile.
     * @param pAvatar : Profile avatar of new profile.
     */
    public void addNewProfileToDataBase(String pName, String pAvatar){
        mPASDBManager.addProfile(mNewProfileId,pName,pAvatar);
        mActiveList.addToPreference(mNewProfileId);
        mNewProfileId++;
        broadCastCallBack(PROFILE_CHANGE);
    }


    /**
     * @brief: Adding given profile id to shared preference where we store profile id of active profile.
     * @param activeProfileId: Profile id of selected profile.
     */
    public void switchProfile(int activeProfileId) {

        if(mActiveList.getActiveId()!=activeProfileId){
            mActiveList.addToPreference(activeProfileId);
            broadCastCallBack(PROFILE_CHANGE);
        }
    }

    /**
     * @brief: Getting settings column of active profile id and returning same.
     * @return :Returns Cursor containing settings.
     */
    public Cursor readActiveProfileSettings() {
        return mPASDBManager.readActiveProfileSettings(getActiveId());
    }

    /**
     * @brief: Updating settings column of active profile.
     * @param contentValues : Settings column name and settings value.
     * @return : Returns Update count.
     */
    public int updateActiveProfileSettings(ContentValues contentValues) {
        broadCastCallBack(PROFILE_SETTINGS_CHANGE);
        return mPASDBManager.updateActiveProfileSettings(getActiveId(),contentValues);
    }

    /**
     * @brief: To get available avatar by removing selected avatar from all profile avatar list.
     * @return : Return a list of avatar.
     */
    public List<String> getAvailableAvatar() {
        List<String> avatarList = new ArrayList<>(Arrays.asList( "avatar1", "avatar2", "avatar3", "avatar4", "avatar5", "avatar6", "avatar7", "avatar8"));
        Cursor cursor = mPASDBManager.readAllData();
        while (cursor.moveToNext()) {
            String avatar= cursor.getString(2);
            avatarList.remove(avatar);
        }
        return avatarList;
    }

    /**
     *
     * @param callback :
     */
    public void registerCallback(IPersonalAccountListener callback) {
        mCallback.register(callback);
    }

    /**
     *
     * @param notificationType :
     */
    private void broadCastCallBack(int notificationType){
        int broadCastCount = mCallback.beginBroadcast();
        try{
            for (int i = 0; i < broadCastCount; i++) {
                mCallback.getBroadcastItem(i).notifyChange(notificationType);
            }
        }catch(Exception e){
            Log.i("Exception",e+" @broadCastCallBack-line 200 PAServiceManager");
        } finally {
            mCallback.finishBroadcast();
        }
    }

    /**
     * @brief: To update active profiles avatar with new selected one.
     * @param newAvatar: New selected avatar to update current active profile.
     */
    public void updateProfileAvatar(String newAvatar) {
        mPASDBManager.updateActiveProfileAvatar(getActiveId(),newAvatar);
        broadCastCallBack(PROFILE_DATA_CHANGE);
    }

    /**
     * @brief: To update active profiles name with new one.
     * @param newName: New profile name to update current active profile.
     */
    public void updateProfileName(String newName) {
        mPASDBManager.updateActiveProfileName(getActiveId(),newName);
        broadCastCallBack(PROFILE_DATA_CHANGE);
    }

    /**
     * @brief: To get active profiles data.
     * @return : returns ProfileData object.
     */
    public ProfileData getActiveProfile() {
        Cursor c = mPASDBManager.getActiveProfile(getActiveId());
        while (c.moveToNext()) {
            String name= c.getString(0);
            String avatar= c.getString(1);
            return new ProfileData(getActiveId(),name,avatar,true);
        }
        return null;
    }

    /**
     * @brief: To get number of profile present in database.
     * @return : Returns profile count.
     */
    public long getProfileCount() {
        return mPASDBManager.getRowCount();
    }

    /**
     * @brief: To remove profile from Database and to change active profile.
     */
    public void deleteActiveProfile() {
        if(mPASDBManager.deleteProfile(getActiveId())){
            mActiveList.removeCurrentId();
            broadCastCallBack(PROFILE_CHANGE);
            Log.i("Delete Profile","SUCCESS");
        }else
            Log.i("Delete Profile","FAILED");
    }
}

