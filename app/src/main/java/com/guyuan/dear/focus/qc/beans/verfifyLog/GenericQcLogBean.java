package com.guyuan.dear.focus.qc.beans.verfifyLog;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.guyuan.dear.net.resultBeans.NetQcReportApproveFlow;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 15:32
 * @company: 固远（深圳）信息技术有限公司
 **/
public class GenericQcLogBean implements Parcelable {
    private int logType;
    private String jsonString;
    public static final int LOG_TYPE_FIRST_CREATE_DATE = 0;
    public static final int LOG_TYPE_SUBMIT_DETAIL=1;
    public static final int LOG_TYPE_VERIFY_RESULT=2;

    public GenericQcLogBean() {
    }

    public GenericQcLogBean(NetQcReportApproveFlow src){
        int status = src.getStatus();
        if(status==0){
            LogTypeSubmitDetail submitDetail = new LogTypeSubmitDetail(src);
            setLogType(LOG_TYPE_SUBMIT_DETAIL);
            setJsonString(new Gson().toJson(submitDetail));
        }else {
            LogTypeVerifyResult verifyResult = new LogTypeVerifyResult(src);
            setLogType(LOG_TYPE_VERIFY_RESULT);
            setJsonString(new Gson().toJson(verifyResult));
        }
    }

    protected GenericQcLogBean(Parcel in) {
        logType = in.readInt();
        jsonString = in.readString();
    }

    public static final Creator<GenericQcLogBean> CREATOR = new Creator<GenericQcLogBean>() {
        @Override
        public GenericQcLogBean createFromParcel(Parcel in) {
            return new GenericQcLogBean(in);
        }

        @Override
        public GenericQcLogBean[] newArray(int size) {
            return new GenericQcLogBean[size];
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
