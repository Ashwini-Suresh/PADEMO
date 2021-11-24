/**
 * @file MainActivityModel.java
 * @brief This class handle all actions related to SettingsApplication.
 * @author Ashwini.
 */
package com.example.settingsapplication.Model;

import android.content.Context;
import android.content.ServiceConnection;
import android.util.Log;

import com.example.settingsapplication.Common.SettingsConstants;
import com.example.settingsapplication.ContentProvider.SettingsProvider;
import com.example.settingsapplication.MainAcitivityContract;
import com.example.settingsapplication.Presenter.MainActivityPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import common.IPersonalAccount;

/**
 * @brief This is the MainActivityModel class which implements interface which handles HashMap and presenter class
 */
public class MainActivityModel implements MainAcitivityContract.MainActivityModel {
    /**
     * Creating an object for MainActivityPresenter
     */

    MainActivityPresenter mMainActivityPresenter;

    /**
     * Creating an object for Hashmap
     */
    HashMap<String, String> mSettingsMap = new HashMap<String, String>();

    IPersonalAccount mCommon;
    ServiceConnection mServiceConnection;
    /**
     * Initializing a context object as null
     */
    Context mContext = null;


    /**
     * @param mainActivityPresenter
     * @param context
     * @brief the MainActivityModel class to handle the hashmap function and define functions declared in the MainActivityPresenter class
     */

    public MainActivityModel(MainActivityPresenter mainActivityPresenter, Context context) {


        mMainActivityPresenter = mainActivityPresenter;

        mContext = context;
        String settingsData = SettingsProvider.getInstance(mContext).getsettings();
        if (settingsData != null && !settingsData.isEmpty()) {
            //Json parsing and assign to mSettingsMap
            try {
                mSettingsMap = jsonToMap(settingsData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            mSettingsMap = getDefaultSettings();
        }


        //bindService

    }

    private void parseSettingsData(String settingsData) {


    }

    private HashMap<String, String> getDefaultSettings() {
        HashMap<String, String> settingsMap = new HashMap<String, String>();
        settingsMap.put(SettingsConstants.AUTO_PLAY_STATUS, "ON");
        settingsMap.put(SettingsConstants.TIME_FORMAT, "ON");
        settingsMap.put(SettingsConstants.DISPLAY, "MANUAL");
        settingsMap.put(SettingsConstants.ACTIVE_THEME, "THEME1");
        settingsMap.put(SettingsConstants.ACTIVE_THEME, "THEME2");
        settingsMap.put(SettingsConstants.FM, "BIG_FM");
        settingsMap.put(SettingsConstants.FM, "RADIO_CITY");
        return settingsMap;
    }

    /**
     * @param settingsKey   :String parameter for handling settingsKey
     * @param settingsValue :String parameter for handling settingsValue
     * @brief This function is used to set the settings status using settingsKey and settingsValue
     */
    @Override
    public void setSettingsStatus(String settingsKey, String settingsValue) {
        mSettingsMap.replace(settingsKey, settingsValue);
        Log.i(settingsKey, settingsValue);
        Log.i("SAMPLE", "***************SAMPLE" + mSettingsMap);

    }

    public HashMap<String, String> getSettings() {

        return mSettingsMap;
        //return null;
    }

    @Override
    public void onSave() {
        Log.i("SAMPLE", "***************SAMPLE");
        saveSettings();
    }

    /**
     * @breif this function makes a Json String and stored as a json variable
     */
    public void saveSettings() {

        JSONObject settingsJson = new JSONObject(mSettingsMap);

        Log.i("SAMPLE", "***************SAMPLE" + settingsJson.toString());
        SettingsProvider.getInstance(mContext).updateSettings(settingsJson.toString());
        String sample = settingsJson.toString();
    }

    public HashMap<String, String> jsonToMap(String t) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = jObject.getString(key);
            map.put(key, value);

        }

        System.out.println("json : " + jObject);
        System.out.println("map : " + map);

        return map;
    }


}
