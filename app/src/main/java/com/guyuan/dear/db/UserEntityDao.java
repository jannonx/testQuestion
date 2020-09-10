package com.guyuan.dear.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.guyuan.dear.db.entities.GroupJoinUserEntity;
import com.guyuan.dear.db.entities.MeetingsJoinUsersEntity;
import com.guyuan.dear.db.entities.UserEntity;

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
 * DAO for table "USER_ENTITY".
*/
public class UserEntityDao extends AbstractDao<UserEntity, Long> {

    public static final String TABLENAME = "USER_ENTITY";

    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property ImgUrl = new Property(2, String.class, "imgUrl", false, "IMG_URL");
    }

    private DaoSession daoSession;

    private Query<UserEntity> meetingEntity_AttendantsQuery;
    private Query<UserEntity> userGroupEntity_UsersQuery;

    public UserEntityDao(DaoConfig config) {
        super(config);
    }
    
    public UserEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"IMG_URL\" TEXT);"); // 2: imgUrl
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(3, imgUrl);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(3, imgUrl);
        }
    }

    @Override
    protected final void attachEntity(UserEntity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public UserEntity readEntity(Cursor cursor, int offset) {
        UserEntity entity = new UserEntity( //
            cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // imgUrl
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserEntity entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setImgUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserEntity entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "attendants" to-many relationship of MeetingEntity. */
    public List<UserEntity> _queryMeetingEntity_Attendants(long meetingId) {
        synchronized (this) {
            if (meetingEntity_AttendantsQuery == null) {
                QueryBuilder<UserEntity> queryBuilder = queryBuilder();
                queryBuilder.join(MeetingsJoinUsersEntity.class, MeetingsJoinUsersEntityDao.Properties.UserId)
                    .where(MeetingsJoinUsersEntityDao.Properties.MeetingId.eq(meetingId));
                meetingEntity_AttendantsQuery = queryBuilder.build();
            }
        }
        Query<UserEntity> query = meetingEntity_AttendantsQuery.forCurrentThread();
        query.setParameter(0, meetingId);
        return query.list();
    }

    /** Internal query to resolve the "users" to-many relationship of UserGroupEntity. */
    public List<UserEntity> _queryUserGroupEntity_Users(long groupId) {
        synchronized (this) {
            if (userGroupEntity_UsersQuery == null) {
                QueryBuilder<UserEntity> queryBuilder = queryBuilder();
                queryBuilder.join(GroupJoinUserEntity.class, GroupJoinUserEntityDao.Properties.UserId)
                    .where(GroupJoinUserEntityDao.Properties.GroupId.eq(groupId));
                userGroupEntity_UsersQuery = queryBuilder.build();
            }
        }
        Query<UserEntity> query = userGroupEntity_UsersQuery.forCurrentThread();
        query.setParameter(0, groupId);
        return query.list();
    }

}
