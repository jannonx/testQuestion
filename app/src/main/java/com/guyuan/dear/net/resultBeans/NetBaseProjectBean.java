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
}
