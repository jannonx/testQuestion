package com.guyuan.dear.focus.purchase.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 16:33
 * @company : 固远（深圳）信息技术有限公司
 **/

public class PurchaseDetailBean {

    private String arriveTime;         //采购合同到货时间
    private String code;               //商品代号
    private String contractCode;       //采购合同编号
    private String createName;         //操作人（采购合同创建人）
    private String createTime;         //采购合同创建时间
    private int exchangeSum;           //换货总数
    private String followName;         //采购跟进人名称
    private int id;                    //主键id
    private String material;           //商品材质
    private String modelCode;          //商品规格型号
    private String name;               //商品名称
    private int number;                //采购数量
    private String projectCode;        //项目编号
    private String projectName;        //项目名称
    private int qualityResult;         //质检结果：1.合格，2.不合格
    private String reason;             //退货原因
    private int receiveStatus;         //签收状态：1.待签收，2.已签收
    private int returnSum;             //退货总数
    private String signName;           //签订人名称
    private String signTime;           //采购合同签订时间
    private String suppName;           //供应商名称
    private int type;                  //商品类型：1.原材料，2.配套设备
    private int status;                //即时状态：1.退货，2.换货，3.待签收，4.已到货，5.拖期

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private List<PurchaseRecordBean> recordsList;

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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

    public int getExchangeSum() {
        return exchangeSum;
    }

    public void setExchangeSum(int exchangeSum) {
        this.exchangeSum = exchangeSum;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getQualityResult() {
        return qualityResult;
    }

    public void setQualityResult(int qualityResult) {
        this.qualityResult = qualityResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public int getReturnSum() {
        return returnSum;
    }

    public void setReturnSum(int returnSum) {
        this.returnSum = returnSum;
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

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<PurchaseRecordBean> getRecordsList() {
        return recordsList;
    }

    public void setRecordsList(List<PurchaseRecordBean> recordsList) {
        this.recordsList = recordsList;
    }
}