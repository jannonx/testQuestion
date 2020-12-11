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
     * 判定维度(申请详情界面显示的判定维度)
     */
    @SerializedName("remark1")
    private String judgeCondition;
    /**
     * 判定维度(返回列表时，在列表界面展示的判定维度)
     */
    @SerializedName("judgeCondition")
    private String listJudgeCondition;
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

    private String signTime;

    /**
     * 申请批复的状态 0 审批中 1 通过 2 拒绝
     */
    private int approveStatus;
    private String approveComment;

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

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

    public int getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(int approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getListJudgeCondition() {
        return listJudgeCondition;
    }

    public void setListJudgeCondition(String listJudgeCondition) {
        this.listJudgeCondition = listJudgeCondition;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
    }
}
