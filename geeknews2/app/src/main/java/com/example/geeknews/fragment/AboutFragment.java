package com.example.geeknews.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.AboutAdapter;
import com.example.geeknews.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {

    @BindView(R.id.iv_about)
    ImageView ivAbout;
    @BindView(R.id.iv_tech_origin)
    ImageView ivTechOrigin;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.about_recycler)
    RecyclerView aboutRecycler;
    Unbinder unbinder;
    private ArrayList<Integer> list;
    private AboutAdapter aboutAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvName.setText("作者：张振川");
        list = new ArrayList<>();
        list.add(R.string.one);
        list.add(R.string.two);
        list.add(R.string.three);
        list.add(R.string.four);
        list.add(R.string.five);
        aboutAdapter = new AboutAdapter(getContext(), list);
        aboutRecycler.setAdapter(aboutAdapter);
        aboutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
