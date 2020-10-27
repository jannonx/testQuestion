package com.guyuan.dear.focus.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/27 10:44
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessDetailBean {

    /**
     * auditContentList : [{"id":0,"name":"","sort":0}]
     * auditFormResultVOList : [{"auditExplain":"","auditImgUrl":"","auditResult":"","auditUserName":""}]
     * auditType : 0
     * compereName :
     * contractNumber :
     * customerName :
     * id : 0
     * meetingName :
     * reserveEndTime :
     * reserveStartTime :
     * status : 0
     */

    private int auditType;                 //评审类型
    private String compereName;            //会议主持人
    private String contractNumber;         //销售合同编号
    private String customerName;           //客户名称
    private int id;                        //评审历史主键ID
    private String meetingName;            //会议室
    private String reserveEndTime;         //预约评审截止时间
    private String reserveStartTime;       //预约评审开始时间
    private int status;                    //评审状态，0:保存草稿、10:待评审、20:评审中、30:评审通过、40:评审不通过)
    /**
     * id : 0
     * name :
     * sort : 0
     */

    private List<AuditContentListBean> auditContentList;
    /**
     * auditExplain :
     * auditImgUrl :
     * auditResult :
     * auditUserName :
     */

    private List<AuditFormResultVOListBean> auditFormResultVOList;

    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
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

    public String getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public String getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(String reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AuditContentListBean> getAuditContentList() {
        return auditContentList;
    }

    public void setAuditContentList(List<AuditContentListBean> auditContentList) {
        this.auditContentList = auditContentList;
    }

    public List<AuditFormResultVOListBean> getAuditFormResultVOList() {
        return auditFormResultVOList;
    }

    public void setAuditFormResultVOList(List<AuditFormResultVOListBean> auditFormResultVOList) {
        this.auditFormResultVOList = auditFormResultVOList;
    }

    public static class AuditContentListBean {
        private int id;               //事项ID
        private String name;          //评审项内容描述
        private int sort;             //评审点

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }

    public static class AuditFormResultVOListBean {
        private String auditExplain;        //评审结论
        private String auditImgUrl;         //附件集
        private String auditResult;         //评审结果
        private String auditUserName;       //评审人员名称

        public String getAuditExplain() {
            return auditExplain;
        }

        public void setAuditExplain(String auditExplain) {
            this.auditExplain = auditExplain;
        }

        public String getAuditImgUrl() {
            return auditImgUrl;
        }

        public void setAuditImgUrl(String auditImgUrl) {
            this.auditImgUrl = auditImgUrl;
        }

        public String getAuditResult() {
            return auditResult;
        }

        public void setAuditResult(String auditResult) {
            this.auditResult = auditResult;
        }

        public String getAuditUserName() {
            return auditUserName;
        }

        public void setAuditUserName(String auditUserName) {
            this.auditUserName = auditUserName;
        }
    }
}