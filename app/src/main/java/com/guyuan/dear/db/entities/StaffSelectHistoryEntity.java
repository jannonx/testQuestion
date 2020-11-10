package com.guyuan.dear.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/10 13:56
 * @company: 固远（深圳）信息技术有限公司
 **/
@Entity(indices ={@Index(value = {"staffId"},unique = true)})
public class StaffSelectHistoryEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long staffId;
    public long selectDate;
}
