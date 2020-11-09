package com.guyuan.dear.focus.produce.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/9 10:22
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProduceOverViewBean implements Serializable {
    /**
     * 生产异常的生产计划数
     */
    private int abnormalNum;
    /**
     * 已完成生产的生产计划数
     */
    private int completeNum;
    /**
     * 完成率
     */
    private String completionRate;
    /**
     * 待生产的生产计划数
     */
    private int prepareNum;
    /**
     * 生产中的生产计划数
     */
    private int processingNum;
    /**
     * 生产拖期的生产计划数
     */
    private int tardinessNum;
    /**
     * 生产计划总数
     */
    private int totalNum;

    public int getAbnormalNum() {
        return abnormalNum;
    }

    public void setAbnormalNum(int abnormalNum) {
        this.abnormalNum = abnormalNum;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public String getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(String completionRate) {
        this.completionRate = completionRate;
    }

    public int getPrepareNum() {
        return prepareNum;
    }

    public void setPrepareNum(int prepareNum) {
        this.prepareNum = prepareNum;
    }

    public int getProcessingNum() {
        return processingNum;
    }

    public void setProcessingNum(int processingNum) {
        this.processingNum = processingNum;
    }

    public int getTardinessNum() {
        return tardinessNum;
    }

    public void setTardinessNum(int tardinessNum) {
        this.tardinessNum = tardinessNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
