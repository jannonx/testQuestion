package com.guyuan.dear.analyse.operate.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/30 14:08
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateAnalyseBean implements Serializable {
    /**
     * 客户名称
     */
    private long id;
    /**
     * 客户名称
     */
    private String cusName;
    /**
     * 成本
     */
    private long totalCost;
    /**
     * 合同名称
     */
    private String projectName;
    /**
     * 状态,0审批中，1审批通过，2审批拒绝
     */
    private String state;
    /**
     * 回款金额
     */
    private long tradeReceivables;
    /**
     * 成本核算的月份
     */
    private String monthTime;
    /**
     * 原材料成本
     */
    private long materialCosts;
    /**
     * 制造成本
     */
    private long manufacturingCosts;
    /**
     * 人力成本
     */
    private long laborCost;
    /**
     * 安装成本
     */
    private long installationCost;

    private OperateType operateType;

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getTotalCost() {
        return String.valueOf(totalCost);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTradeReceivables() {
        return String.valueOf(tradeReceivables);
    }

    public void setTradeReceivables(int tradeReceivables) {
        this.tradeReceivables = tradeReceivables;
    }

    public String getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(String monthTime) {
        this.monthTime = monthTime;
    }

    public String getMaterialCosts() {
        return String.valueOf(materialCosts);
    }

    public void setMaterialCosts(int materialCosts) {
        this.materialCosts = materialCosts;
    }

    public String getManufacturingCosts() {
        return String.valueOf(manufacturingCosts);
    }

    public void setManufacturingCosts(int manufacturingCosts) {
        this.manufacturingCosts = manufacturingCosts;
    }

    public String getLaborCost() {
        return String.valueOf(laborCost);
    }

    public void setLaborCost(int laborCost) {
        this.laborCost = laborCost;
    }

    public String getInstallationCost() {
        return String.valueOf(installationCost);
    }

    public void setInstallationCost(int installationCost) {
        this.installationCost = installationCost;
    }
}
