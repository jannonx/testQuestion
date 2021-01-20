package com.guyuan.dear.work.matterapply.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 20:28
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterProjectBean {

    /**
     * id : 0
     * projectCode :
     * projectName :
     * projectPerson :
     */

    private int id;                          //项目主键
    private String projectCode;              //项目编号
    private String projectName;              //项目名称
    private String projectPerson;            //项目总负责人
    private int stopStatus;                  //0.正常 1.暂停 2.被激活 3审批中

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

    public String getProjectPerson() {
        return projectPerson;
    }

    public void setProjectPerson(String projectPerson) {
        this.projectPerson = projectPerson;
    }

    public int getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(int stopStatus) {
        this.stopStatus = stopStatus;
    }
}