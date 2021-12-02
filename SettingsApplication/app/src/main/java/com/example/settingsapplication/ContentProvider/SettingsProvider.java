/**
 * @file SettingsProvider.java
 * @brief This class handle all actions related to SettingsApplication.
 * In this class it uses Content provider to connect with service application
 * @author Ashwini.
 */
package com.example.settingsapplication.ContentProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


public class SettingsProvider {
    /**
     * creating an object for Context
     */
    private final ContentResolver mContentResolver;

    /**
     * Creating an Instance for the SettingsProvider class
     */
    private static volatile SettingsProvider INSTANCE = null;


    /**
     * Content Provider
     */

    public static final Uri CONTENT_URI = Uri.parse("content://com.training.personalaccountservice/ACTIVE_PROFILE_SETTINGS");


    /**
     * @param context:
     * @return returns singletonInstance
     * @brief this is a instance method of Settings provider which takes the context and creating instance
     */
    public static SettingsProvider getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SettingsProvider.class) {
                if (INSTANCE == null)
                    INSTANCE = new SettingsProvider(context);
            }

        }
        return INSTANCE;

    }

    /**
     * @brief: Constructor for the SettingsProvider class
     * @param context
     */
    private SettingsProvider(Context context) {
        mContentResolver = context.getContentResolver();
    }

    /**
     * @return settings: returns settings which is in a json String
     * @brief : fetching the saved data from the service app and stored in a string variable
     */

    public String getSettings() {
        String mSetings = null;

        Cursor cursor = mContentResolver.query(CONTENT_URI, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            mSetings = cursor.getString(0);
        }
        return mSetings;

    }


    /**
     * @param settings :it is a String in json format
     * @brief this function takes a json string and stored in Service application
     */

    public void updateSettings(String settings) {
        //update content provider settings.
        if (settings != null) {
            ContentValues cv = new ContentValues();
            cv.put("profile_settings", settings);
            int updateCount = mContentResolver.update(CONTENT_URI, cv, null, null);
            if (updateCount < 0) {
                Log.i("update ", "failed");
            }
        }

    }


}
