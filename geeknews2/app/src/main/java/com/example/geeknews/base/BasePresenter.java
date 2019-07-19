package com.example.geeknews.base;

import java.util.ArrayList;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    protected ArrayList<BaseModel> list = new ArrayList<>();

    protected M mModel;
    protected V mView;

    public void initModel(M initMvpModel) {
        this.mModel = initMvpModel;
    }

    public void attachView(V initMvpView) {
        this.mView = initMvpView;
    }

    public void destory() {
        if (mView != null) {
            mView = null;
        }

        //切断网络
        if (list.size() > 0 && list != null) {
            for (BaseModel baseModel : list) {
                baseModel.clear();
            }
        }

        if (mModel != null){
            mModel = null;
        }
    }
}
