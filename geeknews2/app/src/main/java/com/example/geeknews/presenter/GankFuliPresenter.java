package com.example.geeknews.presenter;

import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.GankListGirlBean;
import com.example.geeknews.model.GankFuliModel;
import com.example.geeknews.view.MyView;

public class GankFuliPresenter extends BasePresenter<GankFuliModel, MyView<GankListGirlBean>>{
    public void getGankFuliContent(){
        if (mModel != null){
            mModel.getGankFuliDataList(new BaseCallBack<GankListGirlBean>() {
                @Override
                public void onSuccess(GankListGirlBean gankListGirlBean) {
                    if (mView != null){
                        mView.onSuccess(gankListGirlBean);
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
