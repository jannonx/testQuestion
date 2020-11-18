package com.guyuan.dear.work.goodssign.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/18 11:29
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignDetailBean {

    private int approvalStatus;          //采购合同审批状态：0.审批中，1.审批通过，2.审批被驳回，3.保存为草稿
    private String arrivalTime;          //到货时间
    private String contractCode;         //采购合同编号
    private int equipmentPrice;          //配套设备总价
    private String followName;           //跟进人名称
    private int id;                      //主键id
    private String imgUrl;               //附件url，用英文逗号隔开
    private int materialPrice;           //原材料总价
    private String projectCode;          //项目编号
    private String projectName;          //项目名称
    private String remark;               //备注
    private String signName;             //签订人名称
    private String signTime;             //签订时间
    private String supplierAddr;         //供应商详细住址
    private String supplierName;         //供应商名称
    private String supplyScope;          //供货范围
    private String updateTime;           //最后修改时间
    private List<GoodsSignBean> equipmentList;  //采购配套设备列表
    private List<GoodsSignBean> materialList;   //采购原材料列表

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public int getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(int equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
    }

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName;
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

    public int getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(int materialPrice) {
        this.materialPrice = materialPrice;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSupplierAddr() {
        return supplierAddr;
    }

    public void setSupplierAddr(String supplierAddr) {
        this.supplierAddr = supplierAddr;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplyScope() {
        return supplyScope;
    }

    public void setSupplyScope(String supplyScope) {
        this.supplyScope = supplyScope;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<GoodsSignBean> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<GoodsSignBean> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<GoodsSignBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<GoodsSignBean> materialList) {
        this.materialList = materialList;
    }

}