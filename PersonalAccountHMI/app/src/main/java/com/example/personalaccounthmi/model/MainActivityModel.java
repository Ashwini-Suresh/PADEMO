/**
 * @file MainActivityModel.java
 * @brief This class is the model representation of the MainActivity View class
 * @author Karthika V T
 */
package com.example.personalaccounthmi.model;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.presenter.MainActivityPresenter;


public class MainActivityModel implements MainActivityContract.Model {

    MainActivityPresenter mMainActivityPresenter;

    public MainActivityModel(MainActivityPresenter mainActivityPresenter){
        mMainActivityPresenter = mainActivityPresenter;

    }
}