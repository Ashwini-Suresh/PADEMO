/**
 * @file FragmentEditProfile
 * @brief This is fragment which contains the options to edit profile details
 * @author Karthika V T
 */
package com.example.personalaccounthmi.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.personalaccounthmi.R;

import com.example.personalaccounthmi.dialogfragment.DeletaProfileDialog;
import com.example.personalaccounthmi.dialogfragment.EditAvatarDialog;
import com.example.personalaccounthmi.dialogfragment.EditUsernameDialog;


public class FragmentEditProfile extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View fragmentView = inflater.inflate(R.layout.fragment_editprofile, container, false);


        Button btn_editUsername = fragmentView.findViewById(R.id.btn_editusername);
        Button btn_editAvatar = fragmentView.findViewById(R.id.editavatar);
        Button btn_deleteProfile = fragmentView.findViewById(R.id.deleteprofile);
        Button btn_gotoSettings = fragmentView.findViewById(R.id.settingsbutton);


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

}