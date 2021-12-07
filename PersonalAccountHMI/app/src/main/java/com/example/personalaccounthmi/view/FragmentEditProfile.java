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
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;

import com.example.personalaccounthmi.dialogfragment.EditAvatarDialog;
import com.example.personalaccounthmi.dialogfragment.EditUsernameDialog;
import com.example.personalaccounthmi.presenter.FragmentEditProfilePresenter;



public class FragmentEditProfile extends Fragment implements MainActivityContract.EditProfileVIew {
    FragmentEditProfilePresenter mFragmentEditProfilePresenter;
    ImageView highlightImage;
    TextView highlightText;
    private long mCount;
    Button btn_deleteProfile;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View fragmentView = inflater.inflate(R.layout.fragment_editprofile, container, false);
        Context mContext = fragmentView.getContext();

        mFragmentEditProfilePresenter = new FragmentEditProfilePresenter(this, mContext);
        Button btn_editUsername = fragmentView.findViewById(R.id.btn_editusername);
        Button btn_editAvatar = fragmentView.findViewById(R.id.editavatar);
        btn_deleteProfile = fragmentView.findViewById(R.id.deleteprofile);
        Button btn_gotoSettings = fragmentView.findViewById(R.id.settingsbutton);
        highlightImage = fragmentView.findViewById(R.id.highlightAvatar);
        highlightText = fragmentView.findViewById(R.id.highlightName);


        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    loadEditProfileUI();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                //refreshEditProfile();

            }
        },1000);



        btn_editUsername.setOnClickListener(v -> openEditUsernameDialog());

        btn_editAvatar.setOnClickListener(v -> openEditAvatarDialog());

        btn_deleteProfile.setOnClickListener(v -> openDeleteProfileDialog());


        btn_gotoSettings.setOnClickListener(v -> {
            Intent i;
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

    public void openDeleteProfileDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.deleteprofilelayout);
        Button deleteYes = (Button) dialog.findViewById(R.id.delete_yes);
        Button deleteNO = (Button) dialog.findViewById(R.id.delete_no);
        deleteNO.setOnClickListener(v -> dialog.dismiss());
        deleteYes.setOnClickListener(v -> {
            mFragmentEditProfilePresenter.deleteProfileSelected();
            dialog.dismiss();
            Toast.makeText(getContext(), "PROFILE DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        });
        dialog.show();
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
    public void loadEditProfileUI() throws RemoteException {
        ProfileData profileData = mFragmentEditProfilePresenter.getHighlightProfile();
        String name= profileData.getName();
        String avatar = profileData.getAvatar();
        mCount = mFragmentEditProfilePresenter.getProfileCount();

        highlightText.setText(name);
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