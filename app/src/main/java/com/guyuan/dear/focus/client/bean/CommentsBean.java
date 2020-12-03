package com.guyuan.dear.focus.client.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 客户模块--跟进动态内容
 * @author: 许建宁
 * @since: 2020/10/28 11:35
 * @company: 固远（深圳）信息技术有限公司
 */
public class CommentsBean implements Serializable {
    /**
     * 主键id
     */
    private long id;
    /**
     * 客户id
     */
    private long cusId;
    /**
     * 跟进主表id
     */
    private long followBusinessId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建人名称（跟进/评论人）
     */
    private String createName;
    /**
     * 创建人头像
     */
    private String imgUrl;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 跟进/评论人部门名称
     */
    private String departmentName;
    /**
     * 跟进/评论人部门名称
     */
    private String deptName;
    /**
     * 最后更新人名称（跟进/评论人）
     */
    private String updateName;

    /**
     * 跟评列表
     */
    private List<CommentsBean> businessDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCusId() {
        return cusId;
    }

    public void setCusId(long cusId) {
        this.cusId = cusId;
    }

    public long getFollowBusinessId() {
        return followBusinessId;
    }

    public void setFollowBusinessId(long followBusinessId) {
        this.followBusinessId = followBusinessId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

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

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<CommentsBean> getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(List<CommentsBean> businessDetails) {
        this.businessDetails = businessDetails;
    }
}
