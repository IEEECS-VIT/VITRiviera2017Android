package com.ieeecsvit.riviera17android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Akanshi on 10/17/2016.
 */

public class EventPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    //Constructor to the class
    public EventPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new DescriptionFragment();
            case 1:
                return new EventCoordinatorFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
