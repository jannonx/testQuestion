package com.guyuan.dear.net.resultBeans;

import com.google.gson.annotations.SerializedName;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/19 15:14
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractInfo extends NetBaseContractInfo {

    /**
     * content :
     * cusName :
     * examineBy :
     * followUpTime :
     * qualityDeposit : 0
     * remark1 :
     * remarks :
     * salesman :
     * signTime :
     * state : 0
     * totalAmount : 0
     */


    private String cusName;
    /**
     * 变更状态申请发起人
     */
    private String examineBy;

    /**
     * 判定维度
     */
    @SerializedName("remark1")
    private String judgeCondition;
    /**
     * 原因
     */
    private String remarks;

    /**
     * 0：未审批 1：审批通过 2：审批失败
     */
    private int state;

    /**
     * 通过这个ID获取详情
     */
    private int examineId;

    /**
     * 状态标签 10002：表示暂停 10003：表示激活
     */
    private int changeType;

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getExamineBy() {
        return examineBy;
    }

    public void setExamineBy(String examineBy) {
        this.examineBy = examineBy;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getExamineId() {
        return examineId;
    }

    public void setExamineId(int examineId) {
        this.examineId = examineId;
    }
}
