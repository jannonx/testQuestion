package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/19 12:10
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetSearchContactInfo extends NetBaseContractInfo {

    /**
     * cusName :
     * salesman :
     * signTime :
     * state : 0
     */

    private String cusName;
    private String salesman;
    private String signTime;
    /**
     * 0 正常执行 1 质保金异常 2 验收合格 3 暂停
     */
    private int state;
    /**
     * 1合同暂停,2质保金回不来
     */
    private int abnormalCode;

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAbnormalCode() {
        return abnormalCode;
    }

    public void setAbnormalCode(int abnormalCode) {
        this.abnormalCode = abnormalCode;
    }
}
