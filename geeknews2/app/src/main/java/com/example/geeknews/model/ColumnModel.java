package com.example.geeknews.model;

import com.example.geeknews.api.ApiService;
import com.example.geeknews.api.ZhiHuService;
import com.example.geeknews.base.BaseCallBack;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.ColumnBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ColumnModel extends BaseModel {
    public void getColumnDataList(final BaseCallBack<ColumnBean> columnBeanBaseCallBack){
        ApiService apiService = ZhiHuService.getApiService();
        apiService.getColumnData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ColumnBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ColumnBean columnBean) {
                        columnBeanBaseCallBack.onSuccess(columnBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        columnBeanBaseCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
