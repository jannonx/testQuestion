package com.guyuan.dear.work.goodssign.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/18 12:21
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsDetailBean {

    private String code;                //商品代号
    private int contractId;             //采购合同id
    private String equipmentCode;       //父级配套设备代号
    private int exchangeNum;            //换货数量
    private int exchangeSum;            //换货总数
    private int id;                     //主键id
    private String material;            //商品材质
    private String modelCode;           //商品规格型号
    private String name;                //商品名称
    private int purchaseNum;            //商品采购数量
    private int qualityNum;             //质检判定数量
    private int qualityResult;          //质检结果：1.合格，2.不合格
    private int qualityStatus;          //质检状态：1.已完成质检，2.未完成质检
    private String reason;              //原因
    private int receiveNum;             //到货数量（不另外计算退换货再到货的数量）
    private int receiveStatus;          //签收状态：1.待签收，2.已签收
    private String remark;              //备注
    private int returnFlag;             //有无退换货标记：0.无，1.有
    private int returnNum;              //退货数量
    private int returnSum;              //退货总数
    private int taskType;               //任务类型：1.自制生产2.外购生产3.部分外协4.总体设计
    private int type;                   //商品类型：1.原材料，2.配套设备
    private String unit;                //商品单位

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public int getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(int exchangeNum) {
        this.exchangeNum = exchangeNum;
    }

    public int getExchangeSum() {
        return exchangeSum;
    }

    public void setExchangeSum(int exchangeSum) {
        this.exchangeSum = exchangeSum;
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

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public int getQualityNum() {
        return qualityNum;
    }

    public void setQualityNum(int qualityNum) {
        this.qualityNum = qualityNum;
    }

    public int getQualityResult() {
        return qualityResult;
    }

    public void setQualityResult(int qualityResult) {
        this.qualityResult = qualityResult;
    }

    public int getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(int qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(int returnFlag) {
        this.returnFlag = returnFlag;
    }

    public int getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }

    public int getReturnSum() {
        return returnSum;
    }

    public void setReturnSum(int returnSum) {
        this.returnSum = returnSum;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}