/**
 * @file ViewPagerAdapter.java
 * @brief The class which inflates the views of the two base fragments, fragment all profile and fragment edit profile
 * @author Karthika V T
 */
package com.example.personalaccounthmi.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * The view pager adapter class is used to inflate the views of the fragment
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * declaring object of the Fragment arraylist
     */
    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    /**
     * declaring object of fragment title list
     */
    private final ArrayList<String> fragmentTitle = new ArrayList<>();

    /**
     * creating constructor of the class
     * @param fm
     * @param behavior
     */
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    /**
     * getItem function returns the fragment at the position index
     * @param position
     * @return fragmentArrayList.get(position)
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    /**
     * getCount function is called to get the number of fragment
     * @return fragmentArrayList.size()
     */
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    /**
     * function is used to populate the fragment and fragment title list
     * @param fragment
     * @param title
     */
    public void addFragment(Fragment fragment , String title) {
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);

    }

    /**
     * function returns title of the page at index position
     * @param position
     * @return fragmentTitle.get(position)
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }
}
