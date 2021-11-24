/**
 * @file MainActivityContract.java
 * @brief This Interface handles all actions related to SettingsApplication.
 * This include interfaces used by the SettingsApplication.
 * @author Ashwini.
 */
package com.example.settingsapplication;

import java.util.HashMap;

public interface MainAcitivityContract {
    /**
     *  @brief interface View declares the function in view class
     */

    interface View {


        void loadSettings();
    }

    /**
     * @brief interface presenter handles the functions that are in MainActivityPresenter class
     */
    interface Presenter {

        void doSave();

        void doRefresh();

        void setSettingsStatus(String settingsKey, String settingsValue);

        HashMap<String, String> getSettings();

    }

    /**
     * @brief interface MainActivityModel having the functions that are called in MainActivityPresenter class
     */
    interface MainActivityModel {


        void setSettingsStatus(String settingsKey, String settingsValue);

        void getSettings();

        void onSave();

    }



}
