package com.guyuan.dear.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.guyuan.dear.db.entities.GroupJoinUserEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "GROUP_JOIN_USER_ENTITY".
*/
public class GroupJoinUserEntityDao extends AbstractDao<GroupJoinUserEntity, Long> {

    public static final String TABLENAME = "GROUP_JOIN_USER_ENTITY";

    /**
     * Properties of entity GroupJoinUserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property UserId = new Property(1, long.class, "userId", false, "USER_ID");
        public final static Property GroupId = new Property(2, long.class, "groupId", false, "GROUP_ID");
    }


    public GroupJoinUserEntityDao(DaoConfig config) {
        super(config);
    }
    
    public GroupJoinUserEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GROUP_JOIN_USER_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"USER_ID\" INTEGER NOT NULL ," + // 1: userId
                "\"GROUP_ID\" INTEGER NOT NULL );"); // 2: groupId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GROUP_JOIN_USER_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GroupJoinUserEntity entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getGroupId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GroupJoinUserEntity entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getGroupId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GroupJoinUserEntity readEntity(Cursor cursor, int offset) {
        GroupJoinUserEntity entity = new GroupJoinUserEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.getLong(offset + 1), // userId
            cursor.getLong(offset + 2) // groupId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GroupJoinUserEntity entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.getLong(offset + 1));
        entity.setGroupId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GroupJoinUserEntity entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GroupJoinUserEntity entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GroupJoinUserEntity entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}