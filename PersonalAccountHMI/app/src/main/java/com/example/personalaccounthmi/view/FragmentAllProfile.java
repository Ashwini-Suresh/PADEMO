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
    private long mCount;
    private  ImageButton create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_allprofile, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclarview);
        create = rootView.findViewById(R.id.create);
        mContext = rootView.getContext();
        presenter = new FragmentAllProfilePresenter(this, mContext);
        mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            loadUI();
        }, 1000);
        create.setOnClickListener(v ->{
                openDialog();
        });
        return rootView;
    }

    @Override
    public void loadUI() {

        try {
            mProfileList = presenter.getProfileList();
            mCount = presenter.getProfileCount();
        } catch (Exception e) {
            Log.i("Exception", "" + e);
        }
        adapter = new CustomAdapter(mContext, mProfileList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if(mCount==4){
            create.setEnabled(false);
        }else{
            create.setEnabled(true);
        }
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

