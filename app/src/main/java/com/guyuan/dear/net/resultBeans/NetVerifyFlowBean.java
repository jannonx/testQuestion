package com.guyuan.dear.net.resultBeans;

import java.util.List;

/**
 * 审批流程
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/23 10:45
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetVerifyFlowBean {


    /**
     * businessDetails : [{"content":"","createName":"","createTime":"","departmentName":"","deptName":"","followBusinessId":0,"id":0,"imgUrl":""}]
     * content :
     * createName :
     * createTime :
     * departmentName :
     * deptName :
     * id : 0
     * imgUrl :
     * updateName :
     */

    private String content;
    private String createName;
    private String createTime;
    private String departmentName;
    private String deptName;
    private int id;
    private String imgUrl;
    private String updateName;
    private List<BusinessDetailsBean> businessDetails;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<BusinessDetailsBean> getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(List<BusinessDetailsBean> businessDetails) {
        this.businessDetails = businessDetails;
    }

    public static class BusinessDetailsBean {
        /**
         * content :
         * createName :
         * createTime :
         * departmentName :
         * deptName :
         * followBusinessId : 0
         * id : 0
         * imgUrl :
         */

        private String content;
        private String createName;
        private String createTime;
        private String departmentName;
        private String deptName;
        private int followBusinessId;
        private int id;
        private String imgUrl;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getFollowBusinessId() {
            return followBusinessId;
        }

        public void setFollowBusinessId(int followBusinessId) {
            this.followBusinessId = followBusinessId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
