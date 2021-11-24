package com.example.personalaccounthmi.dialogfragment;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.personalaccounthmi.R;

public class PersonalAccountDialog extends DialogFragment{


    public void openAvatar(String username) {

        createAvatar avatar = new createAvatar(username);
        avatar.show(getFragmentManager(), "createAvatar");
        // return username;

    }

    public void Dismiss(){
        Fragment precious = getFragmentManager().findFragmentById(R.id.createdialog);
        if (precious != null){
            DialogFragment dialogFragment = (DialogFragment) precious;
            dialogFragment.dismiss();

        }



    }


}

