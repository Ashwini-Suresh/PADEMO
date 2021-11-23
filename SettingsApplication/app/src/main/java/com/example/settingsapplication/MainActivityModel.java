/**
 * @file MainActivityModel.java
 * @brief This class handle all actions related to SettingsApplication.
 * @author Ashwini.
 */
package com.example.settingsapplication;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;

import com.example.settingsapplication.Common.SettingsConstants;

import java.util.HashMap;
import java.util.Map;

import common.IPersonalAccount;

/**
 * @brief This is the MainActivityModel class which implements interface which handles HashMap and presenter class
 */
public class MainActivityModel implements MainAcitivityContract.MainActivityModel {

    MainActivityPresenter mMainActivityPresenter;

    HashMap<String, String> mSettingsMap = new HashMap<String, String>();

    IPersonalAccount mCommon;
    ServiceConnection mServiceConnection;

    /**
     *
     * @param mainActivityPresenter
     * @brief the MainActivityModel class to handle the hashmap function and define functions declared in the MainActivityPresenter class
     */

    public MainActivityModel(MainActivityPresenter mainActivityPresenter) {

        mMainActivityPresenter = mainActivityPresenter;
        //bindService



        // adding elements to hashmap

        mSettingsMap.put(SettingsConstants.AUTO_PLAY_STATUS, "ON");
        mSettingsMap.put(SettingsConstants.TIME_FORMAT, "ON");
        mSettingsMap.put(SettingsConstants.DISPLAY, "MANUAL");
        mSettingsMap.put(SettingsConstants.ACTIVE_THEME, "THEME1");
        mSettingsMap.put(SettingsConstants.ACTIVE_THEME, "THEME2");
        mSettingsMap.put(SettingsConstants.FM, "BIG_FM");
        mSettingsMap.put(SettingsConstants.FM, "RADIO_CITY");
    }
    /**
     *
     * @param settingsKey :String parameter for handling settingsKey
     * @param settingsValue :String parameter for handling settingsValue
     * @brief This function is used to set the settings status using settingsKey and settingsValue
     */
    @Override
    public void setSettingsStatus(String settingsKey, String settingsValue) {
        mSettingsMap.replace(settingsKey, settingsValue);
        Log.i(settingsKey, settingsValue);

    }
    public void getSettings()
    {
        //return mSettingsMap;
    }

}
