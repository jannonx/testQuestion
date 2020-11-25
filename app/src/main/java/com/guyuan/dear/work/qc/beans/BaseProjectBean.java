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

    public BaseProjectBean(NetBaseProjectBean src) {
        this.projectId = src.getProjectCode();
        this.projectName =src.getProjectName();
        this.id =src.getId();
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
}
