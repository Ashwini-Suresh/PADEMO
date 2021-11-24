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

import com.example.settingsapplication.MainAcitivityContract;
import com.example.settingsapplication.Model.MainActivityModel;
import com.example.settingsapplication.Presenter.MainActivityPresenter;


public class SettingsProvider {
    /**
     * creating a object for SettingsProvider class and initialized to null
     */
    private static SettingsProvider mSettingsProvider = null;
    /**
     * Creating an object for Context.
     */
    private static Context mContext;
    /**
     * creating an object for Context
     */
    private ContentResolver mContenetResolver;

    /**
     * Content Provider
     */

    public static final Uri CONTENT_URI = Uri.parse("content://com.training.personalaccountservice/ACTIVE_PROFILE_SETTINGS");
    private String mSetings;

    /**
     * @param context
     * @return returns singletonInstance
     * @brief this is a instance method of Settings provider which takes the context and creating instance
     */
    public static SettingsProvider getInstance(Context context) {
        if (mSettingsProvider == null) {
            mSettingsProvider = new SettingsProvider();
            mContext = context;
        }
        return mSettingsProvider;
    }

    /**
     * @return settings: returns settings which is in a json String
     * @brief
     */
    public String getsettings() {
        ContentResolver mContenetResolver = mContext.getContentResolver();
        Cursor cursor = mContenetResolver.query(CONTENT_URI, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            mSetings = cursor.getString(0);
        }
        return mSetings;

    }

    /**
     * @param settings :it is a String in json format
     * @return returns a boolean value
     * @brief this function takes a json string and stored in Service application
     */

    public boolean updateSettings(String settings) {
        //update content provider settings.
        if (settings != null) {
            ContentValues cv = new ContentValues();
            cv.put("profile_settings", mSetings);
            int updateCount = mContenetResolver.update(CONTENT_URI, cv, null, null);
            Log.i("updatecount", " " + updateCount);
        }

        return true;
    }


}
