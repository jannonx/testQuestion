package com.guyuan.dear.net.resultBeans;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 9:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetBaseQcBean {

    /**
     * approvalStatus : 0
     * createTime :
     * equipmentCode :
     * equipmentName :
     * id : 0
     * imgUrl :
     * material :
     * materialRemark :
     * model :
     * productCode :
     * productName :
     * productNum : 0
     * productType : 0
     * productUnit :
     * projectCode :
     * projectId : 0
     * projectName :
     * qualityAvatar :
     * qualityBy : 0
     * qualityCondition : []
     * qualityName :
     * qualityNum : 0
     * qualityRemark :
     * qualityResult : 0
     * qualityType : 0
     * serialNumber :
     * subCodeId : 0
     * updateTime :
     */

    /**
     * 审批状态：1.无需审批，2.待审批，3.审批通过，4.审批被驳回
     */
    private int approvalStatus;
    private String createTime;
    private int id;
    private String imgUrl;
    private String material;
    private String materialRemark;
    private String model;
    private String productCode;
    private String productName;
    private int productNum;
    /**
     * 商品类型：1.原材料，2.配套设备
     */
    private int productType;
    private String productUnit;
    private String projectCode;
    private int projectId;
    private String projectName;
    private String qualityAvatar;
    private int qualityBy;
    private String qualityName;
    private int qualityNum;
    private String qualityRemark;
    /**
     * 质检判断结果：0.未质检，1.合格，2.不合格
     */
    private int qualityResult;
    /**
     * 质检方式：1.全检，2.抽检，3.按标准质检文件
     */
    private int qualityType;
    /**
     * 出厂编号
     */
    private String serialNumber;
    /**
     * 商品id
     */
    private int subCodeId;
    private String updateTime;
    /**
     * 1,设计图样 2 国家标准
     */
    private List<Integer> qualityCondition;

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialRemark() {
        return materialRemark;
    }

    public void setMaterialRemark(String materialRemark) {
        this.materialRemark = materialRemark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getQualityAvatar() {
        return qualityAvatar;
    }

    public void setQualityAvatar(String qualityAvatar) {
        this.qualityAvatar = qualityAvatar;
    }

    public int getQualityBy() {
        return qualityBy;
    }

    public void setQualityBy(int qualityBy) {
        this.qualityBy = qualityBy;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSubCodeId() {
        return subCodeId;
    }

    public void setSubCodeId(int subCodeId) {
        this.subCodeId = subCodeId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getQualityCondition() {
        return qualityCondition;
    }

    public void setQualityCondition(List<Integer> qualityCondition) {
        this.qualityCondition = qualityCondition;
    }
}
