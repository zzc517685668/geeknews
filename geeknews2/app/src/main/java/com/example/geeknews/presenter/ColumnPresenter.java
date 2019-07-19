package com.example.geeknews.presenter;

import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.ColumnBean;
import com.example.geeknews.model.ColumnModel;
import com.example.geeknews.view.MyView;

public class ColumnPresenter extends BasePresenter<ColumnModel, MyView> {
    public void getColumnContent(){
        if (mModel != null){
            mModel.getColumnDataList(new BaseCallBack<ColumnBean>() {
                @Override
                public void onSuccess(ColumnBean columnBean) {
                    if (mView != null){
                        mView.onSuccess(columnBean);
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
