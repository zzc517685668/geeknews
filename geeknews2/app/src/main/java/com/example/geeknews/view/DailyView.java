package com.example.geeknews.view;


import com.example.geeknews.base.BaseView;
import com.example.geeknews.bean.DailyBeforeBean;

public interface DailyView<T> extends BaseView {
    void onSuccess(T t);
    void getBeforeData(DailyBeforeBean dailyBeforeBean);
}
