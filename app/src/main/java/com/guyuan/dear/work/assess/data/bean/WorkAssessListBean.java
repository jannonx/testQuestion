package com.guyuan.dear.work.assess.data.bean;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:02
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessListBean {

    /**
     * auditEndTime :
     * auditStartTime :
     * auditTime :
     * auditUserName :
     * compereName :
     * contractNumber :
     * customerName :
     * id : 0
     * meetingName :
     * status : 0
     */

    private String auditEndTime;
    private String auditStartTime;
    private String auditTime;
    private String auditUserName;
    private String compereName;
    private String contractNumber;
    private String customerName;
    private int id;
    private String meetingName;
    private int status;

    public String getAuditEndTime() {
        return auditEndTime;
    }

    public void setAuditEndTime(String auditEndTime) {
        this.auditEndTime = auditEndTime;
    }

    public String getAuditStartTime() {
        return auditStartTime;
    }

    public void setAuditStartTime(String auditStartTime) {
        this.auditStartTime = auditStartTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getCompereName() {
        return compereName;
    }

    public void setCompereName(String compereName) {
        this.compereName = compereName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}