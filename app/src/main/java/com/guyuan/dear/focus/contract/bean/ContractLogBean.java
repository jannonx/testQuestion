package com.guyuan.dear.focus.contract.bean;

import android.os.Parcel;
import android.os.Parcelable;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 11:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractLogBean implements Parcelable {
    private int logType;
    private String jsonString;
    @Transient
    public static final int LOG_TYPE_FIRST_CREATE_DATE=0;
    @Transient
    public static final int LOG_TYPE_SALES_REVIEW_MEETING=1;

    public ContractLogBean() {
    }

    protected ContractLogBean(Parcel in) {
        logType = in.readInt();
        jsonString = in.readString();
    }

    public static final Creator<ContractLogBean> CREATOR = new Creator<ContractLogBean>() {
        @Override
        public ContractLogBean createFromParcel(Parcel in) {
            return new ContractLogBean(in);
        }

        @Override
        public ContractLogBean[] newArray(int size) {
            return new ContractLogBean[size];
        }
    };

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(logType);
        dest.writeString(jsonString);
    }
}
