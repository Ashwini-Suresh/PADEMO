/**
 * @file FragmentEditProfileModel.java
 * @brief this java class is the presenter class of FragmentAllProfile and communicates between the model and presenter
 * @author Karthika V T
 */
package com.example.personalaccounthmi.model;

import android.content.Context;
import android.os.RemoteException;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.BindServiceInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.presenter.FragmentEditProfilePresenter;
import com.example.personalaccounthmi.util.IPersonalAccountNotificationObserver;
import com.example.personalaccounthmi.util.PersonalAccountNotificationManager;

/**
 * The FragmentEditProfileModel class act as an intermediate class between Presenter and BindServiceInterface to communicate
 */
public class FragmentEditProfileModel implements MainActivityContract.EditProfileModel, IPersonalAccountNotificationObserver {

    /**
     * creating object of BindServiceInterface
     */
    BindServiceInterface bindServiceInterface;

    /**
     * creating object of FragmentEditProfilePresenter
     */
    private FragmentEditProfilePresenter mFragmentEditProfilePresenter;

    /**
     * creating constructor of FragmentEditProfileModel and bind the application with service
     * @param fragmentEditProfilePresenter
     * @param context
     */
    public FragmentEditProfileModel(FragmentEditProfilePresenter fragmentEditProfilePresenter, Context context) {

        /**
         * Assigning BindServiceInterface object with instance of BindServiceInterface
         */
        bindServiceInterface =  BindServiceInterface.getInstance(context);

        /**
         * creating object of FragmentEditProfilePresenter
         */
        mFragmentEditProfilePresenter = fragmentEditProfilePresenter;

        /**
         * calling the registerObserver
         */
        PersonalAccountNotificationManager.getInstance().registerObserver(this);

    }

    @Override
    public ProfileData highlightProfile() throws RemoteException {
        return bindServiceInterface.activeProfile();
    }

    @Override
    public void deleteSelectedProfile() {
        bindServiceInterface.deleteProfile();

    }

    @Override
    public long getProfileCountModel() {
        return bindServiceInterface.getProfileCount();
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
