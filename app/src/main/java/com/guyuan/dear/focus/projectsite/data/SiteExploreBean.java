package com.guyuan.dear.focus.projectsite.data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 我的关注--现场勘查--数据载体
 * @author: Jannonx
 * @since: 2020/11/19 11:01
 * @company: 固远（深圳）信息技术有限公司
 */
@lombok.NoArgsConstructor
@lombok.Data
public class SiteExploreBean implements Serializable {


    /**
     * id : 10
     * auditFormType : 30
     * destination : 香江金融中心
     * status : 10
     * createTime : 2020-11-13 08:23:54
     * projectName :  中空分机
     * projectNum : ACACA
     * name : 谢俊杰
     * finishTime : null
     * idea : 正！
     */

    private long id;
    private Integer auditFormType;
    private String destination;
    private Integer status;
    private String createTime;
    private String projectName;
    private String projectNum;
    private String name;
    private Object finishTime;
    private String idea;


    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAuditFormType() {
        return auditFormType;
    }

    public void setAuditFormType(Integer auditFormType) {
        this.auditFormType = auditFormType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Object finishTime) {
        this.finishTime = finishTime;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}