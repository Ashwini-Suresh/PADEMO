package com.example.personalaccounthmi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.presenter.MainActivityPresenter;
import com.example.personalaccounthmi.util.VPAdapter;
import com.example.personalaccounthmi.view.Fragment_allProfile;
import com.example.personalaccounthmi.view.Fragment_editprofile;
import com.google.android.material.tabs.TabLayout;

import Common.IMyAidlInterface;

/**
 * @author Karthika V T
 */

public class MainActivity extends AppCompatActivity implements MainActivityInterface.View {

    //creating presenter objects
    MainActivityInterface.Presenter presenter;

    //declaring layout elements
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private IMyAidlInterface mCommon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating presenter object
        presenter = new MainActivityPresenter(this);

        //get reference of tab layout and adapter class
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);


        //creating object of adapter class.
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Fragment_allProfile(),"ALL PROFILES");
        vpAdapter.addFragment(new Fragment_editprofile(),"EDIT PROFILES");
        viewPager.setAdapter(vpAdapter);

    }


}