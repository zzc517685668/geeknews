package com.example.geeknews.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseModel {
    //初始化统一管理容器
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    //切换，清空容器
    public void clear(){
        compositeDisposable.clear();
    }
}
