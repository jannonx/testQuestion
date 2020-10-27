package com.guyuan.dear.focus.sales.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/19 12:21
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ImageSource implements Parcelable {
    private int src;

    public ImageSource() {
    }

    protected ImageSource(Parcel in) {
        src = in.readInt();
    }

    public static final Creator<ImageSource> CREATOR = new Creator<ImageSource>() {
        @Override
        public ImageSource createFromParcel(Parcel in) {
            return new ImageSource(in);
        }

        @Override
        public ImageSource[] newArray(int size) {
            return new ImageSource[size];
        }
    };

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(src);
    }
}
