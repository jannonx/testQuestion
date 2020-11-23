package com.guyuan.dear.work.matterapply.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 21:01
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyBean {

    private int approvalStatus;                //审批状态：1.审批中，2.审批通过，3.审批被驳回
    private int approveBy;                     //审批人id
    private String approveName;                //审批人名称
    private int createBy;                      //创建人id
    private String createName;                 //创建人名称
    private String createTime;                 //创建时间
    private int id;                            //主键id
    private String material;                   //原材料材质
    private String materialCode;               //原材料代号
    private int materialId;                    //采购原材料id
    private String materialName;               //原材料名称
    private String modelCode;                  //原材料规格型号
    private int number;                        //领用数量
    private int productId;                     //产品id
    private String productName;                //产品名称
    private String projectCode;                //项目编号
    private int projectId;                     //项目id
    private String projectName;                //项目名称
    private int purchaseNum;                   //原材料库存数量
    private String remark;                     //原材料备注
    private int taskType;                      //任务类型1.自制生产2.外购生产3.部分外协4.总体设计
    private String unit;                       //原材料单位

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public int getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(int approveBy) {
        this.approveBy = approveBy;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}