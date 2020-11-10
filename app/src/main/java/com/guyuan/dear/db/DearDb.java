package com.guyuan.dear.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.guyuan.dear.db.dao.DeptDao;
import com.guyuan.dear.db.dao.StaffDao;
import com.guyuan.dear.db.dao.StaffDeptCroRefDao;
import com.guyuan.dear.db.entities.DeptEntity;
import com.guyuan.dear.db.entities.StaffDeptCrosRef;
import com.guyuan.dear.db.entities.StaffEntity;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 11:16
 * @company: 固远（深圳）信息技术有限公司
 **/
@Database(entities = {StaffEntity.class, DeptEntity.class, StaffDeptCrosRef.class}, version = 1)
public abstract class DearDb extends RoomDatabase {

    public abstract StaffDao getStaffDao();

    public abstract DeptDao getDeptDao();

    public abstract StaffDeptCroRefDao getStaffDeptCroRefDao();

}
