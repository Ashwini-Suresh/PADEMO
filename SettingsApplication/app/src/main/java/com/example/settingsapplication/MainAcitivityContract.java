package com.example.settingsapplication;

public interface  MainAcitivityContract {

    interface View{
        void onSave(String message);
        void onRefresh(String message);
    }
    interface Presenter{

        void doSave(String msg);
        void doRefresh(String msg);
        void setSettingsStatus(String settingsKey, String settingsValue);

    }
    interface MainActivityModel{

        void setSettingsStatus(String settingsKey, String settingsValue);

    }


}
