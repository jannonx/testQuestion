package com.guyuan.dear.focus.projectsite.data;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/19 10:48
 * @company: 固远（深圳）信息技术有限公司
 */
@lombok.NoArgsConstructor
@lombok.Data
public class ProjectOverViewBean {

    /**
     * prospectNumber : 2
     * goodsNumber : 1
     * safetyNumber : 1
     * installNumber : 0
     * cusNumber : 0
     */

    private Integer prospectNumber;
    private Integer goodsNumber;
    private Integer safetyNumber;
    private Integer installNumber;
    private Integer cusNumber;

    public Integer getProspectNumber() {
        return prospectNumber;
    }

    public void setProspectNumber(Integer prospectNumber) {
        this.prospectNumber = prospectNumber;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getSafetyNumber() {
        return safetyNumber;
    }

    public void setSafetyNumber(Integer safetyNumber) {
        this.safetyNumber = safetyNumber;
    }

    public Integer getInstallNumber() {
        return installNumber;
    }

    public void setInstallNumber(Integer installNumber) {
        this.installNumber = installNumber;
    }

    public Integer getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(Integer cusNumber) {
        this.cusNumber = cusNumber;
    }
}