package com.guyuan.dear.net.resultBeans;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 15:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetQcReportApproveFlow {

    /**
     * arType : 0
     * businessId : 0
     * createBy : 0
     * createDept :
     * createName :
     * createTime :
     * id : 0
     * imgUrl :
     * prepareName :
     * qualityCondition :
     * qualityName :
     * qualityNum : 0
     * qualityRemark :
     * qualityResult : 0
     * qualityType : 0
     * remark :
     * remark1 :
     * remark2 :
     * status : 0
     * texamineFlows : [{"approvalTime":"","approveBy":0,"approveName":"","departmentName":"","id":0,"imgUrl":"","mainId":0,"remarks":"","sort":0,"status":0}]
     */

    private int arType;
    private int businessId;
    private int createBy;
    private String createDept;
    private String createName;
    private String createTime;
    private int id;
    private String imgUrl;
    private String prepareName;
    private String qualityCondition;
    private String qualityName;
    private int qualityNum;
    private String qualityRemark;
    private int qualityResult;
    private int qualityType;
    private String remark;
    private String remark1;
    private String remark2;
    /**
     * 状态 0.审批中 1.已同意 2.已拒绝
     */
    private int status;

    public int getArType() {
        return arType;
    }

    public void setArType(int arType) {
        this.arType = arType;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
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

    public String getPrepareName() {
        return prepareName;
    }

    public void setPrepareName(String prepareName) {
        this.prepareName = prepareName;
    }

    public String getQualityCondition() {
        return qualityCondition;
    }

    public void setQualityCondition(String qualityCondition) {
        this.qualityCondition = qualityCondition;
    }

    public String getQualityName() {
        return qualityName;
    }

    public void setQualityName(String qualityName) {
        this.qualityName = qualityName;
    }

    public int getQualityNum() {
        return qualityNum;
    }

    public void setQualityNum(int qualityNum) {
        this.qualityNum = qualityNum;
    }

    public String getQualityRemark() {
        return qualityRemark;
    }

    public void setQualityRemark(String qualityRemark) {
        this.qualityRemark = qualityRemark;
    }

    public int getQualityResult() {
        return qualityResult;
    }

    public void setQualityResult(int qualityResult) {
        this.qualityResult = qualityResult;
    }

    public int getQualityType() {
        return qualityType;
    }

    public void setQualityType(int qualityType) {
        this.qualityType = qualityType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
