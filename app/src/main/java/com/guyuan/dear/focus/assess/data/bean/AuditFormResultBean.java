package com.guyuan.dear.focus.assess.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/2 15:32
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AuditFormResultBean implements Parcelable {
    private String auditExplain;        //评审结论
    private String auditImgUrl;         //附件集
    private String auditResult;         //评审结果
    private String auditUserName;       //评审人员名称

    protected AuditFormResultBean(Parcel in) {
        auditExplain = in.readString();
        auditImgUrl = in.readString();
        auditResult = in.readString();
        auditUserName = in.readString();
    }

    public static final Creator<AuditFormResultBean> CREATOR = new Creator<AuditFormResultBean>() {
        @Override
        public AuditFormResultBean createFromParcel(Parcel in) {
            return new AuditFormResultBean(in);
        }

        @Override
        public AuditFormResultBean[] newArray(int size) {
            return new AuditFormResultBean[size];
        }
    };

    public String getAuditExplain() {
        return auditExplain;
    }

    public void setAuditExplain(String auditExplain) {
        this.auditExplain = auditExplain;
    }

    public String getAuditImgUrl() {
        return auditImgUrl;
    }

    public void setAuditImgUrl(String auditImgUrl) {
        this.auditImgUrl = auditImgUrl;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(auditExplain);
        dest.writeString(auditImgUrl);
        dest.writeString(auditResult);
        dest.writeString(auditUserName);
    }
}