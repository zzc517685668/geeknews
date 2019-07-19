package com.example.geeknews.base;

public interface BaseCallBack<T> {
    void onSuccess(T t);
    void onFail(String error);
}
