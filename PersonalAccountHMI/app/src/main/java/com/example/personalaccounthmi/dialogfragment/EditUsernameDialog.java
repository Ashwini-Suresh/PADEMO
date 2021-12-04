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
import androidx.fragment.app.DialogFragment;

import com.example.personalaccounthmi.R;

public class EditUsernameDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.editusernamelayout,container,false);


        ImageButton close = fragmentView.findViewById(R.id.imageclose);
        EditText editName = fragmentView.findViewById(R.id.editname);
        Button cancel = fragmentView.findViewById(R.id.cancel);
        Button save = fragmentView.findViewById(R.id.cancel);


        close.setOnClickListener(v -> dismiss());


        cancel.setOnClickListener(v -> dismiss());

        save.setOnClickListener(v -> {

        });

        return fragmentView;
    }
}