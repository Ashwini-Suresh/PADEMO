/**
 * @file CreateProfileDialog
 * @brief The java class which contains the functionalities of the dialog fragment for creating a profile
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

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;

public class createDialog extends PersonalAccountDialog {


    private EditText name;
    private String avatar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View rootView = inflater.inflate(R.layout.createdialog, container , false);


        ImageButton close = rootView.findViewById(R.id.closebutton);
        Button cancel = rootView.findViewById(R.id.cancelbtn);
        Button save = rootView.findViewById(R.id.savebtn);
        name = rootView.findViewById(R.id.Name);



        MainActivityInterface mainActivityInterface = MainActivityInterface.getInstance(getContext());




        close.setOnClickListener(v -> dismiss());


        cancel.setOnClickListener(v -> dismiss());




        save.setOnClickListener(v -> {
            String username = String.valueOf(name.getText());



            mainActivityInterface.addProfile(username , avatar);
            Toast.makeText(getContext(), "Profile Saved successfully", Toast.LENGTH_SHORT).show();
            dismiss();


        });

        return rootView;
    }

//    public void show(Fragment parentFragment, String createDialog) {
//    }
}
