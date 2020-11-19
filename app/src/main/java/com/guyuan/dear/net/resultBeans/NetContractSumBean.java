package com.guyuan.dear.net.resultBeans;

import com.google.gson.annotations.SerializedName;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/18 19:15
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractSumBean {

    /**
     * cusNum : 5
     * contNum : 10
     * lastYearNum : 0
     * thisYearNum : 10
     * completeNum : 5
     * inNum : 5
     * executionNum : 0
     */

    private int cusNum;
    private int contNum;
    private int lastYearNum;
    private int thisYearNum;
    private int completeNum;
    @SerializedName("inNum")
    private int executingCount;
    @SerializedName("executionNum")
    private int exceptionCount;

    public int getCusNum() {
        return cusNum;
    }

    public void setCusNum(int cusNum) {
        this.cusNum = cusNum;
    }

    public int getContNum() {
        return contNum;
    }

    public void setContNum(int contNum) {
        this.contNum = contNum;
    }

    public int getLastYearNum() {
        return lastYearNum;
    }

    public void setLastYearNum(int lastYearNum) {
        this.lastYearNum = lastYearNum;
    }

    public int getThisYearNum() {
        return thisYearNum;
    }

    public void setThisYearNum(int thisYearNum) {
        this.thisYearNum = thisYearNum;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public int getExecutingCount() {
        return executingCount;
    }

    public void setExecutingCount(int executingCount) {
        this.executingCount = executingCount;
    }

    public int getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(int exceptionCount) {
        this.exceptionCount = exceptionCount;
    }
}
