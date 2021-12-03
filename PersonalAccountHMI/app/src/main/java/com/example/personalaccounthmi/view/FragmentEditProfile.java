/**
 * @file FragmentEditProfile
 * @brief This is fragment which contains the options to edit profile details
 * @author Karthika V T
 */
package com.example.personalaccounthmi.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;

import com.example.personalaccounthmi.dialogfragment.DeletaProfileDialog;
import com.example.personalaccounthmi.dialogfragment.EditAvatarDialog;
import com.example.personalaccounthmi.dialogfragment.EditUsernameDialog;
import com.example.personalaccounthmi.presenter.FragmentEditProfilePresenter;


public class FragmentEditProfile extends Fragment implements MainActivityContract.EditProfileVIew {
    FragmentEditProfilePresenter fragmentEditProfilePresenter;
    private Context mContext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View fragmentView = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mContext = fragmentView.getContext();

        fragmentEditProfilePresenter = new FragmentEditProfilePresenter(this,mContext);


        Button btn_editUsername = fragmentView.findViewById(R.id.btn_editusername);
        Button btn_editAvatar = fragmentView.findViewById(R.id.editavatar);
        Button btn_deleteProfile = fragmentView.findViewById(R.id.deleteprofile);
        Button btn_gotoSettings = fragmentView.findViewById(R.id.settingsbutton);
        ImageView highlightImage = fragmentView.findViewById(R.id.highlightAvatar);
        TextView highlightText = fragmentView.findViewById(R.id.highlightName);
        refreshHighlightProfile();

        btn_editUsername.setOnClickListener(v -> openEditUsernameDialog());

        btn_editAvatar.setOnClickListener(v -> openEditAvatarDialog());

        btn_deleteProfile.setOnClickListener(v -> openDeleteProfileDialog());


        btn_gotoSettings.setOnClickListener(v -> {
            Intent i;
            PackageManager manager = getActivity().getPackageManager();
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

    private void openDeleteProfileDialog() {
        DeletaProfileDialog deleteProfile_dialog = new DeletaProfileDialog();
        deleteProfile_dialog.show(getFragmentManager(), "DeleteProfile_Dialog");
    }


    private void openEditAvatarDialog() {
        EditAvatarDialog editAvatar_dialog = new EditAvatarDialog();
        editAvatar_dialog.show(getChildFragmentManager(), "EditAvatar_Dialog");
    }

    private void openEditUsernameDialog() {
        EditUsernameDialog editUsername_dialog = new EditUsernameDialog();
        editUsername_dialog.show(getFragmentManager(), "EditUsername_dialog");
    }

    @Override
    public void showHighlightProfile() throws RemoteException {

         ProfileData profileData = fragmentEditProfilePresenter.getHighlightProfile();
         String name= profileData.getName();
         String avatar = profileData.getAvatar();
        Log.i("get","highlight "+name+" "+avatar);
    }

    @Override
    public void refreshHighlightProfile() {
        try {
            showHighlightProfile();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}