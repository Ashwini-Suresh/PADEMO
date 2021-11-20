package com.example.personalaccounthmi;

import android.os.Handler;
import android.os.RemoteException;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class MainActivityPresenter implements MainactivityInterface.Presenter{
    MainActivity view;
    public MainActivityPresenter(MainActivity view){
        this.view = view;
    }

    @Override
    public void loadAllProfiles() {


    }


}
