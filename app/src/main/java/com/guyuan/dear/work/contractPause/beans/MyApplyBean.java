package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.net.resultBeans.NetContractInfo;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:30
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyBean extends BaseContractExcptBean implements Parcelable {
    @Transient
    public static final int APPLY_REJECTED = 0;
    @Transient
    public static final int APPLY_PENDING_FOR_START = 1;
    @Transient
    public static final int APPLY_APPROVED = 2;
    @Transient
    public static final int APPLY_PROCESSING = 3;

    private int state;
    private String applier;

    public MyApplyBean() {
    }

    public MyApplyBean(NetContractInfo src) {
        setBuyer(src.getCusName());
        setContractNum(src.getContractNum());
        setExamineId(src.getExamineId());
        setJudgement(src.getJudgeCondition());
        String signTime = src.getSignTime();
        long time = 0L;
        if (signTime != null) {
            try {
                Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(signTime);
                time = date.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setDate(time);
        List<String> tags = new ArrayList<>();
        int state = src.getState();
        String tag = null;
        switch (state) {
            case 1:
                this.state = APPLY_APPROVED;
                tag = "审批通过";
                break;
            case 2:
                this.state = APPLY_REJECTED;
                tag = "申请驳回";
                break;
            case 0:
            default:
                this.state = APPLY_PENDING_FOR_START;
                tag = "未审批";
                break;
        }
        if (tag != null) {
            tags.add(tag);
        }
        setTags(tags);
    }

    protected MyApplyBean(Parcel in) {
        super(in);
        state = in.readInt();
        applier = in.readString();
    }

    public static final Creator<MyApplyBean> CREATOR = new Creator<MyApplyBean>() {
        @Override
        public MyApplyBean createFromParcel(Parcel in) {
            return new MyApplyBean(in);
        }

        @Override
        public MyApplyBean[] newArray(int size) {
            return new MyApplyBean[size];
        }
    };

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(state);
        dest.writeString(applier);
    }
}
