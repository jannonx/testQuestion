package com.guyuan.dear.focus.purchase.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 16:33
 * @company : 固远（深圳）信息技术有限公司
 **/

public class PurchaseContentBean {

    /**
     * arriveTime :
     * code :
     * contractCode :
     * createName :
     * createTime :
     * exchangeSum : 0
     * followName :
     * id : 0
     * material :
     * modelCode :
     * name :
     * number : 0
     * projectCode :
     * projectName :
     * qualityResult : 0
     * reason :
     * receiveStatus : 0
     * recordsList : [{"createName":"","createTime":"","deptName":"","operateNum":0,"reason":"","type":0}]
     * returnSum : 0
     * signName :
     * signTime :
     * suppName :
     * type : 0
     */

    private String arriveTime;
    private String code;
    private String contractCode;
    private String createName;
    private String createTime;
    private int exchangeSum;
    private String followName;
    private int id;
    private String material;
    private String modelCode;
    private String name;
    private int number;
    private String projectCode;
    private String projectName;
    private int qualityResult;
    private String reason;
    private int receiveStatus;
    private int returnSum;
    private String signName;
    private String signTime;
    private String suppName;
    private int type;
    /**
     * createName :
     * createTime :
     * deptName :
     * operateNum : 0
     * reason :
     * type : 0
     */

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