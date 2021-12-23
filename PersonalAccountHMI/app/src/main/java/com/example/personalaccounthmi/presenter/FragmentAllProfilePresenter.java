/**
 * @file FragmentEditProfilePresenter.java
 * @brief The class represents the presenter class of FragmentAllProfile class
 * @author Karthika V T
 */
package com.example.personalaccounthmi.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.model.FragmentAllProfileModel;

import java.util.ArrayList;

/**
 * The FragmentAllProfilePresenter class is used to communicate between View and Model class
 */
public class FragmentAllProfilePresenter implements MainActivityContract.Presenter {

    /**
     * creating variable of MainActivityContract.Model and FragmentAllProfileModel
     */
    MainActivityContract.View view;
    FragmentAllProfileModel fragmentAllProfileModel;

    /**
     * creating constructor of FragmentAllProfilePresenter
     * @param view
     * @param context
     */
    public FragmentAllProfilePresenter(MainActivityContract.View view, Context context) {
        this.view = view;
        fragmentAllProfileModel = new FragmentAllProfileModel( this,context);

    }

    /**
     * Function getProfileList is called to invoke toGetProfile in the FragmentAllProfileModel to get the list of profiles saved in the database
     * @return fragmentAllProfile
     */
    public ArrayList<ProfileData> getProfileList(){
        return fragmentAllProfileModel.toGetProfile();
    }

    /**
     * The function getProfileCount called to invoke the getProfileCount in FragmentAllProfileModel to get the number of profiles in the database
     * @return fragmentAllProfileModel.getProfileCount
     */
    @Override
    public long getProfileCount() {
        return fragmentAllProfileModel.getProfileCount();
    }

    /**
     * The function refreshAllProfiles is called to invoke the function refreshAllProfiles in the FragmentAllProfile to update the list of profiles.
     */
    @Override
    public void refreshAllProfiles() {

        view.refreshAllProfiles();
    }


    @Override
    public void switchActiveProfile(int id) {
        fragmentAllProfileModel.switchActiveProfile(id);


    }

}
