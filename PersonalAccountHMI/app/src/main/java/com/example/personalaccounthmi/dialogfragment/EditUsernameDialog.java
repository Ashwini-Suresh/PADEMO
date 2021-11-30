/**
 * @file EditUsernameDialog.java
 * @brief The class class includes the functionalities present in the dialog fragment for editing the username of an existing profile
 * @author Karthika V T
 */
package com.example.personalaccounthmi.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personalaccounthmi.dialogfragment.PersonalAccountDialog;
import com.example.personalaccounthmi.R;

public class EditUsernameDialog extends PersonalAccountDialog {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentview = inflater.inflate(R.layout.editusernamelayout,container,false);


        ImageButton close = fragmentview.findViewById(R.id.imageclose);
        EditText editName = fragmentview.findViewById(R.id.editname);
        Button cancel = fragmentview.findViewById(R.id.cancel);
        Button save = fragmentview.findViewById(R.id.cancel);


        close.setOnClickListener(v -> dismiss());


        cancel.setOnClickListener(v -> dismiss());

        save.setOnClickListener(v -> {

        });

        return fragmentview;
    }
}