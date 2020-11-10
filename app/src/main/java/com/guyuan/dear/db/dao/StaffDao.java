package com.guyuan.dear.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.guyuan.dear.db.entities.StaffAndDepts;
import com.guyuan.dear.db.entities.StaffEntity;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 11:19
 * @company: 固远（深圳）信息技术有限公司
 **/
@Dao
public interface StaffDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long[] insertStaffs(StaffEntity... staffs);

    @Update
    public int upDateStaffs(StaffEntity... staffs);

    @Delete
    public int deleteStaffs(StaffEntity... staffs);

    @Query("SELECT * FROM StaffEntity")
    @Transaction
    public LiveData<List<StaffAndDepts>> loadAll();


}
