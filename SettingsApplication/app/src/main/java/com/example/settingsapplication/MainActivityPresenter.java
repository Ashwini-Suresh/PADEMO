package com.example.settingsapplication;

public class MainActivityPresenter implements MainAcitivityContract.Presenter{

   MainAcitivityContract.View view;

    public MainActivityPresenter(MainAcitivityContract.View view) {
        this.view = view;
    }

    @Override
    public void doSave(String msg) {
     view.onSave("Saved Successfully");
    }

    @Override
    public void doRefresh(String msg) {
     view.onRefresh("Refreshed");
    }
}
