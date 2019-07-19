package com.example.geeknews.model;

import com.example.geeknews.api.ApiService;
import com.example.geeknews.api.GankService;
import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.bean.GankListGirlBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GankModel extends BaseModel {
    public void getGankList(String key, final BaseCallBack<GankBean> callBack){
        ApiService apiService = GankService.getApiService();
        apiService.getTechList(key,20,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GankBean gankBean) {
                        callBack.onSuccess(gankBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGankGirlList(final BaseCallBack<GankListGirlBean> callBack){
        ApiService apiService = GankService.getApiService();
        apiService.getGirlList(20,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankListGirlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GankListGirlBean gankListGirlBean) {
                        callBack.onSuccess(gankListGirlBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
