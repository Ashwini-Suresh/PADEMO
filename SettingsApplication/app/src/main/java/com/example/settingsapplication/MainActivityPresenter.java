/**
 * @file        MainActivityPresenter.java
 * @brief       This class handle all actions related to SettingsApplication.
 *              This makes a connection between view and model class.
 * @author      Ashwini.
 */
package com.example.settingsapplication;

public class MainActivityPresenter implements MainAcitivityContract.Presenter{

   MainAcitivityContract.View view;
   MainAcitivityContract.MainActivityModel mMainActivityModel;

    /**
     *
     * @param view
     */
    public MainActivityPresenter(MainAcitivityContract.View view) {
        this.view = view;
        mMainActivityModel = new MainActivityModel(this);
    }

    /**
     *
     * @param msg
     * @brief when click on save button the settings status need to change and save in serviceApp
     */
    @Override
    public void doSave(String msg) {
     view.onSave("Saved Successfully");
    }

    /**
     *
     * @param msg
     * @brief doRefresh function calls the  onRefresh function in the view
     */
    @Override
    public void doRefresh(String msg) {
     view.onRefresh("Refreshed");
    }

    /**
     *
     * @param settingsKey
     * @param settingsValue
     * @brief setting the settings status
     */
    @Override
    public void setSettingsStatus(String settingsKey, String settingsValue) {
        mMainActivityModel.setSettingsStatus(settingsKey, settingsValue);
    }

}
