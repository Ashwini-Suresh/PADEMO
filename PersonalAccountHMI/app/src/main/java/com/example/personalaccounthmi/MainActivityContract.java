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

    }

    interface Presenter {
        ArrayList<ProfileData> getProfileList();
        long getProfileCount();
        void refreshAllProfiles();
    }
    interface View{
        void loadUI();
        void openDialog();
        void refreshAllProfiles();
    }
    interface EditProfilePresenter{
        void editUsername();
        ProfileData getHighlightProfile() throws RemoteException;
        void refreshHighlightProfile() throws RemoteException;
    }
    interface EditProfileModel{
        ProfileData highlightProfile() throws RemoteException;
    }
    interface EditProfileVIew{
        void showHighlightProfile() throws RemoteException;
        void refreshHighlightProfile();
    }
}
