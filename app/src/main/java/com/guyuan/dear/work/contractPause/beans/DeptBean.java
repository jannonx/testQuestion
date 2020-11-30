package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.db.entities.DeptEntity;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 11:21
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DeptBean implements Parcelable {
    private String deptName;
    private long id;
    private short level;

    public DeptBean() {
    }

    protected DeptBean(Parcel in) {
        deptName = in.readString();
        id = in.readLong();
        level = (short) in.readInt();
    }

    public DeptBean(DeptEntity deptEntity) {
        setDeptName(deptEntity.deptName);
        setId(deptEntity.deptId);
        setLevel((short) deptEntity.level);
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public static final Creator<DeptBean> CREATOR = new Creator<DeptBean>() {
        @Override
        public DeptBean createFromParcel(Parcel in) {
            return new DeptBean(in);
        }

        @Override
        public DeptBean[] newArray(int size) {
            return new DeptBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deptName);
        dest.writeLong(id);
        dest.writeInt((int) level);
    }
}
