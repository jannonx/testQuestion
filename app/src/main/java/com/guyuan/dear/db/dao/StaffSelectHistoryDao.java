package com.guyuan.dear.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.guyuan.dear.db.entities.StaffSelectHistoryEntity;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/10 14:00
 * @company: 固远（深圳）信息技术有限公司
 **/
@Dao
public interface StaffSelectHistoryDao {

    @Query("SELECT * FROM StaffSelectHistoryEntity ORDER BY selectDate DESC LIMIT :count")
    List<StaffSelectHistoryEntity> loadRecentByDate(int count);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(StaffSelectHistoryEntity... entities);

    @Delete
    int delete(StaffSelectHistoryEntity... entities);


    @Query("SELECT * FROM StaffSelectHistoryEntity ORDER BY selectDate ASC")
    List<StaffSelectHistoryEntity> loadAllAscByDate();


}
