package com.guyuan.dear.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.guyuan.dear.db.entities.GroupJoinUserEntity;
import com.guyuan.dear.db.entities.UserGroupEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "USER_GROUP_ENTITY".
*/
public class UserGroupEntityDao extends AbstractDao<UserGroupEntity, Long> {

    public static final String TABLENAME = "USER_GROUP_ENTITY";

    /**
     * Properties of entity UserGroupEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property CreateTime = new Property(2, long.class, "createTime", false, "CREATE_TIME");
        public final static Property DataOwnerId = new Property(3, long.class, "dataOwnerId", false, "DATA_OWNER_ID");
    }

    private DaoSession daoSession;

    private Query<UserGroupEntity> userEntity_UserGroupEntitiesQuery;

    public UserGroupEntityDao(DaoConfig config) {
        super(config);
    }
    
    public UserGroupEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_GROUP_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"TITLE\" TEXT," + // 1: title
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 2: createTime
                "\"DATA_OWNER_ID\" INTEGER NOT NULL );"); // 3: dataOwnerId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_GROUP_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserGroupEntity entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
        stmt.bindLong(3, entity.getCreateTime());
        stmt.bindLong(4, entity.getDataOwnerId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserGroupEntity entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
        stmt.bindLong(3, entity.getCreateTime());
        stmt.bindLong(4, entity.getDataOwnerId());
    }

    @Override
    protected final void attachEntity(UserGroupEntity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserGroupEntity readEntity(Cursor cursor, int offset) {
        UserGroupEntity entity = new UserGroupEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.getLong(offset + 2), // createTime
            cursor.getLong(offset + 3) // dataOwnerId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserGroupEntity entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.getLong(offset + 2));
        entity.setDataOwnerId(cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserGroupEntity entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserGroupEntity entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserGroupEntity entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "userGroupEntities" to-many relationship of UserEntity. */
    public List<UserGroupEntity> _queryUserEntity_UserGroupEntities(long userId) {
        synchronized (this) {
            if (userEntity_UserGroupEntitiesQuery == null) {
                QueryBuilder<UserGroupEntity> queryBuilder = queryBuilder();
                queryBuilder.join(GroupJoinUserEntity.class, GroupJoinUserEntityDao.Properties.GroupId)
                    .where(GroupJoinUserEntityDao.Properties.UserId.eq(userId));
                userEntity_UserGroupEntitiesQuery = queryBuilder.build();
            }
        }
        Query<UserGroupEntity> query = userEntity_UserGroupEntitiesQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

}
