package com.example.geeknews.presenter;

import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.DailyBean;
import com.example.geeknews.bean.DailyBeforeBean;
import com.example.geeknews.model.DailyModel;
import com.example.geeknews.view.DailyView;

public class DailyPresenter extends BasePresenter<DailyModel, DailyView> {

    public void getDailyContent() {
        if (mModel != null) {
            mModel.getDailyDataList(new BaseCallBack<DailyBean>() {
                @Override
                public void onSuccess(DailyBean dailyBean) {
                    if (mView != null) {
                        mView.onSuccess(dailyBean);
                    }
                }

                @Override
                public void onFail(String error) {
                    if (mView != null) {
                        mView.onFail(error);
                    }
                }
            });
        }
    }

    public void getDailyBeforeData(String date) {
        if (mModel != null) {
            mModel.getDailyBeforeData(date, new BaseCallBack<DailyBeforeBean>() {
                @Override
                public void onSuccess(DailyBeforeBean dailyBeforeBean) {
                    if (mView != null) {
                        mView.getBeforeData(dailyBeforeBean);
                    }
                }

                @Override
                public void onFail(String error) {
                    if (mView != null) {
                        mView.onFail(error);
                    }
                }
            });
        }
    }
}
