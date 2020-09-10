package com.guyuan.dear.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.guyuan.dear.db.entities.StaffsJoinLocationsEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "STAFFS_JOIN_LOCATIONS_ENTITY".
*/
public class StaffsJoinLocationsEntityDao extends AbstractDao<StaffsJoinLocationsEntity, Long> {

    public static final String TABLENAME = "STAFFS_JOIN_LOCATIONS_ENTITY";

    /**
     * Properties of entity StaffsJoinLocationsEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property StaffId = new Property(1, long.class, "staffId", false, "STAFF_ID");
        public final static Property LocationId = new Property(2, long.class, "locationId", false, "LOCATION_ID");
    }


    public StaffsJoinLocationsEntityDao(DaoConfig config) {
        super(config);
    }
    
    public StaffsJoinLocationsEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STAFFS_JOIN_LOCATIONS_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"STAFF_ID\" INTEGER NOT NULL ," + // 1: staffId
                "\"LOCATION_ID\" INTEGER NOT NULL );"); // 2: locationId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STAFFS_JOIN_LOCATIONS_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StaffsJoinLocationsEntity entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getStaffId());
        stmt.bindLong(3, entity.getLocationId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StaffsJoinLocationsEntity entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getStaffId());
        stmt.bindLong(3, entity.getLocationId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public StaffsJoinLocationsEntity readEntity(Cursor cursor, int offset) {
        StaffsJoinLocationsEntity entity = new StaffsJoinLocationsEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.getLong(offset + 1), // staffId
            cursor.getLong(offset + 2) // locationId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StaffsJoinLocationsEntity entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStaffId(cursor.getLong(offset + 1));
        entity.setLocationId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(StaffsJoinLocationsEntity entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(StaffsJoinLocationsEntity entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(StaffsJoinLocationsEntity entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
