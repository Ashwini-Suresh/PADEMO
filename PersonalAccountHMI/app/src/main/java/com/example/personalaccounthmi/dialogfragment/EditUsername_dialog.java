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

public class EditUsername_dialog extends PersonalAccountDialog {

    private ImageButton close;
    private EditText editName;
    private Button save;
    private Button cancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentview = inflater.inflate(R.layout.editusernamelayout,container,false);
        close = fragmentview.findViewById(R.id.imageclose);
        editName = fragmentview.findViewById(R.id.editname);
        cancel = fragmentview.findViewById(R.id.cancel);
        save = fragmentview.findViewById(R.id.cancel);

        //on clicking close button the dialog fragmrnt will be closed
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //on clicking cancel button the dialog fragment wil be closed
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //on clicking save button the profile name will be changed to the new profile that is entered by the user
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return fragmentview;
    }
}