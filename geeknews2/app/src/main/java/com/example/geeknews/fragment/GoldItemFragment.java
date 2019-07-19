package com.example.geeknews.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldItemFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;
    private String name;

    @SuppressLint("ValidFragment")
    public GoldItemFragment(String name) {
        this.name = name;
    }

    public GoldItemFragment() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gold_item;
    }

    @Override
    protected void initView(View view) {
        tvTitle.setText(name);
    }
}
