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
    private final FragmentEditProfilePresenter mFragmentEditProfilePresenter;

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

    /**
     * function highlightProfile is called to invoke the activeProfile in bindServiceInterface to highlight the profile selected
     * @return
     * @throws RemoteException
     */
    @Override
    public ProfileData highlightProfile() throws RemoteException {
        return bindServiceInterface.activeProfile();
    }

    /**
     * function is called to invoke the deleteProfile in BindServiceInterface to delete the selected profile
     */
    @Override
    public void deleteSelectedProfile() {
        bindServiceInterface.deleteProfile();

    }

    /**
     * function is called to invoke the getProfileCount function in the BindserviceInterface to get the number of profiles saved
     * @return the number of profiles saved
     */
    @Override
    public long getProfileCountModel() {
        return bindServiceInterface.getProfileCount();
    }

    /**
     * function is called to change the profile viewed in the card to the selected profile
     * @param propertyType
     * @param data
     * @throws RemoteException
     */
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
