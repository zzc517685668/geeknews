package com.example.geeknews.view;

import com.example.geeknews.base.BaseView;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.bean.GankListGirlBean;

public interface GankView extends BaseView {
    void onGankData(GankBean gankBean);
    void onGankGirl(GankListGirlBean gankListGirlBean);
}
