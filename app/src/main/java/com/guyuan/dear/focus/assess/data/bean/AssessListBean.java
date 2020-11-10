package com.guyuan.dear.focus.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :我的关注-评审列表bean
 * @since: 2020/10/27 10:34
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessListBean {

    /**
     * content : [{"auditEndTime":"","auditStartTime":"","auditTime":"","auditUserName":"","compereName":"","contractNumber":"","customerName":"","id":0,"meetingName":"","status":0}]
     * pageNum : 0
     * pageSize : 0
     * totalPages : 0
     * totalSize : 0
     * updateTime :
     */

    private int pageNum;
    private int pageSize;
    private int totalPages;
    private int totalSize;
    private String updateTime;
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

    private List<ContentBean> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private String auditEndTime;           //评审截止时间
        private String auditStartTime;         //评审开始时间
        private String auditTime;              //评审时间
        private String auditUserName;          //评审用户名称
        private String compereName;            //会议主持人
        private String contractNumber;         //销售合同编号
        private String customerName;           //客户名称
        private int id;                        //主键ID
        private String meetingName;            //会议室名称
        private int status;                    //评审状态，0:保存草稿、10:待评审、20:评审中、30:评审通过、40:评审不通过)

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
}