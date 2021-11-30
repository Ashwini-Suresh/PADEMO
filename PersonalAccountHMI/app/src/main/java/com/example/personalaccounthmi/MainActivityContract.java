/**
 * @file MainActivityContract.java
 * @brief This interface handles all actions related to PersonalAccountHMI application
 * It includes all interfaces in the application
 * @author Karthika V T
 */
package com.example.personalaccounthmi;

import android.content.Context;

import java.util.ArrayList;

public interface MainActivityContract {

    interface Model{

    }

    interface Presenter {
        ArrayList<ProfileData> getProfileList();


    }
    interface View{
        void loadUI(Context context);
        void openDialog();


        }
    interface EditProfilePresenter{
        void editUsername();
    }
}
