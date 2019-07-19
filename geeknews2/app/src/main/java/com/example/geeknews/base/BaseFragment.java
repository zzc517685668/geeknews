package com.example.geeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        //初始化控件
        unbinder = ButterKnife.bind(this, view);
        //mvp
        initMvp();
        //初始化数据
        initView(view);
        //加载数据
        initData();
        //监听
        initLastener();
        return view;
    }

    protected abstract int getLayoutID();

    protected void initLastener() {
    }

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void initMvp() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
