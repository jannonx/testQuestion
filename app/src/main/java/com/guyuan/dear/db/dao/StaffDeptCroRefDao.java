package com.guyuan.dear.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.guyuan.dear.db.entities.StaffDeptCrosRef;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 16:13
 * @company: 固远（深圳）信息技术有限公司
 **/
@Dao
public interface  StaffDeptCroRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long[] insert(StaffDeptCrosRef...ref);
}
