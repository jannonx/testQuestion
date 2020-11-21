package com.guyuan.dear.focus.projectsite.bean;

import java.util.List;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/19 14:04
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteCommonDetailBean {

    /**
     * 评审主键ID
     */
    private int id;
    /**
     * 类型
     */
    private int auditFormType;
    /**
     * 问题描述
     */
    private String auditItemExplain;
    /**
     * 客户名称
     */
    private String cusName;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 完成时间
     */
    private String finishTime;

    /**
     * 附件
     */
    private String imgUrl;
    /**
     * 执行人名称
     */
    private String name;
    /**
     * 名称
     */
    private String projectName;
    /**
     * 项目编号
     */
    private String projectNum;
    /**
     * 是否满足条件
     */
    private int satisfyFlag;
    /**
     * 状态
     */
    private int status;
    /**
     * 内容描述集
     */
    private List<ProjectSiteStatusBean> answerVOList;
    /**
     * 回复意见集
     */
    private List<ProjectSiteOpinionBean> psAuditItemVOList;

    public int getAuditFormType() {
        return auditFormType;
    }

    public void setAuditFormType(int auditFormType) {
        this.auditFormType = auditFormType;
    }

    public String getAuditItemExplain() {
        return auditItemExplain;
    }

    public void setAuditItemExplain(String auditItemExplain) {
        this.auditItemExplain = auditItemExplain;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSatisfyFlag() {
        return satisfyFlag;
    }

    public void setSatisfyFlag(int satisfyFlag) {
        this.satisfyFlag = satisfyFlag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProjectSiteStatusBean> getAnswerVOList() {
        return answerVOList;
    }

    public void setAnswerVOList(List<ProjectSiteStatusBean> answerVOList) {
        this.answerVOList = answerVOList;
    }

    public List<ProjectSiteOpinionBean> getPsAuditItemVOList() {
        return psAuditItemVOList;
    }

    public void setPsAuditItemVOList(List<ProjectSiteOpinionBean> psAuditItemVOList) {
        this.psAuditItemVOList = psAuditItemVOList;
    }
}