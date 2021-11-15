package com.example.personalaccounthmi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class Fragment1 extends Fragment {

    private ImageButton create;
    private Object Fragmentcreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_1, container , false);
        create = rootview.findViewById(R.id.create);

        // Inflate the layout for this fragment
        return rootview;
    }




}