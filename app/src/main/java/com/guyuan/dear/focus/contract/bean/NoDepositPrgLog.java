package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 14:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NoDepositPrgLog {
    /**
     * 日期
     */
    private long date;
    /**
     * 审批人
     */
    private String approveBy;
    /**
     * 原因
     */
    private String cause;

    public NoDepositPrgLog(NetContractStatusFlow.ContractAbnormalQualityVOBean src) {
        try {
            date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getCreateTime()).getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        approveBy = src.getApprovedBy();
        cause = src.getRemark();
    }

    public NoDepositPrgLog() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
