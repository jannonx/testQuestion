package com.guyuan.dear.work.assess.data.bean;

import com.guyuan.dear.focus.assess.data.bean.AuditContentBean;
import com.guyuan.dear.focus.assess.data.bean.AuditFormResultBean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:11
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessDetailBean {

    private AuditFormResultBean appAuditFormResultVO;//当前用户查看详情-参与评审结论
    private String auditEndTime;       //评审截止时间
    private String auditStartTime;     //评审开始时间
    private String compereName;        //会议主持人
    private String contractNumber;     //销售合同编号
    private String customerName;       //客户名称
    private int id;                    //id
    private String meetingName;        //会议室名称
    private int status;                //评审状态，0:保存草稿、10:待评审、20:评审中、30:评审通过、40:评审不通过)
    private List<AuditContentBean> auditContentList;//评审内容事项集合

    public AuditFormResultBean getAppAuditFormResultVO() {
        return appAuditFormResultVO;
    }

    public void setAppAuditFormResultVO(AuditFormResultBean appAuditFormResultVO) {
        this.appAuditFormResultVO = appAuditFormResultVO;
    }

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

    public List<AuditContentBean> getAuditContentList() {
        return auditContentList;
    }

    public void setAuditContentList(List<AuditContentBean> auditContentList) {
        this.auditContentList = auditContentList;
    }

}