package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ZhiHuAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<Fragment> list;
    private ArrayList<String> titles;

    public ZhiHuAdapter(FragmentManager fm, Context context, ArrayList<Fragment> list, ArrayList<String> titles) {
        super(fm);
        this.context = context;
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
