package com.example.demo3.model;

import android.util.Log;

import com.example.demo3.api.ApiService;
import com.example.demo3.api.DataService;
import com.example.demo3.ben.Bean;
import com.example.demo3.contract.Contract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model implements Contract.Model {
    private static final String TAG = "Model====";

    @Override
    public void getRequestData(final Contract.CallBack callBack) {
        ApiService apiService = DataService.getApiService();
        apiService.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        callBack.getSuccess(bean.getData());
                        Log.d(TAG, "onNext: "+bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
