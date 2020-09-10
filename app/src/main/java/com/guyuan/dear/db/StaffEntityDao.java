package com.guyuan.dear.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.db.entities.StaffsJoinLocationsEntity;

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
 * DAO for table "STAFF_ENTITY".
*/
public class StaffEntityDao extends AbstractDao<StaffEntity, Long> {

    public static final String TABLENAME = "STAFF_ENTITY";

    /**
     * Properties of entity StaffEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Name = new Property(0, String.class, "name", false, "NAME");
        public final static Property Id = new Property(1, Long.class, "id", true, "_id");
        public final static Property ImageUrl = new Property(2, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property LocationId = new Property(3, long.class, "locationId", false, "LOCATION_ID");
        public final static Property DeptId = new Property(4, long.class, "deptId", false, "DEPT_ID");
        public final static Property WorkId = new Property(5, String.class, "workId", false, "WORK_ID");
    }

    private DaoSession daoSession;

    private Query<StaffEntity> locationEntity_StaffsQuery;

    public StaffEntityDao(DaoConfig config) {
        super(config);
    }
    
    public StaffEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STAFF_ENTITY\" (" + //
                "\"NAME\" TEXT," + // 0: name
                "\"_id\" INTEGER PRIMARY KEY ," + // 1: id
                "\"IMAGE_URL\" TEXT," + // 2: imageUrl
                "\"LOCATION_ID\" INTEGER NOT NULL ," + // 3: locationId
                "\"DEPT_ID\" INTEGER NOT NULL ," + // 4: deptId
                "\"WORK_ID\" TEXT);"); // 5: workId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STAFF_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StaffEntity entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(3, imageUrl);
        }
        stmt.bindLong(4, entity.getLocationId());
        stmt.bindLong(5, entity.getDeptId());
 
        String workId = entity.getWorkId();
        if (workId != null) {
            stmt.bindString(6, workId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StaffEntity entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(3, imageUrl);
        }
        stmt.bindLong(4, entity.getLocationId());
        stmt.bindLong(5, entity.getDeptId());
 
        String workId = entity.getWorkId();
        if (workId != null) {
            stmt.bindString(6, workId);
        }
    }

    @Override
    protected final void attachEntity(StaffEntity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    @Override
    public StaffEntity readEntity(Cursor cursor, int offset) {
        StaffEntity entity = new StaffEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // name
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // imageUrl
            cursor.getLong(offset + 3), // locationId
            cursor.getLong(offset + 4), // deptId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // workId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StaffEntity entity, int offset) {
        entity.setName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setImageUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLocationId(cursor.getLong(offset + 3));
        entity.setDeptId(cursor.getLong(offset + 4));
        entity.setWorkId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(StaffEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(StaffEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(StaffEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "staffs" to-many relationship of LocationEntity. */
    public List<StaffEntity> _queryLocationEntity_Staffs(long locationId) {
        synchronized (this) {
            if (locationEntity_StaffsQuery == null) {
                QueryBuilder<StaffEntity> queryBuilder = queryBuilder();
                queryBuilder.join(StaffsJoinLocationsEntity.class, StaffsJoinLocationsEntityDao.Properties.StaffId)
                    .where(StaffsJoinLocationsEntityDao.Properties.LocationId.eq(locationId));
                locationEntity_StaffsQuery = queryBuilder.build();
            }
        }
        Query<StaffEntity> query = locationEntity_StaffsQuery.forCurrentThread();
        query.setParameter(0, locationId);
        return query.list();
    }

}
