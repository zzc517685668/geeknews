package com.example.geeknews.model;

import com.example.geeknews.api.ApiService;
import com.example.geeknews.api.WeChatService;
import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.WeChatBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeChatModel extends BaseModel {
    public void getWeChatDataList(final BaseCallBack<WeChatBean> chatBeanBaseCallBack){
        ApiService weChatApiService = WeChatService.getWeChatApiService();
        weChatApiService.getWeChatData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WeChatBean weChatBean) {
                        chatBeanBaseCallBack.onSuccess(weChatBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        chatBeanBaseCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
