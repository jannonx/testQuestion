package com.guyuan.dear.focus.assess.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import kotlinx.android.parcel.Parcelize;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/2 15:32
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AuditContentBean implements Parcelable {
    private int id;               //事项ID
    private String name;          //评审项内容描述
    private int sort;             //评审点

    protected AuditContentBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        sort = in.readInt();
    }

    public static final Creator<AuditContentBean> CREATOR = new Creator<AuditContentBean>() {
        @Override
        public AuditContentBean createFromParcel(Parcel in) {
            return new AuditContentBean(in);
        }

        @Override
        public AuditContentBean[] newArray(int size) {
            return new AuditContentBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(sort);
    }
}