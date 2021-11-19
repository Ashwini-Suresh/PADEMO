package com.example.personalaccounthmi;

import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class PersonalAccoundialog extends DialogFragment {
    public void openAvatar() {
        Toast.makeText(getContext(), "Succesfull", Toast.LENGTH_SHORT).show();
      /*  createAvatar avatar = new createAvatar();
        avatar.show(getFragmentManager(), "createAvatar");*/
    }
}
