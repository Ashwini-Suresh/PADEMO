package com.example.personalaccounthmitrial;

public class MainActivityPresenter implements MainActivityInterface.Presenter{

    MainActivity view;
    public MainActivityPresenter(MainActivity view){
        this.view = view;
    }

    @Override
    public void loadAllProfiles() {

    }
}
