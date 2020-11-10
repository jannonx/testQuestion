package com.guyuan.dear.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.guyuan.dear.db.entities.DeptEntity;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 11:47
 * @company: 固远（深圳）信息技术有限公司
 **/
@Dao
public interface DeptDao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    public Long[] insertDept(DeptEntity...depts);

    @Update()
    public int updateDept(DeptEntity...depts);

    @Query("SELECT * FROM DEPTENTITY")
    public List<DeptEntity> loadAll();

}
