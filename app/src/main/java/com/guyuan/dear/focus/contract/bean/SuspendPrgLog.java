package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 14:56
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SuspendPrgLog {
    private long date;
    private String judgeCondition;
    private String approveBy;
    private String cause;

    public SuspendPrgLog() {
    }

//    public SuspendPrgLog(NetContractStatusFlow.ContractAbnormalSuspendVOBean src) {
//        try {
//            this.date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getCreateTime()).getTime();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        judgeCondition = src.getRemark1();
//        approveBy =src.getApprovedBy();
//        cause = src.getRemark();
//
//    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
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
