package com.training.personalaccountservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PAServiceManager {

    PADataBaseManager myDB;
    PAServicePreference activeList;
    List<ProfileData> list =new ArrayList<>();
    int newId,pId;
    String pName;
    String pAvatar;
    boolean isActive;

    private static volatile PAServiceManager INSTANCE=null;

    public static PAServiceManager getInstance(Context context){
        if (INSTANCE==null){
            synchronized (PAServiceManager.class){
                if(INSTANCE==null)
                    INSTANCE=new PAServiceManager(context);
            }

        }
        return INSTANCE;
    }

    private PAServiceManager(Context context){
        activeList = PAServicePreference.getInstance(context);
        myDB=PADataBaseManager.getInstance(context);
        newId=2;
    }

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


    void addToDataBase(String pName,String pAvatar){
        myDB.addProfile(newId,pName,pAvatar);
        activeList.add(newId);
        newId++;
    }


    void newActiveProfile(int activeProfileId) {
        Log.i("ChangeActiveProfile","Profile id " +activeProfileId);
        Log.i("ActiveProfile","Before changing "+ activeList.getActiveId());
        if(activeList.getActiveId()!=activeProfileId){
            activeList.add(activeProfileId);
            Log.i("ActiveProfile","After changing "+ activeList.getActiveId());
        }
    }


    public Cursor readActiveProfileSettings() {
        Cursor cursor;
        cursor= myDB.readActiveProfileSettings();
        return cursor;
    }

    public int updateActiveProfileSettings(ContentValues contentValues) {
        return myDB.updateActiveProfileSettings(contentValues);
    }
}
