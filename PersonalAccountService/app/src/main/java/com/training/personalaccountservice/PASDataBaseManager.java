/**
 * @file        PASDataBaseManager.java
 * @brief       PASDataBaseManager manages the functionalities of DataBase.
 * @author      Akshay K B
 */

package com.training.personalaccountservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

/**
 * @brief: PASDataBaseManager manages the functionalities of DataBase.
 */
public class PASDataBaseManager extends SQLiteOpenHelper {

    /**
     * Variable denoting database name.
     */
    private static final String DATABASE_NAME="ProfileDB.db";
    /**
     * Variable denoting Database version.
     */
    private static final int DATABASE_VERSION =1;
    /**
     * Variable denoting database Table name.
     */
    private static final String TABLE_NAME="profile_table";
    /**
     * Variable denoting column name 0.
     */
    private static final String COLUMN_ID="_id";
    /**
     * Variable denoting column name 1.
     */
    private static final String COLUMN_NAME ="profile_name";
    /**
     * Variable denoting column name 2.
     */
    private static final String COLUMN_AVATAR ="profile_avatar";
    /**
     * Variable denoting column name 3.
     */
    private static final String COLUMN_SETTINGS ="profile_settings";


    /**
     * Variable that hold object of PASDataBaseManager.
     */
    private static volatile PASDataBaseManager INSTANCE=null;

    /**
     * @brief: Singleton instance for PPADataBaseManager.
     * @param context: This context required for creating Database.
     * @return : Return current instance.
     */
    public static PASDataBaseManager getInstance(Context context){
        if (INSTANCE==null){
            synchronized (PASDataBaseManager.class){
                if(INSTANCE==null)
                    INSTANCE=new PASDataBaseManager(context);
            }
        }
        return INSTANCE;
    }

    /**
     * @brief: private constructor to make this class singleton.
     * @param context:This context required for creating Database.
     */
    private PASDataBaseManager(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    /**
     * @brief: creating a database Table with required columns and adding one profile on creating.
     * @param sqLiteDatabase : Database object.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_AVATAR + " TEXT, " +
                COLUMN_SETTINGS + " TEXT);";
        sqLiteDatabase.execSQL(query);

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, 1);
        cv.put(COLUMN_NAME, "Driver1");
        cv.put(COLUMN_AVATAR, "avatar1");

        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Log.i("AddProfile", "failed");
        } else
            Log.i("AddProfile", "Success");

    }

    /**
     * @brief: on upgrading database is not used.
     * @param sqLiteDatabase : Database object.
     * @param i : Old version.
     * @param i1 : New version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * @brief: To add profile data to Database Table.
     * @param profile_id : Profile id of new profile to put into column 0 of new row.
     * @param profile_name : Profile name of new profile to put into column 1 of new row.
     * @param profile_avatar : Profile avatar of new profile to put into column 2 of new row.
     */
    public void addProfile(int profile_id, String profile_name, String profile_avatar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, profile_id);
        cv.put(COLUMN_NAME, profile_name);
        cv.put(COLUMN_AVATAR, profile_avatar);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Log.i("AddProfile", "failed");
        } else
            Log.i("AddProfile", "Success");
    }


    /**
     * @brief: To read all data stored in Database.
     * @return : Returns Cursor containing data from Table.
     */
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        assert db != null;

        return cursor;
    }

    /**
     * @brief: To read settings column of active profile.
     * @param activeProfileId : Profile id of current active profile.
     * @return : Returns Cursor containing Settings value.
     */
    public Cursor readActiveProfileSettings(int activeProfileId) {
        String arg = String.valueOf(activeProfileId);
        String query = "SELECT " + COLUMN_SETTINGS + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{arg});
        }

        return cursor;
    }

    /**
     * @brief: Update setting details received to setting column of active profile of database Table .
     * @param activeProfileId : Profile id of current active profile.
     * @param cv : Content value contains Column name and value to update into Table.
     * @return : Returns integer value of how many columns effected on update action.
     */
    public int updateActiveProfileSettings(int activeProfileId,ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(activeProfileId)});
    }

    /**
     * @brief: To update profile avatar of received profile id with new avatar.
     * @param activeProfileId : Profile id of current active profile.
     * @param newAvatar : New avatar to update.
     */
    public void updateActiveProfileAvatar(int activeProfileId,String newAvatar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_AVATAR, newAvatar);
        int count = db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(activeProfileId)});
        if(count==-1){
            Log.i("UPDATE_PROFILE_AVATAR","FAILED");
        }else{
            Log.i("UPDATE_PROFILE_AVATAR","SUCCESS");
        }

    }

    /**
     *  @brief: To update profile name of received profile id with new name.
     * @param activeProfileId : Profile id of current active profile.
     * @param newName : New name to update.
     */
    public void updateActiveProfileName(int activeProfileId, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, newName);
        int count = db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(activeProfileId)});

        if(count==-1){
            Log.i("UPDATE_PROFILE_NAME","FAILED");
        }else {
            Log.i("UPDATE_PROFILE_NAME","SUCCESS");
        }

    }

    /**
     * @brief: To get Row containing data of current active profile.
     * @param activeProfileId : Active profiles Id to check with COLUMN_ID to get row.
     * @return : Returns Cursor containing values of that row.
     */
    public Cursor getActiveProfile(int activeProfileId) {
        String arg = String.valueOf(activeProfileId);

        String query = "SELECT " + COLUMN_NAME+", "+COLUMN_AVATAR + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{arg});
        }

        return cursor;
    }

    /**
     * @brief: To get count of profiles.
     * @return : Returns number of rows present in Table.
     */
    public long getRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    /**
     * @brief: To delete row containing profile to delete.
     * @param activeId : Active profiles Id to check with COLUMN_ID to delete row.
     * @return : Returns boolean whether deletion is performed or not.
     */
    public boolean deleteProfile(int activeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME,COLUMN_ID + " = ?",new String[]{String.valueOf(activeId)})>0;
    }
}
