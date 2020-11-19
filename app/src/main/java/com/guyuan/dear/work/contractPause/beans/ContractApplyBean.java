package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import java.util.ArrayList;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:30
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyBean extends BaseContractExcptBean implements Parcelable {
    private String detailReason;
    private ArrayList<StaffBean> sendList = new ArrayList<>();
    private ArrayList<StaffBean> copyList = new ArrayList<>();
    /**
     * 申请类型，参考：
     * {@link ContractApplyBean#APPLY_TYPE_PAUSE} {@link ContractApplyBean#APPLY_TYPE_RESUME}
     */
    private int applyType;
    /**
     * 申请类型：合同暂停
     */
    public static final int APPLY_TYPE_PAUSE=1;
    /**
     * 申请类型：合同重启
     */
    public static final int APPLY_TYPE_RESUME=2;

    public String getDetailReason() {
        return detailReason;
    }

    public void setDetailReason(String detailReason) {
        this.detailReason = detailReason;
    }

    public ArrayList<StaffBean> getSendList() {
        return sendList;
    }

    public void setSendList(ArrayList<StaffBean> sendList) {
        this.sendList = sendList;
    }

    public ArrayList<StaffBean> getCopyList() {
        return copyList;
    }

    public void setCopyList(ArrayList<StaffBean> copyList) {
        this.copyList = copyList;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }
}
