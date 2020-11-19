package com.guyuan.dear.focus.contract.bean;

import android.os.Parcel;
import android.os.Parcelable;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseContractExcptBean extends BaseContractBean implements Parcelable {
    /**
     * 标签，如：“暂停”，“终止”
     */
    private String exceptionTag;
    /**
     * 异常原因
     */
    private String cause;
    /**
     * 判定维度，如“国家政策终止”
     */
    private String judgement;
    private String judgementKey;

    public BaseContractExcptBean() {
    }

    protected BaseContractExcptBean(Parcel in) {
        super(in);
        exceptionTag = in.readString();
        cause = in.readString();
        judgement = in.readString();
        judgementKey = in.readString();
    }

    public static final Creator<BaseContractExcptBean> CREATOR = new Creator<BaseContractExcptBean>() {
        @Override
        public BaseContractExcptBean createFromParcel(Parcel in) {
            return new BaseContractExcptBean(in);
        }

        @Override
        public BaseContractExcptBean[] newArray(int size) {
            return new BaseContractExcptBean[size];
        }
    };

    public String getExceptionTag() {
        return exceptionTag;
    }

    public void setExceptionTag(String exceptionTag) {
        this.exceptionTag = exceptionTag;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    public String getJudgementKey() {
        return judgementKey;
    }

    public void setJudgementKey(String judgementKey) {
        this.judgementKey = judgementKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(exceptionTag);
        dest.writeString(cause);
        dest.writeString(judgement);
        dest.writeString(judgementKey);
    }
}
