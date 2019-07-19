package com.example.geeknews.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.geeknews.R;
import com.example.geeknews.adapter.ZhiHuAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;

public class GankFragment extends BaseFragment {

    @BindView(R.id.gank_tab)
    TabLayout gankTab;
    @BindView(R.id.gank_vp)
    ViewPager gankVp;
    public static ArrayList<String> tabTitles = new ArrayList<>();
    private ArrayList<Fragment> fragments;
    private static final String TAG = "GankFragment";
    private ZhiHuAdapter zhiHuAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView(View view) {
        tabTitles.add("Android");
        tabTitles.add("IOS");
        tabTitles.add("前端");
        tabTitles.add("福利");
        fragments = new ArrayList<>();

        initBroadcastReceiver();
    }

    @Override
    protected void initData() {
        //android 传送数据
        GankCommonFragment android = new GankCommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitles.get(0));
        android.setArguments(bundle);

        //ios 传送数据
        GankCommonFragment ios = new GankCommonFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitles.get(1));
        ios.setArguments(bundle);

        //前端 传送数据
        GankCommonFragment web = new GankCommonFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitles.get(2));
        web.setArguments(bundle);

        //福利 传送数据
        GankFuliFragment fuli = new GankFuliFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitles.get(3));
        fuli.setArguments(bundle);

        fragments.add(android);
        fragments.add(ios);
        fragments.add(web);
        fragments.add(fuli);

        zhiHuAdapter = new ZhiHuAdapter(getChildFragmentManager(), getContext(), fragments, tabTitles);
        gankVp.setAdapter(zhiHuAdapter);

        gankTab.addTab(gankTab.newTab().setText(tabTitles.get(0)));
        gankTab.addTab(gankTab.newTab().setText(tabTitles.get(1)));
        gankTab.addTab(gankTab.newTab().setText(tabTitles.get(2)));
        gankTab.addTab(gankTab.newTab().setText(tabTitles.get(3)));
        gankTab.getTabAt(0).setText(tabTitles.get(0));
        gankTab.getTabAt(1).setText(tabTitles.get(1));
        gankTab.getTabAt(2).setText(tabTitles.get(2));
        gankTab.getTabAt(3).setText(tabTitles.get(3));
        gankTab.setupWithViewPager(gankVp);
        gankVp.setOffscreenPageLimit(4);
    }

    private void initBroadcastReceiver() {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(gankBroadCastReceiver, new IntentFilter("com.gank.search"));
    }

    BroadcastReceiver gankBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "onReceive: " + intent.getStringExtra("data"));
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "unregisterReceiver: ");
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(gankBroadCastReceiver);
    }
}
