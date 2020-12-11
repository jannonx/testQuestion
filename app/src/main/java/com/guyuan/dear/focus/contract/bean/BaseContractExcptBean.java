package com.guyuan.dear.focus.contract.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractInfo;

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
    /**
     * 评审流程ID，获取合同详情时用
     */
    private int examineId;

    private int state;

    public BaseContractExcptBean() {
    }

    public BaseContractExcptBean(NetContractInfo info) {
        this.state = info.getChangeType();
        String tag = null;
        switch (state) {
            case 10002:
                tag = "暂停";
                break;
            case 10003:
                tag = "激活";
                break;
            default:
                break;
        }
        if (tag != null) {
            setExceptionTag(tag);
        }
        if (!TextUtils.isEmpty(info.getJudgeCondition())) {
            setJudgement(info.getListJudgeCondition());
        } else if (!TextUtils.isEmpty(info.getListJudgeCondition())) {
            setJudgement(info.getListJudgeCondition());
        }
        setCause(info.getApproveComment());
        setExamineId(info.getExamineId());
        setContractNum(info.getContractNum());
        setBuyer(info.getCusName());

    }

    protected BaseContractExcptBean(Parcel in) {
        super(in);
        exceptionTag = in.readString();
        cause = in.readString();
        judgement = in.readString();
        judgementKey = in.readString();
        examineId = in.readInt();
        state = in.readInt();
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

    public int getExamineId() {
        return examineId;
    }

    public void setExamineId(int examineId) {
        this.examineId = examineId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(exceptionTag);
        dest.writeString(cause);
        dest.writeString(judgement);
        dest.writeString(judgementKey);
        dest.writeInt(examineId);
        dest.writeInt(state);
    }
}
