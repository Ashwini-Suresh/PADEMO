package com.example.personalaccounthmitrial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class Fragment_allProfile extends Fragment {

    private ImageButton create;
    private TextView name;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_all_profile, container , false);
        create = rootview.findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_profile, container, false);
    }
    private void openDialog() {
        createDialog dialog = new createDialog();
        dialog.show(getParentFragment(),"CreateDialog");
    }

}