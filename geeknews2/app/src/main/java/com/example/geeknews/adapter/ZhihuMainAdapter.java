package com.example.geeknews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZhihuMainAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments ;


    public ZhihuMainAdapter(FragmentManager childFragmentManager, List<Fragment> fragments) {
        super(childFragmentManager);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
