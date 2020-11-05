package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 14:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffBean implements Parcelable {
    private String name;
    private String id;
    private String imgUrl;
    private String dept;

    public StaffBean() {
    }

    protected StaffBean(Parcel in) {
        name = in.readString();
        id = in.readString();
        imgUrl = in.readString();
        dept = in.readString();
    }

    public static final Creator<StaffBean> CREATOR = new Creator<StaffBean>() {
        @Override
        public StaffBean createFromParcel(Parcel in) {
            return new StaffBean(in);
        }

        @Override
        public StaffBean[] newArray(int size) {
            return new StaffBean[size];
        }
    };

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "StaffBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(imgUrl);
        dest.writeString(dept);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffBean bean = (StaffBean) o;
        return id.equals(bean.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
