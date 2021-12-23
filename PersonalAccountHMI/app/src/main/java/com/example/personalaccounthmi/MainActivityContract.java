/**
 * @file MainActivityContract.java
 * @brief This interface handles all actions related to PersonalAccountHMI application
 * It includes all interfaces in the application
 * @author Karthika V T
 */
package com.example.personalaccounthmi;

import android.content.Context;
import android.os.RemoteException;

import java.util.ArrayList;

public interface MainActivityContract {

    interface Model{
        ArrayList<ProfileData> toGetProfile();
        long getProfileCount();
        void switchActiveProfile(int id);






        }

    interface Presenter {
        ArrayList<ProfileData> getProfileList();
        long getProfileCount();
        void refreshAllProfiles();
        void switchActiveProfile(int id);
    }
    interface View{
        void loadUI();
        void openDialog();
        void refreshAllProfiles();

    }
    interface EditProfilePresenter{
        ProfileData getHighlightProfile() throws RemoteException;
        void refreshEditProfile() throws RemoteException;
        void deleteProfileSelected();
        long getProfileCount();


    }
    interface EditProfileModel{
        ProfileData highlightProfile() throws RemoteException;
        void deleteSelectedProfile();
        long getProfileCountModel();
    }
    interface EditProfileVIew{
        void loadEditProfileUI() throws RemoteException;
        void refreshEditProfile();
        void openDeleteProfileDialog();
    }
}
