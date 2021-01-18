package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 17:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetBaseProjectBean {

    /**
     * id : 0
     * projectCode :
     * projectName :
     */

    private int id;
    private String projectCode;
    private String projectName;
    /**
     * 销售合同状态：0.正常 1.暂停 2.被激活 3审批中
     */
    private int stopStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(int stopStatus) {
        this.stopStatus = stopStatus;
    }
}
