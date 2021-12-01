/**
 * @file FragmentAllProfileModel.java
 * @brief This class is model representation of FragmentALlProfile View
 * @author Karthika V T
 */
package com.example.personalaccounthmi.model;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.presenter.FragmentAllProfilePresenter;
import com.example.personalaccounthmi.presenter.MainActivityPresenter;
import com.example.personalaccounthmi.util.IPersonalAccountNotificationObserver;
import com.example.personalaccounthmi.util.PersonalAccountNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentAllProfileModel implements MainActivityContract.Model , IPersonalAccountNotificationObserver {

    private ArrayList<ProfileData> list;




    MainActivityInterface mainActivityInterface;
    private FragmentAllProfilePresenter mfragmentAllProfilePresenter;

    public FragmentAllProfileModel(FragmentAllProfilePresenter fragmentAllProfilePresenter, Context context) {
        mainActivityInterface =  MainActivityInterface.getInstance(context);
        mfragmentAllProfilePresenter = fragmentAllProfilePresenter;
        mainActivityInterface.bindTOAIDLService();
        PersonalAccountNotificationManager.getInstance().registerObserver(this);

    }


    public ArrayList<ProfileData> toGetProfile(){
        try {
            list=(ArrayList<ProfileData>) mainActivityInterface.getAllProfile();
            Log.i("fragment","get");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void notifyPersonalAccountChange(int propertyType, int data) {
        Log.i("fragment","hi"+propertyType);
        switch (propertyType) {
            case 7:
            Log.i("fragment","hi2222"+propertyType);
            mfragmentAllProfilePresenter.refreshAllProfiles();
            default:
        }
    }
}
