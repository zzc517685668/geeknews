package com.example.geeknews.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geeknews.GoldDetailActivity;
import com.example.geeknews.R;
import com.example.geeknews.adapter.GoldAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.GoldTabBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GoldFragment extends BaseFragment {

    @BindView(R.id.ic_menu)
    ImageView icMenu;
    @BindView(R.id.gold_tab)
    TabLayout goldTab;
    @BindView(R.id.gold_vp)
    ViewPager goldVp;

    private String[] mTypes = new String[]{"All", "Android", "IOS", "休息视频", "扩展资源", "瞎推荐", "前端"};

    private ArrayList<GoldTabBean> list;
    private ArrayList<String> mTitles;
    private ArrayList<GoldItemFragment> mFragments;
    private GoldAdapter goldAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView(View view) {
        list = new ArrayList<>();
        for (int i = 0; i < mTypes.length; i++) {
            String type = mTypes[i];
            if (i % 3 == 0) {
                list.add(new GoldTabBean(type, true));
            } else {
                list.add(new GoldTabBean(type, false));
            }
        }

        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
    }

    @Override
    protected void initData() {
        for (int i = 0; i < list.size(); i++) {
            GoldTabBean goldTabBean = list.get(i);
            if (goldTabBean.isSelected()) {
                mFragments.add(new GoldItemFragment(goldTabBean.getTitles()));
                mTitles.add(goldTabBean.getTitles());
            }
        }

        goldAdapter = new GoldAdapter(getChildFragmentManager(), mTitles, mFragments);
        goldVp.setAdapter(goldAdapter);
        goldTab.setupWithViewPager(goldVp);
    }

    @Override
    protected void initLastener() {
        icMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoldDetailActivity.class);
                intent.putExtra("list", list);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            list = (ArrayList<GoldTabBean>) data.getSerializableExtra("list");
            mFragments.clear();
            mTitles.clear();
            initData();
        }
    }
}
