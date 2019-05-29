package com.example.demo3two;

import android.app.Application;

import jiyun.example.cts.greendao.dao.DaoMaster;
import jiyun.example.cts.greendao.dao.DaoSession;

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "dog.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getSession(){
        return daoSession;
    }
}
