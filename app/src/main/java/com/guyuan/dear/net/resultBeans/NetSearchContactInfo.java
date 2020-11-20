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
    private int state;

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


}
