package com.training.personalaccountservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PADataBaseManager extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME="ProfileDB.db";
    private static final int DATABASE_VERSION =1;

    private static final String TABLE_NAME="profile_table";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME ="profile_name";
    private static final String COLUMN_AVATAR ="profile_avatar";
    private static final String COLUMN_SETTINGS ="profile_settings";

    public PADataBaseManager(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String query ="CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " + COLUMN_AVATAR + " TEXT, " +
                    COLUMN_SETTINGS +" TEXT);";
            sqLiteDatabase.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

        void addProfile(String name,String avatar, String settings){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_NAME,name);
            cv.put(COLUMN_AVATAR,avatar);
            cv.put(COLUMN_SETTINGS,settings);

            long result = db.insert(TABLE_NAME, null, cv);
            if(result==-1){
                Log.i("AddProfile","failed");
            }else
                Log.i("AddProfile","Success");
        }


        Cursor readAllData(){
            String query = "SELECT * FROM " +TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=null;
            if(db!=null){
                cursor=db.rawQuery(query,null);
            }
            return  cursor;
        }
}
