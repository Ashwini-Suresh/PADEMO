/**
 * @file FragmentAllProfile
 * @brief This class is the main View class which contains the list of all the profiles created
 * @author Karthika V T
 */
package com.example.personalaccounthmi.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.dialogfragment.createDialog;
import com.example.personalaccounthmi.presenter.FragmentAllProfilePresenter;

import java.util.ArrayList;


public class FragmentAllProfile extends Fragment implements MainActivityContract.View {

    FragmentAllProfilePresenter presenter;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public CustomAdapter adapter;
    private ArrayList<ProfileData> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_allprofile, container, false);


        recyclerView = rootView.findViewById(R.id.recyclarview);
        ImageButton create = rootView.findViewById(R.id.create);


        Context context = rootView.getContext();
        presenter = new FragmentAllProfilePresenter(this, context);
        layoutManager = new GridLayoutManager(context, 4);
        recyclerView.setHasFixedSize(true);


        Handler handler = new Handler();
        handler.postDelayed(() -> {
           loadUI(context);
        }, 1000);


        create.setOnClickListener(v -> openDialog());
        return rootView;
    }

    public void loadUI(Context context) {
        try {
            list = presenter.getProfileList();
        } catch (Exception e) {
            Log.i("Exception",""+e);
        }

        adapter = new CustomAdapter(context, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void openDialog() {
        createDialog dialog = new createDialog();
        dialog.show(getFragmentManager(), "CreateDialog");
    }


}

