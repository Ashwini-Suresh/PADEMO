package com.example.personalaccounthmi.model;

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.presenter.MainActivityPresenter;


public class MainActivityModel  {
    MainActivityPresenter mMainActivityPresenter;

    //create constructor of Mainactivity Model
    public MainActivityModel(MainActivityPresenter mainActivityPresenter){
        mMainActivityPresenter = mainActivityPresenter;


    }


}