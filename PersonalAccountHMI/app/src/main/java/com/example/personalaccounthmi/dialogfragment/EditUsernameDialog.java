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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;

public class EditUsernameDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.editusernamelayout,container,false);


        ImageButton close = fragmentView.findViewById(R.id.imageClose);
        EditText editName = fragmentView.findViewById(R.id.newName);
        Button cancel = fragmentView.findViewById(R.id.cancelUsername);
        Button save = fragmentView.findViewById(R.id.saveUsername);

        MainActivityInterface mainActivityInterface = MainActivityInterface.getInstance(getContext());



        close.setOnClickListener(v -> dismiss());


        cancel.setOnClickListener(v -> dismiss());

        save.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            if (name == null){
                Toast.makeText(getContext(), "ENTER USERNAME", Toast.LENGTH_SHORT).show();
            }else {
                mainActivityInterface.updateProfileName(name);
                dismiss();
                Toast.makeText(getContext(), "PROFILE NAME SAVED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

            }

        });

        return fragmentView;
    }
}