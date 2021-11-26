package com.example.personalaccounthmi.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.dialogfragment.DeletaProfile_Dialog;
import com.example.personalaccounthmi.dialogfragment.EditAvatar_Dialog;
import com.example.personalaccounthmi.dialogfragment.EditUsername_dialog;


public class Fragment_editprofile extends Fragment {

    //declaring layout elements
    private Button btn_editUsername;
    private Button btn_editavatar;
    private Button btn_deleteprofile;
    private Button btn_gotosettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //creating View object to inflate fragment view
        View fragmentview = inflater.inflate(R.layout.fragment_editprofile,container,false);
        btn_editUsername = fragmentview.findViewById(R.id.btn_editusername);
        btn_editavatar = fragmentview.findViewById(R.id.editavatar);
        btn_deleteprofile = fragmentview.findViewById(R.id.deleteprofile);
        btn_gotosettings = fragmentview.findViewById(R.id.settingsbutton);

        //on clicking edit username button dialog fragment opened to edit the username
        btn_editUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditUsernameDialog();
            }
        });

        //on clicking edit avatar button dialog opens to edit the avatar image
        btn_editavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditAvatarDialog();
            }
        });

        //on clicking delete profile button the selected profile will be deleted from the database
        btn_deleteprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openDeleteProfileDialog();}
        });

        //on clicking go to settings button the settings app will be opened
        btn_gotosettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // Inflate the layout for this fragment
        return fragmentview;
    }

    /**
     * @brief The function creates an object of Delete Profile class and show method is called to show the dialog fragment for delete profile
     */
    private void openDeleteProfileDialog() {
        DeletaProfile_Dialog deletaProfile_dialog = new DeletaProfile_Dialog();
        deletaProfile_dialog.show(getFragmentManager(),"DeleteProfile_Dislog");
    }

    /**
     * @brief The function creates an object of EditAvatar class and show method is called to show the dialog fragment
     */
    private void openEditAvatarDialog() {
        EditAvatar_Dialog editAvatar_dialog = new EditAvatar_Dialog();
        editAvatar_dialog.show(getChildFragmentManager(),"EditAvatra_Dialog");
    }

    /**
     * @brief The function creates an object of EditUsername class and show method is called to show the dialog fragment
     */
    private void openEditUsernameDialog() {
        EditUsername_dialog editUsername_dialog = new EditUsername_dialog();
        editUsername_dialog.show(getFragmentManager(),"EditUsername_dialog");
    }

}