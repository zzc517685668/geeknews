package com.example.geeknews.presenter;

import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.bean.GankListGirlBean;
import com.example.geeknews.model.GankModel;
import com.example.geeknews.view.GankView;
import com.example.geeknews.view.MyView;

public class GankPresenter extends BasePresenter<GankModel, GankView> {
    public void getGankContent(String key){
        if (mModel != null){
            mModel.getGankList(key, new BaseCallBack<GankBean>() {
                @Override
                public void onSuccess(GankBean gankBean) {
                    if (mView != null){
                        mView.onGankData(gankBean);
                    }
                }

                @Override
                public void onFail(String error) {
                    if (mView != null){
                        mView.onFail(error);
                    }
                }
            });
        }
    }

    public void getGankGirlContent(){
        if (mModel != null){
            mModel.getGankGirlList(new BaseCallBack<GankListGirlBean>() {
                @Override
                public void onSuccess(GankListGirlBean gankListGirlBean) {
                    if (mView != null){
                        mView.onGankGirl(gankListGirlBean);
                    }
                }

                @Override
                public void onFail(String error) {
                    if (mView != null){
                        mView.onFail(error);
                    }
                }
            });
        }
    }
}
