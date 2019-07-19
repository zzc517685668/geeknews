package com.example.geeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化视图
        setContentView(getLayoutID());
        //初始化控件
        ButterKnife.bind(this);
        //mvp
        initMvp();
        //初始化数据
        initView();
        //加载数据
        initData();
        //监听
        initLastener();
    }

    protected void initLastener() {
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void initMvp() {
    }

    protected abstract int getLayoutID();
}
