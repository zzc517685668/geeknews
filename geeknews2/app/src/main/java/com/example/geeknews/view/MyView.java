package com.example.geeknews.view;

import com.example.geeknews.base.BaseView;

public interface MyView<T> extends BaseView {
    void onSuccess(T t);
}
