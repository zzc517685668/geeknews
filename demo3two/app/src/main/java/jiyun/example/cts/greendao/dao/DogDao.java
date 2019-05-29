package jiyun.example.cts.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.demo3two.Dog;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOG".
*/
public class DogDao extends AbstractDao<Dog, Long> {

    public static final String TABLENAME = "DOG";

    /**
     * Properties of entity Dog.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Kid = new Property(0, Long.class, "kid", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property AiHao = new Property(2, String.class, "aiHao", false, "AI_HAO");
    }


    public DogDao(DaoConfig config) {
        super(config);
    }
    
    public DogDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: kid
                "\"NAME\" TEXT," + // 1: name
                "\"AI_HAO\" TEXT);"); // 2: aiHao
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Dog entity) {
        stmt.clearBindings();
 
        Long kid = entity.getKid();
        if (kid != null) {
            stmt.bindLong(1, kid);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String aiHao = entity.getAiHao();
        if (aiHao != null) {
            stmt.bindString(3, aiHao);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Dog entity) {
        stmt.clearBindings();
 
        Long kid = entity.getKid();
        if (kid != null) {
            stmt.bindLong(1, kid);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String aiHao = entity.getAiHao();
        if (aiHao != null) {
            stmt.bindString(3, aiHao);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Dog readEntity(Cursor cursor, int offset) {
        Dog entity = new Dog( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // kid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // aiHao
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Dog entity, int offset) {
        entity.setKid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAiHao(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Dog entity, long rowId) {
        entity.setKid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Dog entity) {
        if(entity != null) {
            return entity.getKid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Dog entity) {
        return entity.getKid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}