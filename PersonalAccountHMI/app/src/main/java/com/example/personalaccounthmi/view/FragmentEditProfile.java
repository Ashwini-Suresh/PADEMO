/**
 * @file FragmentEditProfile
 * @brief This is fragment which contains the options to edit profile details
 * @author Karthika V T
 */
package com.example.personalaccounthmi.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;

import com.example.personalaccounthmi.dialogfragment.EditAvatarDialog;
import com.example.personalaccounthmi.dialogfragment.EditUsernameDialog;
import com.example.personalaccounthmi.presenter.FragmentEditProfilePresenter;

/**
 * @brief Class FragmentEditProfile includes the functionalities of the Edit Profile tab that edit the profile details and delete the profile. The class implements the Interface MainActivityContract.EditProfileView
 */
public class FragmentEditProfile extends Fragment implements MainActivityContract.EditProfileVIew {

    /**
     * creating object of FragmentEditProfilePresenter class
     */
    FragmentEditProfilePresenter mFragmentEditProfilePresenter;

    /**
     * declaring all layout elements
     */
    ImageView highlightImage;
    TextView highlightText;
    Button btn_deleteProfile;
    private long mCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * creating object of View class and inflate fragment_editprofile to view
         */
        View fragmentView = inflater.inflate(R.layout.fragment_editprofile, container, false);

        /**
         * creating object of Context
         */
        Context mContext = fragmentView.getContext();

        /**
         * creating object of FragmentEditProfilePresenter
         */
        mFragmentEditProfilePresenter = new FragmentEditProfilePresenter(this, mContext);

        /**
         * finding all UI elements
         */
        Button btn_editUsername = fragmentView.findViewById(R.id.btn_editusername);
        Button btn_editAvatar = fragmentView.findViewById(R.id.editavatar);
        btn_deleteProfile = fragmentView.findViewById(R.id.deleteprofile);
        Button btn_gotoSettings = fragmentView.findViewById(R.id.settingsbutton);
        highlightImage = fragmentView.findViewById(R.id.highlightAvatar);
        highlightText = fragmentView.findViewById(R.id.highlightName);

        /**
         * loadEditProfileUI function is called to get the profile selected in the view
         */
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    loadEditProfileUI();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        },1000);

        /**
         * onClicking edit username button the dialog fragment for editing the username tab opens
         */
        btn_editUsername.setOnClickListener(v -> openEditUsernameDialog());

        /**
         *  onClicking edit avatar button the dialog fragment for editing the avatar tab opens
         */
        btn_editAvatar.setOnClickListener(v -> openEditAvatarDialog());

        /**
         * onClicking the delete profile button the alert dialog to delete the profile opens
         */
        btn_deleteProfile.setOnClickListener(v -> openDeleteProfileDialog());

        /**
         * onClicking GO TO SETTINGS button the application navigates to Settings application
         */
        btn_gotoSettings.setOnClickListener(v -> {
            Intent i;

            /**
             * creating object of PackageManager, the class that provides the information of applications in the device
             */
            PackageManager manager = requireActivity().getPackageManager();
            try {
                i = manager.getLaunchIntentForPackage("com.example.settingsapplication");
                if (i== null)
                    throw new PackageManager.NameNotFoundException();
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(i);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        });

        return fragmentView;
    }

    /**
     * Function openDeleteDialog is used to open an alert dialog to delete the selected profile
     */
    public void openDeleteProfileDialog() {

        /**
         * creating object of the Dialog class
         */
        final Dialog dialog = new Dialog(getContext());

        /**
         * setting the view of deleteprofilelayout to view
         */
        dialog.setContentView(R.layout.deleteprofilelayout);

        /**
         * finding UI elements
         */
        Button deleteYes = (Button) dialog.findViewById(R.id.delete_yes);
        Button deleteNO = (Button) dialog.findViewById(R.id.delete_no);

        /**
         * onCLicking NO button the alert dialog dismisses
         */
        deleteNO.setOnClickListener(v -> dialog.dismiss());

        /**
         * onClicking YES button function deleteProfileSelected is called by the FragmentEditProfilePresenter to delete profile
         */
        deleteYes.setOnClickListener(v -> {
            mFragmentEditProfilePresenter.deleteProfileSelected();
            dialog.dismiss();
            Toast.makeText(getContext(), "PROFILE DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        });
        dialog.show();
    }

    /**
     * Function openEditAvatarDialog is called to open the dialog fragment to edit the avatar of the selected profile
     */
    private void openEditAvatarDialog() {
        EditAvatarDialog editAvatar_dialog = new EditAvatarDialog();
        editAvatar_dialog.show(getChildFragmentManager(), "EditAvatar_Dialog");
    }

    /**
     * Function openEditUsernameDialog is called to open the dialog fragment to edit the user name of the selected profile
     */
    private void openEditUsernameDialog() {
        EditUsernameDialog editUsername_dialog = new EditUsernameDialog();
        editUsername_dialog.show(getFragmentManager(), "EditUsername_dialog");
    }

    /**
     * Function loadEditProfileUI is called get the username and avatar image, and display on the cardview
     * @throws RemoteException
     */
    @Override
    public void loadEditProfileUI() throws RemoteException {
        ProfileData profileData = mFragmentEditProfilePresenter.getHighlightProfile();
        String name= profileData.getName();
        String avatar = profileData.getAvatar();
        mCount = mFragmentEditProfilePresenter.getProfileCount();

        highlightText.setText(name);

        /**
         * image view in the card will be inflated with avatar image corresponding to the string value of the avatar from the database
         */
        switch (avatar){
            case "avatar1" : highlightImage.setImageResource(R.mipmap.avatar1);
                break;
            case "avatar2" : highlightImage.setImageResource(R.mipmap.avatar2);
                break;
            case "avatar3" : highlightImage.setImageResource(R.mipmap.avatar3);
                break;
            case "avatar4" : highlightImage.setImageResource(R.mipmap.avatar4);
                break;
            case "avatar5" : highlightImage.setImageResource(R.mipmap.avatar5);
                break;
            case "avatar6" : highlightImage.setImageResource(R.mipmap.avatar6);
                break;
            case "avatar7" : highlightImage.setImageResource(R.mipmap.avatar7);
                break;
            case "avatar8" : highlightImage.setImageResource(R.mipmap.avatar8);
                break;
        }

        /**
         * if the number of profiles in the database is one the delete button will be disabled
         */
        if ( mCount == 1){
            btn_deleteProfile.setEnabled(false);
        }else {
            btn_deleteProfile.setEnabled(true);
        }
    }

    @Override
    public void refreshEditProfile() {
        try {
            loadEditProfileUI();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}