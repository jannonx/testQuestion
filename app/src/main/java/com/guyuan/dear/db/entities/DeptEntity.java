package com.guyuan.dear.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 10:23
 * @company: 固远（深圳）信息技术有限公司
 **/
@Entity
public class DeptEntity {
    @PrimaryKey
    public long deptId;
    public String deptName;
    /**
     * 0,1,2分别表示一级，二级，三级部门
     */
    public int level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeptEntity)) return false;
        DeptEntity entity = (DeptEntity) o;
        return deptId == entity.deptId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId);
    }
}
