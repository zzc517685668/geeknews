package com.example.geeknews.presenter;

import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.WeChatBean;
import com.example.geeknews.model.WeChatModel;
import com.example.geeknews.view.MyView;

public class WeChatPresenter extends BasePresenter<WeChatModel, MyView<WeChatBean>> {
    public void getWeChatContent(){
        if (mModel != null){
            mModel.getWeChatDataList(new BaseCallBack<WeChatBean>() {
                @Override
                public void onSuccess(WeChatBean weChatBean) {
                    if (mView != null){
                        mView.onSuccess(weChatBean);
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
