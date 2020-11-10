package com.guyuan.dear.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.LineNumberReader;
import java.util.Objects;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 10:22
 * @company: 固远（深圳）信息技术有限公司
 **/
@Entity
public class StaffEntity {
//    /**
//     * userId : 4
//     * name : 唐力
//     * imgUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160379026584005989.jpeg
//     * workId : 4
//     * userDepartment : 760,764
//     * userDepartmentName : 研发部,研发二部
//     * status : 1
//     */
    public String name;
    /**
     * 用户ID，平时用这个发起网络申请和本地数据库查询
     */
    @PrimaryKey
    public long userId;
    public String imgUrl;
    public short deleteFlag;
    /**
     * 工牌ID，用来展示，一般不用
     */
    public String workId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffEntity)) return false;
        StaffEntity that = (StaffEntity) o;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
