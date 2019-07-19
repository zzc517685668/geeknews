package com.example.geeknews.model;

import android.util.Log;

import com.example.geeknews.api.ApiService;
import com.example.geeknews.api.ZhiHuService;
import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.DailyBean;
import com.example.geeknews.bean.DailyBeforeBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DailyModel extends BaseModel {

    /**
     * 获取日报今日新闻
     * @param callBack
     */
    public void getDailyDataList(final BaseCallBack<DailyBean> callBack) {
        ApiService apiService = ZhiHuService.getApiService();
        apiService.getDailyData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyBean dailyBean) {
                        callBack.onSuccess(dailyBean);
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

    /**
     *  获取日报往期新闻
     * @param date --20190710
     * @param callBack
     */
    public void getDailyBeforeData(String date, final BaseCallBack<DailyBeforeBean> callBack){
        ApiService apiService = ZhiHuService.getApiService();
        apiService.getDailyBeforeList(date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyBeforeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyBeforeBean dailyBeforeBean) {
                        callBack.onSuccess(dailyBeforeBean);
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
