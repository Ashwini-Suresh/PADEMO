/**
 * @file        PASettingsProvider.java
 * @brief       PASettingsProvider provides settings details to PASettingsHMI.
 *
 * @author      Akshay K B
 */
package com.training.personalaccountservice;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @brief: content provider to give settings details to PASettingsHMI
 */
public class PASettingsProvider extends ContentProvider {

    /**
     * Initialising AUTHORITY for CONTENT URI
     */
    public static final String AUTHORITY="com.training.personalaccountservice";

    /**
     * Initialising PATH for CONTENT URI
     */
    public static final String PATH_ACTIVE_PROFILE_SETTINGS="ACTIVE_PROFILE_SETTINGS";

    /**
     * Initialising URI for content provider
     */
    public static final Uri CONTENT_URI= Uri.parse("content://"+AUTHORITY+"/"+PATH_ACTIVE_PROFILE_SETTINGS);

    /**
     * Integer variable to match with specific URI
     */
    public static final  int ACTIVE_PROFILE_SETTINGS = 1;

    /**
     * Declaring new UriMatcher object
     */
    private static final UriMatcher MATCHER =new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * Matching integer variable with URI
     */
    static {
        MATCHER.addURI(AUTHORITY, PATH_ACTIVE_PROFILE_SETTINGS, ACTIVE_PROFILE_SETTINGS);
    }

    /**
     * Initialising MIME TYPE to inform PASettingsHMI what type of data is sent
     */
    public static final String MIME_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+"vnd.com.training.settings";

    /**
     * Declaring object of PASDataBaseManager
     */
    PAServiceManager mPASManager;

    /**
     * @brief: getting PAServiceManager instance on creating the content provider
     * @return : returns boolean true
     */
    @Override
    public boolean onCreate() {
        Log.i("Content provider","onCreate called");
        mPASManager =PAServiceManager.getInstance(getContext());
        return true;
    }

    /**
     * @brief: To provide settings details of active profile stored in database to PASettingsHMI
     * @param uri : URI from PASettingsHMI. data will be sent only if it matches
     * @param strings : Array of columns to select from table.
     *                do not require this parameter to perform action
     * @param s : Specific criteria for selection. do not require this parameter to perform action
     * @param strings1 : selection values of criteria. do not require this parameter to perform action
     * @param s1 : Sort order. do not require this parameter to perform action
     * @return : Returns Cursor containing Settings column value of active profile
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Log.i("Content provider","Query called");
        Cursor cursor = null;
        if (MATCHER.match(uri) == ACTIVE_PROFILE_SETTINGS)
            cursor = mPASManager.readActiveProfileSettings();

        return cursor;
    }

    /**
     * @brief: To inform what type of data is shared to PASettingsHMI.
     * @param uri : URI from PASettingsHMI.
     * @return : Returns String MIME_TYPE.
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        if(MATCHER.match(uri)==ACTIVE_PROFILE_SETTINGS){
            return MIME_TYPE;
        }else {
            return null;
        }
    }

    /**
     * @brief: used to insert data into table , here it is not using
     * @param uri : URI from PASettingsHMI.
     * @param contentValues : ContentValues to put into table
     * @return : returns Uri null because its not used
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }


    /**
     * @brief: used to insert data into table , here it is not using
     * @param uri :URI from PASettingsHMI. Not used
     * @param s : where clause for perform query of database to delete. Not used
     * @param strings : values for for where clause. Not used
     * @return : returns integer value of number of columns deleted, here it returns zero because
     * we are not performing the delete operation
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    /**
     * @brief: To update settings details provided by PASettingsHMI to Settings column of active profile
     * @param uri : URI from PASettingsHMI
     * @param contentValues : Content values provided by PASettingsHMI
     * @param s : Where clause . Not needed here
     * @param strings : Values for where clause. Not needed here
     * @return : Integer value of numbers of rows changed
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        Log.i("Content provider","update called");
        int updateCount =-1;
        if (MATCHER.match(uri) == ACTIVE_PROFILE_SETTINGS) {
            updateCount= mPASManager.updateActiveProfileSettings(contentValues);
        }

        return updateCount;
    }
}
