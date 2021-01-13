package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.net.resultBeans.NetContractInfo;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;

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


    private int applyState;
    private String applier;
    private long applyDate;

    public MyApplyBean() {
    }

    public MyApplyBean(NetContractInfo src) {
        String statusExamineTime = src.getStatusExamineTime();
        if(!TextUtils.isEmpty(statusExamineTime)){
            try {
                applyDate = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(statusExamineTime).getTime();
            }catch (Exception e){
                LogUtils.showLog(e.getMessage());
            }
        }
        setContractId(src.getId());
        setBuyer(src.getCusName());
        setContractNum(src.getContractNum());
        setExamineId(src.getExamineId());
        setCause(src.getApproveComment());
        if(!TextUtils.isEmpty(src.getJudgeCondition())){
            setJudgement(src.getJudgeCondition());
        }else if(!TextUtils.isEmpty(src.getListJudgeCondition())){
            setJudgement(src.getListJudgeCondition());
        }
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
        int state = src.getApproveStatus();
        //审批状态 0 审批中 1 通过 2 拒绝
        String tag = null;
        switch (state) {
            case 0:
                this.applyState = APPLY_PENDING_FOR_START;
                tag = "待审批";
                break;
            case 1:
                this.applyState = APPLY_APPROVED;
                tag = "已通过";
                break;
            case 2:
                this.applyState = APPLY_REJECTED;
                tag = "已驳回";
                break;
            default:
                break;
        }
        if (tag != null) {
            tags.add(tag);
        }
        setTags(tags);
    }

    protected MyApplyBean(Parcel in) {
        super(in);
        applyState = in.readInt();
        applier = in.readString();
        applyDate = in.readLong();
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

    public int getApplyState() {
        return applyState;
    }

    public void setApplyState(int applyState) {
        this.applyState = applyState;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(applyState);
        dest.writeString(applier);
        dest.writeLong(applyDate);
    }
}
