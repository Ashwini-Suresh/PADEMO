/**
 * @file FragmentAllProfile
 * @brief This class is the main View class which contains the mProfileList of all the profiles created
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.dialogfragment.createDialog;
import com.example.personalaccounthmi.presenter.FragmentAllProfilePresenter;

import java.util.ArrayList;


public class FragmentAllProfile extends Fragment implements MainActivityContract.View {

    FragmentAllProfilePresenter presenter;


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    public CustomAdapter adapter;
    private ArrayList<ProfileData> mProfileList;
    private Context mContext;
    private int mCount =1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_allprofile, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclarview);
        ImageButton create = rootView.findViewById(R.id.create);
        mContext = rootView.getContext();
        presenter = new FragmentAllProfilePresenter(this, mContext);
        mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        Handler handler = new Handler();
        handler.postDelayed(this::loadUI, 200);
        create.setOnClickListener(v ->{
            if(mCount ==4){
                Toast.makeText(getContext(), "MAXIMUM PROFILE REACHED", Toast.LENGTH_SHORT).show();
            }
            else {
                mCount++;
                openDialog();
            }
        });
        return rootView;
    }

    @Override
    public void loadUI() {

        try {
            mProfileList = presenter.getProfileList();
        } catch (Exception e) {
            Log.i("Exception", "" + e);
        }
        adapter = new CustomAdapter(mContext, mProfileList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void openDialog() {
        createDialog dialog = new createDialog();
        dialog.show(getFragmentManager(), "CreateDialog");
    }

    @Override
    public void refreshAllProfiles() {
        loadUI();
    }


}

