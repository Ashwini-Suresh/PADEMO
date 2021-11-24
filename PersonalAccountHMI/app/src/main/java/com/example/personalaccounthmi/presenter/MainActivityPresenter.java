package com.example.personalaccounthmi.presenter;

import com.example.personalaccounthmi.view.MainActivity;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.model.MainActivityModel;

public class MainActivityPresenter implements MainActivityInterface.Presenter {

    // MainActivity view;
    MainActivityInterface.View view;
    MainActivityInterface.Model model;
    public MainActivityPresenter(MainActivity view){
        this.view = view;
        model = new MainActivityModel(this);
    }



}

