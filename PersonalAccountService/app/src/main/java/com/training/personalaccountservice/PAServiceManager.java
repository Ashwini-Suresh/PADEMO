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
    PADataBaseManager myDB;

    /**
     * Declaring object of PAServicePreference to manipulate data in sharedPreference
     */
    PAServicePreference activeList;

    /**
     * new arrayList for adding profile object and return it to ServiceInterface
     */
    List<ProfileData> list =new ArrayList<>();

    /**
     * profile id for newly added profiles this will add to database and sharedPreference
     */
    int newId;

    /**
     * profile id from database used to store in profile list
     */
    int pId;

    /**
     * profile name from database used to store in profile list
     */
    String pName;

    /**
     * profile avatar from database used to store in profile list
     */
    String pAvatar;

    /**
     * @brief: to indicate which is active profile using id stored in sharedPreference
     */
    boolean isActive;

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
        activeList = PAServicePreference.getInstance(context);
        myDB=PADataBaseManager.getInstance(context);
        //Initialising new profile id as 2 for first time because of already one profile is
        //present in database
        newId=2;
    }

    /**
     * @brief: get profile data from database and add this to profileList for
     * showing all profiles present in ui of client
     * @return : returns List of profileData
     */
    List<ProfileData> storeDataInArray() {

        int aId = activeList.getActiveId();
        list.clear();
        Cursor cursor = myDB.readAllData();

        while (cursor.moveToNext()) {
            pId = Integer.parseInt(cursor.getString(0));
            pName = cursor.getString(1);
            pAvatar = cursor.getString(2);
            isActive = pId == aId;
            ProfileData p = new ProfileData(pId, pName, pName, isActive);
            list.add(p);
        }

        return list;
    }


    /**
     * @brief: add new profile to database and id in shared preference
     * @param pName: profile name from serviceInterface
     * @param pAvatar:profile name from serviceInterface
     */
    void addToDataBase(String pName,String pAvatar){
        myDB.addProfile(newId,pName,pAvatar);
        activeList.add(newId);
        newId++;
    }


    /**
     * @brief: adding given profile id to shared preference where we store profile id of active profile
     * @param activeProfileId: profile id of selected profile
     */
    void newActiveProfile(int activeProfileId) {
        Log.i("ChangeActiveProfile","Profile id " +activeProfileId);
        Log.i("ActiveProfile","Before changing "+ activeList.getActiveId());
        if(activeList.getActiveId()!=activeProfileId){
            activeList.add(activeProfileId);
            Log.i("ActiveProfile","After changing "+ activeList.getActiveId());
        }
    }

    /**
     * @brief: getting settings column of active profile id and returning same
     * @return :returns settings
     */
    public Cursor readActiveProfileSettings() {
        Cursor cursor;
        cursor= myDB.readActiveProfileSettings();
        return cursor;
    }

    /**
     * @brief: updating settings column of active profile
     * @param contentValues : settings column name and settings value
     * @return : update count
     */
    public int updateActiveProfileSettings(ContentValues contentValues) {
        return myDB.updateActiveProfileSettings(contentValues);
    }
}
