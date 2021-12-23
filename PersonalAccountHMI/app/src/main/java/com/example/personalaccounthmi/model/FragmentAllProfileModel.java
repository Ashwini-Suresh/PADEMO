/**
 * @file FragmentAllProfileModel.java
 * @brief This class is model representation of FragmentALlProfile View
 * @author Karthika V T
 */
package com.example.personalaccounthmi.model;

import android.content.Context;
import android.os.RemoteException;
import android.widget.Toast;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.BindServiceInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.presenter.FragmentAllProfilePresenter;
import com.example.personalaccounthmi.util.IPersonalAccountNotificationObserver;
import com.example.personalaccounthmi.util.PersonalAccountNotificationManager;

import java.util.ArrayList;

/**
 * @brief The class FragmentAllProfileModel class which implements the Model interface of MainActivityContract and IPersonalAccountNotificationObserver acts as a intermediate class for communicating view group with the interface class
 */
public class FragmentAllProfileModel implements MainActivityContract.Model , IPersonalAccountNotificationObserver {

    /**
     * declaring arraylist variable to get the list of profiles saved
     */
    private ArrayList<ProfileData> list;
    BindServiceInterface bindServiceInterface;

    /**
     * creating object of FragmentALlProfilePresenter
     */
    private final FragmentAllProfilePresenter mFragmentAllProfilePresenter;

    /**
     * creating the constructor and service is bind to the application
     * @param fragmentAllProfilePresenter
     * @param context
     */
    public FragmentAllProfileModel(FragmentAllProfilePresenter fragmentAllProfilePresenter, Context context) {

        /**
         * Assigning BindServiceInterface instance in an object of BindServiceInterface class
         */
        bindServiceInterface =  BindServiceInterface.getInstance(context);

        /**
         * creating object of FragmentAllProfilePresenter
         */
        mFragmentAllProfilePresenter = fragmentAllProfilePresenter;

        /**
         * Assigning PersonalAccountNotificationManager instance in an object of PersonalAccountNotificationManager
         */
        PersonalAccountNotificationManager.getInstance().registerObserver(this);

    }

    /**
     * toGetProfile function to called to invoke the getAllProfile function in the BindServiceInterface to display the list of profile in the FragmentAllProfile View
     * @return list of Profile
     */
    public ArrayList<ProfileData> toGetProfile(){
        try {
            list=(ArrayList<ProfileData>) bindServiceInterface.getAllProfile();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @brief The notifyPersonalAccountChange function is called to invoke the refreshProfiles function with the object of FragmentALlProfilePresenter class to get back the updated profile list when either username or avatar of the profile is changed
     * @param propertyType
     * @param data
     */
    @Override
    public void notifyPersonalAccountChange(int propertyType, int data) {
        if (propertyType == 7|| propertyType == 11) {
            mFragmentAllProfilePresenter.refreshAllProfiles();

        }
    }

    /**
     * getProfileCount function is called to invoke the getProfileCount function in BindServiceInterface class to get the number of profiles in the database an
     * @return bindServiceInterface.getProfileCount
     */
    public long getProfileCount() {
        return bindServiceInterface.getProfileCount();
    }

    public void switchActiveProfile(int id) {
        bindServiceInterface.changeActiveProfile(id);
    }
}
