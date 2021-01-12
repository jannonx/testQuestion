package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/7 15:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseInfo implements Parcelable {
    /**
     * 暂停维度
     */
    private String pauseCauseType;
    private String approveBy;
    private long applyDate;
    private String detailPauseReason;
    /**
     * 暂停维度（提交申请时用）
     */
    private int applyCauseType;

    public ContractPauseInfo(NetBaseContractInfo src) {
        pauseCauseType = src.getStopCause();
        approveBy = src.getLastApprovedBy();
        applyDate = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getStopDate()).getTime();
        detailPauseReason = src.getStopCauseDetails();
    }

    public ContractPauseInfo() {
    }

    public ContractPauseInfo(NetContractStatusDetail.TexamineVoBean src) {
        pauseCauseType = src.getRemark1();
        detailPauseReason = src.getRemark();
        approveBy = src.getLastApprovedBy();
        try {
            applyDate = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getStopTime()).getTime();
        }catch (Exception e){
            LogUtils.showLog(e.getMessage());
        }
        applyCauseType = src.getApplyCauseType();
    }


    protected ContractPauseInfo(Parcel in) {
        pauseCauseType = in.readString();
        approveBy = in.readString();
        applyDate = in.readLong();
        detailPauseReason = in.readString();
        applyCauseType = in.readInt();
    }

    public static final Creator<ContractPauseInfo> CREATOR = new Creator<ContractPauseInfo>() {
        @Override
        public ContractPauseInfo createFromParcel(Parcel in) {
            return new ContractPauseInfo(in);
        }

        @Override
        public ContractPauseInfo[] newArray(int size) {
            return new ContractPauseInfo[size];
        }
    };

    public String getPauseCauseType() {
        return pauseCauseType;
    }

    public void setPauseCauseType(String pauseCauseType) {
        this.pauseCauseType = pauseCauseType;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    public String getDetailPauseReason() {
        return detailPauseReason;
    }

    public void setDetailPauseReason(String detailPauseReason) {
        this.detailPauseReason = detailPauseReason;
    }

    public int getApplyCauseType() {
        return applyCauseType;
    }

    public void setApplyCauseType(int applyCauseType) {
        this.applyCauseType = applyCauseType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pauseCauseType);
        dest.writeString(approveBy);
        dest.writeLong(applyDate);
        dest.writeString(detailPauseReason);
        dest.writeInt(applyCauseType);
    }
}
