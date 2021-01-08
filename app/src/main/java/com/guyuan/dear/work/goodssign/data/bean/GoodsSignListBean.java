package com.guyuan.dear.work.goodssign.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/18 11:25
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignListBean {

    /**
     * content : [{"approvalStatus":0,"arrivalTime":"","arriveStatus":0,"code":"","followName":"","id":0,"remark":"","signName":"","signTime":"","supplierName":"","updateName":""}]
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
     * approvalStatus : 0
     * arrivalTime :
     * arriveStatus : 0
     * code :
     * followName :
     * id : 0
     * remark :
     * signName :
     * signTime :
     * supplierName :
     * updateName :
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
        private int approvalStatus;        //采购合同审批状态：0.审批中，1.审批通过，2.审批被驳回，3.保存为草稿
        private String arrivalTime;        //到货时间
        private int arriveStatus;          //签收状态：1.待收货，2.全部到货
        private String code;               //采购合同编号
        private String followName;         //跟进人名称
        private int id;                    //主键id
        private String remark;             //备注
        private String signName;           //签订人员名称
        private String signTime;           //签订时间
        private String supplierName;       //供应商名称
        private String updateName;         //操作人员名称
        private int projectId;             //项目ID

        public int getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(int approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public int getArriveStatus() {
            return arriveStatus;
        }

        public void setArriveStatus(int arriveStatus) {
            this.arriveStatus = arriveStatus;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFollowName() {
            return followName;
        }

        public void setFollowName(String followName) {
            this.followName = followName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }
    }
}