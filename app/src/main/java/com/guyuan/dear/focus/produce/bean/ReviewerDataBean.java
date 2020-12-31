package com.guyuan.dear.focus.produce.bean;

import java.io.Serializable;

/**
 * @description: 审核人员信息
 * @author: 许建宁
 * @since: 2020/12/30 15:48
 * @company: 固远（深圳）信息技术有限公司
 */
public class ReviewerDataBean implements Serializable {
    /**
     * 审批人员姓名
     */
    private String auditName;
    /**
     * 判断维度
     */
    private String dimensionality;
    /**
     * 暂停原因
     */
    private String stopCause;
    /**
     * 时间
     */
    private String stopTime;

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getDimensionality() {
        return dimensionality;
    }

    public void setDimensionality(String dimensionality) {
        this.dimensionality = dimensionality;
    }

    public String getStopCause() {
        return stopCause;
    }

    public void setStopCause(String stopCause) {
        this.stopCause = stopCause;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
}
