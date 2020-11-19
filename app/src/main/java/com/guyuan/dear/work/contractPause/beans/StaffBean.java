package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 14:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffBean implements Parcelable {
    private String name;
    private Long id;
    private String imgUrl;
    private String workId;
    private List<DeptBean> depts;

    public StaffBean() {
    }


    protected StaffBean(Parcel in) {
        name = in.readString();
        id = in.readLong();
        imgUrl = in.readString();
        workId = in.readString();
        depts = in.createTypedArrayList(DeptBean.CREATOR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public List<DeptBean> getDepts() {
        return depts;
    }

    public void setDepts(List<DeptBean> depts) {
        this.depts = depts;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(id);
        dest.writeString(imgUrl);
        dest.writeString(workId);
        dest.writeTypedList(depts);
    }
}
