package com.guyuan.dear.focus.qc.beans;

/**
 * @author: 廖华凯
 * @description: 表示一段时间的原材料质量概况
 * @since: 2020/11/11 17:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcSumBean {
    private long startPeriod;
    private long endPeriod;
    private int sampleSize;
    private int passNum;
    private int rejectNum;

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public int getRejectNum() {
        return rejectNum;
    }

    public void setRejectNum(int rejectNum) {
        this.rejectNum = rejectNum;
    }

    public long getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(long startPeriod) {
        this.startPeriod = startPeriod;
    }

    public long getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(long endPeriod) {
        this.endPeriod = endPeriod;
    }
}
