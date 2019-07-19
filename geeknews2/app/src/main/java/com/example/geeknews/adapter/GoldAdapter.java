package com.example.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geeknews.fragment.GoldItemFragment;

import java.util.ArrayList;

public class GoldAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> mTitle;
    private ArrayList<GoldItemFragment> mFragments;

    public GoldAdapter(FragmentManager fm, ArrayList<String> mTitle, ArrayList<GoldItemFragment> mFragments) {
        super(fm);
        this.mTitle = mTitle;
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
