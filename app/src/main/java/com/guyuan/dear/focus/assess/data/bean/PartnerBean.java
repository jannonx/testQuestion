package com.guyuan.dear.focus.assess.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/8 14:07
 * @company : 固远（深圳）信息技术有限公司
 **/

public class PartnerBean implements Parcelable {
    private int id;
    private String name;
    private String url;

    protected PartnerBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PartnerBean> CREATOR = new Creator<PartnerBean>() {
        @Override
        public PartnerBean createFromParcel(Parcel in) {
            return new PartnerBean(in);
        }

        @Override
        public PartnerBean[] newArray(int size) {
            return new PartnerBean[size];
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
    }
}