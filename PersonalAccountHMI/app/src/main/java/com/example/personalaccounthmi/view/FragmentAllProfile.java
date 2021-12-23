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

import com.example.personalaccounthmi.CustomAdapterListener;
import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.dialogfragment.CreateDialog;
import com.example.personalaccounthmi.presenter.FragmentAllProfilePresenter;

import java.util.ArrayList;

/**
 * @brief The class FragmentAllProfile contains the functionalities of the fragment that lists all the profiles in the view
 */
public class FragmentAllProfile extends Fragment implements MainActivityContract.View, CustomAdapterListener {

    /**
     * creating object of FragmentAllProfilePresenter class
     */
    FragmentAllProfilePresenter presenter;

    /**
     * declaring all layout elements
     */
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    public CustomAdapter adapter;
    private ArrayList<ProfileData> mProfileList;
    private  ImageButton create;

    /**
     * creating object of Context
     */
    private Context mContext;
    private long mCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * creating object of view and the view of fragment_allprofile is inflated
         */
        View rootView = inflater.inflate(R.layout.fragment_allprofile, container, false);

        /**
         * finding all UI elements
         */
        mRecyclerView = rootView.findViewById(R.id.recyclarview);
        create = rootView.findViewById(R.id.create);
        mContext = rootView.getContext();

        /**
         * creating object of FragmentAllProfilePresenter
         */
        presenter = new FragmentAllProfilePresenter(this, mContext);
        mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);

        /**
         * refreshAllProfiles function is called in the FragmentAllProfile to get the list of profiles and display it in the view
         */
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            refreshAllProfiles();
        }, 1000);

        /**
         * onClicking create button the dialog fragment for profile creation appears
         */
        create.setOnClickListener(v ->{
                openDialog();
        });
        return rootView;
    }

    /**
     * @vrief loadUI function invokes getProfileList to get the list of profiles saved and getProfileCount to get the number of profiles saved and the avatar image corresponding to the string value is inflated in the recyclerview
     */
    @Override
    public void loadUI() {
        try {
            mProfileList = presenter.getProfileList();
            mCount = presenter.getProfileCount();
        } catch (Exception e) {
            Log.i("Exception", "" + e);
        }
        adapter = new CustomAdapter(mContext, mProfileList,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if(mCount==4){
            create.setEnabled(false);
            create.setVisibility(View.INVISIBLE);
        }else{
            create.setEnabled(true);
            create.setVisibility(View.VISIBLE);
        }
    }

    /**
     * openDialog function is used to create a dialog fragment for create profile functionality
     */
    public void openDialog() {
        CreateDialog dialog = new CreateDialog();
        dialog.show(getFragmentManager(), "CreateDialog");
    }

    /**
     * Function refreshAllProfiles is used to call loadUI function in FragmentAllProfile which returns the list of profiles to the view
     */
    @Override
    public void refreshAllProfiles() {
        loadUI();
    }

    @Override
    public void switchActiveProfile(int id) {
        presenter.switchActiveProfile(id);

    }
}

