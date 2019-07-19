package com.example.geeknews.presenter;

import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.model.HotModel;
import com.example.geeknews.view.MyView;

public class HotPresenter extends BasePresenter<HotModel, MyView<HotBean>> {
    public void getHotContent(){
        if (mModel != null){
            mModel.getHotDataList(new BaseCallBack<HotBean>() {
                @Override
                public void onSuccess(HotBean hotBean) {
                    if (mView != null){
                        mView.onSuccess(hotBean);
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
