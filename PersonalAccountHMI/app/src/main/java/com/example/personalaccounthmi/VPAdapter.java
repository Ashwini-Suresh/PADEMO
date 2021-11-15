package com.example.personalaccounthmi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter{
    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmenttitle = new ArrayList<>();

    public VPAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    //This method gives fragment with respect to the position
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    //This method gives total number of fragment present
    public int getCount() {
        return fragmentArrayList.size();
    }

    public <title> void addFragment(Fragment fragment , String title) {
        fragmentArrayList.add(fragment);
        fragmenttitle.add(title);

    }

    @Nullable
    @Override
    //this method gives title of the fragment
    public CharSequence getPageTitle(int position) {
        return fragmenttitle.get(position);
    }
}
