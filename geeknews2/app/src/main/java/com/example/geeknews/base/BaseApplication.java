package com.example.geeknews.base;

import android.app.Application;

import com.example.geeknews.dao.DaoMaster;
import com.example.geeknews.dao.DaoSession;


public class BaseApplication extends Application {

    private static BaseApplication context ;
    private static DaoSession daoSession;

    public static BaseApplication getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        initDb();
    }

    private void initDb() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "geeknews.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
