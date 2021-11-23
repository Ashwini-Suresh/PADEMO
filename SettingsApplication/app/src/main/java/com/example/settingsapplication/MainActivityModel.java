/**
 * @file        MainActivityModel.java
 * @brief       This class handle all actions related to SettingsApplication.
 *
 * @author      Ashwini.
 */
package com.example.settingsapplication;

import android.util.Log;

import com.example.settingsapplication.Common.SettingsConstants;

import java.util.HashMap;

public class MainActivityModel implements MainAcitivityContract.MainActivityModel {

    MainActivityPresenter mMainActivityPresenter;

    HashMap<String, String> mSettingsMap = new HashMap<String, String>();

    public MainActivityModel(MainActivityPresenter mainActivityPresenter) {

        mMainActivityPresenter = mainActivityPresenter;
        //bindService


        /**
         * @brief adding elements to hashmap
         *
         */
        mSettingsMap.put(SettingsConstants.AUTO_PLAY_STATUS, "ON");
        //mSettingsMap.put(SettingsConstants.AUTO_PLAY_STATUS, "OFF");
        mSettingsMap.put(SettingsConstants.TIME_FORMAT, "ON");
       // mSettingsMap.put(SettingsConstants.TIME_FORMAT, "OFF");
        mSettingsMap.put(SettingsConstants.DISPLAY, "ON");
        mSettingsMap.put(SettingsConstants.ACTIVE_THEME, "THEME1");
        mSettingsMap.put(SettingsConstants.FM, "RADIO_MANGO");
       // hmap.put(2, "Rahul");
      //  hmap.put(7, "Singh");
      //  hmap.put(49, "Ajeet");
      //  hmap.put(3, "Anuj");
    }

    @Override
    public void setSettingsStatus(String settingsKey, String settingsValue) {
        mSettingsMap.replace(settingsKey,settingsValue);
        Log.i(settingsKey,settingsValue);

    }

}
