/**
 * @file MainActivityPresenter.java
 * @brief This class handle all actions related to SettingsApplication.
 * This makes a connection between view and model class.
 * @author Ashwini.
 */
package com.example.settingsapplication.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.settingsapplication.MainAcitivityContract;
import com.example.settingsapplication.Model.MainActivityModel;

import java.util.HashMap;

public class MainActivityPresenter implements MainAcitivityContract.Presenter {

    /**
     * creating an object for View Interface in the MainActivityContract
     */
    MainAcitivityContract.View view;

    /**
     * Creating an object for the model interface in the MainActivityContract
     */
    MainAcitivityContract.MainActivityModel mMainActivityModel;


    /**
     * @brief: Creating a constructor for this class
     * @param view :Object of View interface
     * @param context: passing context to model class from view
     */
    public MainActivityPresenter(MainAcitivityContract.View view, Context context) {
        this.view = view;

        mMainActivityModel = new MainActivityModel(this, context);

    }

    /**
     * @brief when click on save button the settings status need to change and save in serviceApp
     */
    @Override
    public void doSave() {

        mMainActivityModel.onSave();
    }


    /**
     * @brief doRefresh function calls the  a function in model class to get the current settings.
     */
    @Override
    public void doRefresh() {
        mMainActivityModel.getSettings();
        Log.i("do refresh in presenter", "modelget settings");
    }


    /**
     * @param settingsKey   :String parameter for handling settingsKey
     * @param settingsValue :String parameter for handling settingsValue
     * @brief setting the settings status
     */
    @Override
    public void setSettingsStatus(String settingsKey, String settingsValue) {
        mMainActivityModel.setSettingsStatus(settingsKey, settingsValue);
    }

    /**
     * @return null: it return null value
     * @brief : This function calls a function in model class .
     */
    @Override
    public HashMap<String, String> getSettings() {

        return mMainActivityModel.getSettings();
    }


}
