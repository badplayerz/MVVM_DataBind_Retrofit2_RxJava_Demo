package com.zlh.mvvp_databind_retrofit2_rxjava.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdbean-zlh on 16/6/23.
 */
public class ContentViewPagerAdapter extends FragmentPagerAdapter{
    private final List<Fragment> mFragments = new ArrayList<>();

    public ContentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment){
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
