/**
 * @file FragmentEditProfilePresenter.java
 * @brief This java class represents the presenter class of the FragmentEditProfilePresenter,java
 * @author Karthika V T
 */
package com.example.personalaccounthmi.presenter;

import android.content.Context;
import android.os.RemoteException;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.model.FragmentEditProfileModel;

/**
 * The FragmentEditProfilePresenter class represents the presenter class which communicates between view class and model class
 */
public class FragmentEditProfilePresenter implements MainActivityContract.EditProfilePresenter {

    /**
     * creating variables of MainActivityContract.EditProfileView and FragmentEditProfileModel
     */
    MainActivityContract.EditProfileVIew view;
    FragmentEditProfileModel fragmentEditProfileModel;

    /**
     * creating constructor of FragmentEditProfilePresenter
     * @param view
     * @param context
     */
    public FragmentEditProfilePresenter(MainActivityContract.EditProfileVIew view, Context context) {
        this.view = view;
        fragmentEditProfileModel = new FragmentEditProfileModel( this,context);
    }

    /**
     * Function getHighlightProfile invokes highlightProfile function in FragmentEditProfileModel to get the object that is selected
     * @return fragmentEditProfileModel.highlightProfile
     * @throws RemoteException
     */
    @Override
    public ProfileData getHighlightProfile() throws RemoteException {
        return fragmentEditProfileModel.highlightProfile();
    }

    /**
     * The function refreshEditProfile invokes loadEditProfileUI in the FragmentEditProfile to refresh the list of profiles saved
     * @throws RemoteException
     */
    @Override
    public void refreshEditProfile() throws RemoteException {
        view.loadEditProfileUI();
    }

    /**
     * The deleteProfileSelected is used to invoke the deleteSelectedProfile function in FragmentEditProfileModel to delete the selected profile from database
     */
    @Override
    public void deleteProfileSelected() {
        fragmentEditProfileModel.deleteSelectedProfile();
    }

    /**
     * The getProfileCount function is called to invoke getProfileCountModel to invoke getProfileCountModel function in FragmentEditProfileModel to get the number of profiles saved in the database
     * @return fragmentEdtiProfileModel.getProfileCountModel
     */
    @Override
    public long getProfileCount() {
        return fragmentEditProfileModel.getProfileCountModel();
    }
}
