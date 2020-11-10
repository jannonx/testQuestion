package com.guyuan.dear.db.entities;

import androidx.room.Entity;

import java.util.Objects;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 11:08
 * @company: 固远（深圳）信息技术有限公司
 **/
@Entity(primaryKeys = {"userId","level"})
public class StaffDeptCrosRef {
    public long userId;
    public long deptId;
    public int level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffDeptCrosRef)) return false;
        StaffDeptCrosRef crosRef = (StaffDeptCrosRef) o;
        return userId == crosRef.userId &&
                deptId == crosRef.deptId &&
                level == crosRef.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, deptId, level);
    }
}
