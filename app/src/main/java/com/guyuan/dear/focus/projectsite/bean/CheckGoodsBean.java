package com.guyuan.dear.focus.projectsite.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/20 15:52
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsBean implements Serializable {


    /**
     * 清单产品明细主键ID
     */
    private long id;
    /**
     * 产品名称
     */
    private String projectName;
    /**
     * 产品代号
     */
    private int projectCode;
    /**
     * 规格型号
     */
    private String model;
    /**
     * 清点人员名称
     */
    private String checkName;
    /**
     * 清点异常原因备注
     */
    private String checkRemark;
    /**
     * 清点是否异常，0正常，1异常
     */
    private int isException;
    /**
     * 单位
     */
    private String unit;
    /**
     * 数量
     */
    private int amount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 清点确认状态(0:正常,1:异常)
     */
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(int projectCode) {
        this.projectCode = projectCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
