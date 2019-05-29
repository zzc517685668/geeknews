package jiyun.example.cts.greendao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.demo3two.Dog;

import jiyun.example.cts.greendao.dao.DogDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dogDaoConfig;

    private final DogDao dogDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dogDaoConfig = daoConfigMap.get(DogDao.class).clone();
        dogDaoConfig.initIdentityScope(type);

        dogDao = new DogDao(dogDaoConfig, this);

        registerDao(Dog.class, dogDao);
    }
    
    public void clear() {
        dogDaoConfig.clearIdentityScope();
    }

    public DogDao getDogDao() {
        return dogDao;
    }

}
