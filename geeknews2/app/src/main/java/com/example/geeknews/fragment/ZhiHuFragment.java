package com.example.geeknews.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapter.ZhiHuAdapter;
import com.example.geeknews.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHuFragment extends BaseFragment {

    @BindView(R.id.zhihu_tab)
    TabLayout zhihuTab;
    @BindView(R.id.zhizhu_vp)
    ViewPager zhizhuVp;
    Unbinder unbinder;
    private DailyFragment dailyFragment;
    private ColumnFragment columnFragment;
    private HotFragment hotFragment;
    private ArrayList<Fragment> list;
    private ArrayList<String> titles;
    private ZhiHuAdapter zhiHuAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_zhi_hu;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        //创建所需fragment实例
        dailyFragment = new DailyFragment();
        columnFragment = new ColumnFragment();
        hotFragment = new HotFragment();

        //创建一个fragment集合和一个tab标题集合
        list = new ArrayList<>();
        titles = new ArrayList<>();
        list.add(dailyFragment);
        list.add(columnFragment);
        list.add(hotFragment);
        titles.add("日报");
        titles.add("专栏");
        titles.add("热门");
        //创建适配器绑定所需集合，childfragmentManager之所以用这个是因为这是在fragment嵌套下又一层嵌套
        zhiHuAdapter = new ZhiHuAdapter(getChildFragmentManager(), getContext(), list, titles);
        zhizhuVp.setAdapter(zhiHuAdapter);
        zhihuTab.setupWithViewPager(zhizhuVp);
        zhizhuVp.setOffscreenPageLimit(3);
    }
}
