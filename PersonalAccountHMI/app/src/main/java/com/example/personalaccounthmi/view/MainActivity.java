/**
 * @file MainActivity.java
 * @brief This is Main activity class of the application. It handles the model class and the presenter class of the application
 * @author Karthika V T
 */
package com.example.personalaccounthmi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.util.VPAdapter;
import com.google.android.material.tabs.TabLayout;



public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);


        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new FragmentAllProfile(),"ALL PROFILES");
        vpAdapter.addFragment(new FragmentEditProfile(),"EDIT PROFILES");
        viewPager.setAdapter(vpAdapter);

    }


}