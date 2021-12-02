/**
 * @file FragmentAllProfilePresenter.java
 * @brief The class represents the presenter class of FragmentAllProfile class
 * @author Karthika V T
 */
package com.example.personalaccounthmi.presenter;

import android.content.Context;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.model.FragmentAllProfileModel;
import com.example.personalaccounthmi.model.MainActivityModel;

import java.util.ArrayList;

public class FragmentAllProfilePresenter implements MainActivityContract.Presenter {

    MainActivityContract.View view;
    FragmentAllProfileModel fragmentAllProfileModel;


    public FragmentAllProfilePresenter(MainActivityContract.View view, Context context) {
        this.view = view;
        fragmentAllProfileModel = new FragmentAllProfileModel( this,context);

    }
    public ArrayList<ProfileData> getProfileList(){
        return fragmentAllProfileModel.toGetProfile();

    }

    @Override
    public void refreshAllProfiles() {
        view.refreshAllProfiles();
    }
}
