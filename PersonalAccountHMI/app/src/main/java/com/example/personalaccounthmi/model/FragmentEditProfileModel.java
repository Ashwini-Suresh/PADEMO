/**
 * @file FragmentEditProfileModel.java
 * @brief this java class is the presenter class of FragmentAllProfile and communicates between the model and presenter
 * @author Karthika V T
 */
package com.example.personalaccounthmi.model;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

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
    public void notifyPersonalAccountChange(int propertyType, int data) throws RemoteException {
        Log.i("fragment","hi"+propertyType);
        switch (propertyType) {
            case 7:
            case 11:
                Log.i("fragment","hi2222"+propertyType);
                mFragmentEditProfilePresenter.refreshHighlightProfile();
                break;


            default:
        }

    }
}
