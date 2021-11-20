package com.training.personalaccountservice;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class PAServiceManager {

    PADataBaseManager myDB;
    List<ProfileData> list =new ArrayList<>();
    int id;
    String name;
    String avatar;

    public PAServiceManager(Context context){
        myDB=new PADataBaseManager(context);
    }


    List<ProfileData> storeDataInArray(){

        Cursor cursor= myDB.readAllData();

        if(cursor.getCount()==0){
            myDB.addProfile("Driver1","avatar1","settings");
            storeDataInArray();
        }else{
            while(cursor.moveToNext())
            {
                id=Integer.parseInt(cursor.getString(0));
                name=cursor.getString(1);
                avatar=cursor.getString(2);
                ProfileData p = new ProfileData(id,name,avatar);
                list.add(p);
            }
        }
        return list;
    }


    void addToDataBase(String pName,String pAvatar,String pSettings){
        myDB.addProfile(pName,pAvatar,pSettings);
    }
}