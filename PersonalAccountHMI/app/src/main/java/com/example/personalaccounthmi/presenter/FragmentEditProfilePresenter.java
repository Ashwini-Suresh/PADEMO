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
import com.example.personalaccounthmi.model.FragmentAllProfileModel;
import com.example.personalaccounthmi.model.FragmentEditProfileModel;

public class FragmentEditProfilePresenter implements MainActivityContract.EditProfilePresenter {

    MainActivityContract.EditProfileVIew view;
    FragmentEditProfileModel fragmentEditProfileModel;


    public FragmentEditProfilePresenter(MainActivityContract.EditProfileVIew view, Context context) {
        this.view = view;
        fragmentEditProfileModel = new FragmentEditProfileModel( this,context);

    }


    @Override
    public void editUsername() {

    }

    @Override
    public ProfileData getHighlightProfile() throws RemoteException {
        return fragmentEditProfileModel.highlightProfile();
    }

    @Override
    public void refreshHighlightProfile() throws RemoteException {
        view.showHighlightProfile();


    }
}
