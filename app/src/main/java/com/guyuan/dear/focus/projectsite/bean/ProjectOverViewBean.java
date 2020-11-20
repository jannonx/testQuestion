package com.guyuan.dear.focus.projectsite.bean;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/19 10:48
 * @company: 固远（深圳）信息技术有限公司
 */

public class ProjectOverViewBean {

    /**
     * 现场勘查报告数
     */
    private int prospectNumber;
    /**
     * 货物清点报告数
     */
    private int goodsNumber;
    /**
     * 安全报告数
     */
    private int safetyNumber;
    /**
     * 客户报告数
     */
    private int installNumber;
    /**
     * 客户报告数
     */
    private int cusNumber;

    public String getProspectNumber() {
        return String.valueOf(prospectNumber);
    }

    public void setProspectNumber(int prospectNumber) {
        this.prospectNumber = prospectNumber;
    }

    public String getGoodsNumber() {
        return String.valueOf(goodsNumber);
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getSafetyNumber() {
        return String.valueOf(safetyNumber);
    }

    public void setSafetyNumber(int safetyNumber) {
        this.safetyNumber = safetyNumber;
    }

    public String getInstallNumber() {
        return String.valueOf(installNumber);
    }

    public void setInstallNumber(int installNumber) {
        this.installNumber = installNumber;
    }

    public String getCusNumber() {
        return String.valueOf(cusNumber);
    }

    public void setCusNumber(int cusNumber) {
        this.cusNumber = cusNumber;
    }
}