package com.example.demo3two;

import android.util.Log;

import java.util.List;

import jiyun.example.cts.greendao.dao.DaoSession;
import jiyun.example.cts.greendao.dao.DogDao;

public class Utils {
    public static void insert(Dog dog){
        DaoSession session = MyApplication.getSession();
        if (quertItem(dog) == null){
            session.insert(dog);
        }else {
            Log.e("数据库--", "insert: 已经存在");
        }
    }

    public static Dog quertItem(Dog dog){
        DaoSession session = MyApplication.getSession();
        Dog unique = session.queryBuilder(Dog.class)
                .where(DogDao.Properties.Name.eq(dog.getName()))
                .build()
                .unique();
        return unique;
    }

    public static List<Dog> queryAll(){
        DaoSession session = MyApplication.getSession();
        List<Dog> dogs = session.loadAll(Dog.class);
        return dogs;
    }
}
