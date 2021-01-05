package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 11:43
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseHistory {
    private long date;
    private String auditor;
    private String remark;
    private String causeType;

    public ContractPauseHistory(NetContractHistory.ContractStopVOSBean src) {
        String createTime = src.getCreateTime();
        if(!TextUtils.isEmpty(createTime)){
            date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(createTime).getTime();
        }
        auditor = src.getApprovName();
        remark = src.getRemark();
        causeType = src.getRemark1();
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCauseType() {
        return causeType;
    }

    public void setCauseType(String causeType) {
        this.causeType = causeType;
    }
}
