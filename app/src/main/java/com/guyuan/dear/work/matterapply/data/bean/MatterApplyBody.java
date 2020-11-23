package com.guyuan.dear.work.matterapply.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 20:48
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyBody {

    /**
     * approvalStatus : 0
     * approveBy : 0
     * id : 0
     * materialId : 0
     * number : 0
     * productId : 0
     * projectId : 0
     * taskType : 0
     */

    private int approvalStatus;         //审批状态：1.审批中，2.审批通过，3.审批被驳回
    private int approveBy;              //审批人id
    private int id;                     //主键id
    private int materialId;             //采购原材料id
    private int number;                 //领用数量
    private int productId;              //产品id
    private int projectId;              //项目id
    private int taskType;               //任务类型1.自制生产2.外购生产3.部分外协4.总体设计

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }
}