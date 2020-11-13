package com.guyuan.dear.focus.qc.beans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/11 17:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSummaryBean {
    private long startPeriod;
    private long endPeriod;
    private ProductQcSumBean productSum;
    private MaterialQcSumBean materialSum;

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

    public ProductQcSumBean getProductSum() {
        return productSum;
    }

    public void setProductSum(ProductQcSumBean productSum) {
        this.productSum = productSum;
    }

    public MaterialQcSumBean getMaterialSum() {
        return materialSum;
    }

    public void setMaterialSum(MaterialQcSumBean materialSum) {
        this.materialSum = materialSum;
    }
}
