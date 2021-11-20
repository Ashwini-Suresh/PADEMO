package com.example.personalaccounthmitrial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class createDialog extends PersonalAcccountDialog{
    private ImageButton close;
    private Button cancel, next;
    private EditText name;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootview = inflater.inflate(R.layout.createdialog, container , false);
        close = rootview.findViewById(R.id.closebutton);
        cancel = rootview.findViewById(R.id.cancelbtn);
        next = rootview.findViewById(R.id.nextbtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAvatar();

            }
        });

        return rootview;
    }
    public void show(Fragment parentFragment, String createDialog) {
    }
}
