package com.example.personalaccounthmi.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personalaccounthmi.dialogfragment.PersonalAccountDialog;
import com.example.personalaccounthmi.R;

public class EditUsername_dialog extends PersonalAccountDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentview = inflater.inflate(R.layout.editusernamelayout,container,false);

        return fragmentview;
    }
}