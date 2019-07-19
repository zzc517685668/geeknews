package com.example.geeknews.utils;

import android.util.Log;

import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.bean.CollectionDbBean;
import com.example.geeknews.dao.CollectionDbBeanDao;
import com.example.geeknews.dao.DaoSession;

import java.util.List;

public class DbUtils {
    public static DaoSession daoSession = BaseApplication.getDaoSession();
    private static final String TAG = "DbUtils";

    public static CollectionDbBean queryItem(CollectionDbBean collectionDbBean){
        CollectionDbBean unique = daoSession.queryBuilder(CollectionDbBean.class)
                .where(CollectionDbBeanDao.Properties.Id.eq(collectionDbBean.getId()))
                .build()
                .unique();
        return unique;
    }

    public static void insert(CollectionDbBean collectionDbBean){
        if (queryItem(collectionDbBean) == null){
            daoSession.insert(collectionDbBean);
        }else {
            Log.e(TAG, "insert: 已经存在");
        }
    }

    public static List<CollectionDbBean> queryAll(){
        List<CollectionDbBean> collectionDbBeans = daoSession.loadAll(CollectionDbBean.class);
        return collectionDbBeans;
    }

    public static void delete(CollectionDbBean collectionDbBean){
        CollectionDbBean collectionDbBean1 = queryItem(collectionDbBean);
        if (collectionDbBean1!= null){
            daoSession.delete(collectionDbBean1);
        }else {
            Log.e(TAG, "delete: 不存在");
        }
    }
}
