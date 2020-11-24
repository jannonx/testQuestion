package com.guyuan.dear.work.projectsite.bean;

import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 工程现场--现场勘查--上传数据载体
 * @author: 许建宁
 * @since: 2020/11/24 10:57
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostSiteExploreInfo implements Serializable {
    /**
     * 主表ID
     */
    private long id;
    /**
     * 问题描述
     */
    private String auditExplain;
    /**
     * 是否满足条件、是否安全(1:是，2:否)
     */
    private int satisfyFlag;
    /**
     * 附件
     */
    private List<String> imgUrl;

    /**
     * 备注原因
     */
    private List<ProjectSiteOpinionBean> psAuditItemDetailParamsList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuditExplain() {
        return auditExplain;
    }

    public void setAuditExplain(String auditExplain) {
        this.auditExplain = auditExplain;
    }

    public int getSatisfyFlag() {
        return satisfyFlag;
    }

    public void setSatisfyFlag(int satisfyFlag) {
        this.satisfyFlag = satisfyFlag;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ProjectSiteOpinionBean> getPsAuditItemDetailParamsList() {
        return psAuditItemDetailParamsList;
    }

    public void setPsAuditItemDetailParamsList(List<ProjectSiteOpinionBean> psAuditItemDetailParamsList) {
        this.psAuditItemDetailParamsList = psAuditItemDetailParamsList;
    }
}
