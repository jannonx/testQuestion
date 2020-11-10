package com.guyuan.dear.db.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 10:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAndDepts {
    @Embedded
    public StaffEntity staffEntity;
    @Relation(
            parentColumn = "userId",
            entityColumn = "deptId",
            associateBy = @Junction(value = StaffDeptCrosRef.class))
    public List<DeptEntity> deptEntities;

}
