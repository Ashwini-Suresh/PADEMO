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

    private SettingsProvider() {

    }

    /**
     * Content Provider
     */


 /*  public static final Uri CONTENT_URI = Uri.parse("content://com.training.personalaccountservice/ACTIVE_PROFILE_SETTINGS");

   public ContentResolver mContenetResolver=getContentResolver();
   String mSetings;

   public  String RefreshSettings()
   {
      Cursor cursor=mContenetResolver.query(CONTENT_URI,null,null,null);
      while (cursor!=null&& cursor.moveToNext()){
         mSetings=cursor.getString(0);
      }
      return mSetings;
   }
   public void SaveSettings()
   {

      if(mSetings!=null){
         ContentValues cv = new ContentValues();
         cv.put("profile_settings",mSetings);
         int updateCount =mContenetResolver.update(CONTENT_URI,cv,null,null);
         Log.i("updatecount"," "+updateCount);
      }

   }
*/
    public static SettingsProvider getInstance(Context context) {
        if (mSettingsProvider == null) {
            mSettingsProvider = new SettingsProvider();
            mContext = context;

        }
        return mSettingsProvider;
    }
    /**
     * @brief
     * @return settings: returns settings which is a json String
     */
    public String getsettings() {

        String settings = ""; ///Content provider using context


        return settings;
    }

    public boolean updateSettings(String settings) {
        //update content provider settings.

        return true;
    }


}
