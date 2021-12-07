/**
 * @file FragmentEditProfileModel.java
 * @brief this java class is the presenter class of FragmentAllProfile and communicates between the model and presenter
 * @author Karthika V T
 */
package com.example.personalaccounthmi.model;

import android.content.Context;
import android.os.RemoteException;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.presenter.FragmentEditProfilePresenter;
import com.example.personalaccounthmi.util.IPersonalAccountNotificationObserver;
import com.example.personalaccounthmi.util.PersonalAccountNotificationManager;

public class FragmentEditProfileModel implements MainActivityContract.EditProfileModel, IPersonalAccountNotificationObserver {


    MainActivityInterface mainActivityInterface;
    private FragmentEditProfilePresenter mFragmentEditProfilePresenter;


    public FragmentEditProfileModel(FragmentEditProfilePresenter fragmentEditProfilePresenter, Context context) {
        mainActivityInterface =  MainActivityInterface.getInstance(context);
        mFragmentEditProfilePresenter = fragmentEditProfilePresenter;
        PersonalAccountNotificationManager.getInstance().registerObserver(this);

    }

    @Override
    public ProfileData highlightProfile() throws RemoteException {

        return mainActivityInterface.activeProfile();
    }

    @Override
    public void deleteSelectedProfile() {
        mainActivityInterface.deleteProfile();

    }

    @Override
    public long getProfileCountModel() {
        return mainActivityInterface.getProfileCount();
    }

    @Override
    public void notifyPersonalAccountChange(int propertyType, int data) throws RemoteException {

        switch (propertyType) {
            case 7:
            case 11:
                mFragmentEditProfilePresenter.refreshEditProfile();
                break;
            default:
        }

    }
}
