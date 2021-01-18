package com.guyuan.dear.work.qc.beans;

import com.guyuan.dear.net.resultBeans.NetBaseProjectBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 14:02
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseProjectBean {
    private String projectId;
    private String projectName;
    private int id;
    /**
     * 销售合同状态：0.正常 1.暂停 2.被激活 3审批中
     */
    private int status;

    public BaseProjectBean(NetBaseProjectBean src) {
        this.projectId = src.getProjectCode();
        this.projectName =src.getProjectName();
        this.id =src.getId();
        this.status = src.getStopStatus();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
