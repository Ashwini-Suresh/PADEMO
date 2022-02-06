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
import android.view.Window;
import android.view.WindowManager;

import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.util.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

/**
 * MainActivity class includes the viewpager to populate the view of fragments
 */
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        /**
         * finding UI elements
         */
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new FragmentAllProfile(),"ALL PROFILES");
        viewPagerAdapter.addFragment(new FragmentEditProfile(),"EDIT PROFILES");
        viewPager.setAdapter(viewPagerAdapter);

    }


}