/**
 * @file        PADataBaseManager.java
 * @brief       PADataBaseManager manages the functionalities of DataBase.
 * @author      Akshay K B
 */

package com.training.personalaccountservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @brief: PADataBaseManager manages the functionalities of DataBase.
 */
public class PADataBaseManager extends SQLiteOpenHelper {

    /**
     * @brief: varaiable denoting
     */
    private static final String DATABASE_NAME="ProfileDB.db";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME="profile_table";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME ="profile_name";
    private static final String COLUMN_AVATAR ="profile_avatar";
    private static final String COLUMN_SETTINGS ="profile_settings";

    private final Context context;

    private static volatile PADataBaseManager INSTANCE=null;



    public static PADataBaseManager getInstance(Context context){
        if (INSTANCE==null){
            synchronized (PADataBaseManager.class){
                if(INSTANCE==null)
                    INSTANCE=new PADataBaseManager(context);
            }
        }
        return INSTANCE;
    }

    private PADataBaseManager(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    //creating new database with columns as id,name,avatar,settings
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String query ="CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " + COLUMN_AVATAR + " TEXT, " +
                    COLUMN_SETTINGS +" TEXT);";
            sqLiteDatabase.execSQL(query);

            ContentValues cv = new ContentValues();

            cv.put(COLUMN_ID,1);
            cv.put(COLUMN_NAME,"Driver1");
            cv.put(COLUMN_AVATAR,"avatar1");

            long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);
            if(result==-1){
                Log.i("AddProfile","failed");
            }else
                Log.i("AddProfile","Success");

        }

        //when upgrading delete current table and create new
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

        //adding new profile to database
        void addProfile(int profile_id,String profile_name,String profile_avatar){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_ID,profile_id);
            cv.put(COLUMN_NAME,profile_name);
            cv.put(COLUMN_AVATAR,profile_avatar);

            long result = db.insert(TABLE_NAME, null, cv);
            if(result==-1){
                Log.i("AddProfile","failed");
            }else
                Log.i("AddProfile","Success");
        }


        //reading all profiles from database and returning as cursor
        Cursor readAllData(){
            String query = "SELECT * FROM " +TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=null;
            if(db!=null){
                cursor=db.rawQuery(query,null);
            }
            return  cursor;
        }

    Cursor readActiveProfileSettings(){
        int activeProfileId = PAServicePreference.getInstance(context).getActiveId();
        String arg=String.valueOf(activeProfileId);
        Log.i("content provider","activeprofile "+activeProfileId);
        String query = "SELECT "+COLUMN_SETTINGS+" FROM " +TABLE_NAME+" WHERE "+COLUMN_ID+ " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,new String[] {arg});
        }
        return  cursor;
    }

    int updateActiveProfileSettings(ContentValues cv) {
        int activeProfileId = PAServicePreference.getInstance(context).getActiveId();
        SQLiteDatabase db = this.getWritableDatabase();

        return db.update(TABLE_NAME,cv,COLUMN_ID+" = ?",new String[]{String.valueOf(activeProfileId)});
    }

}
