package com.guyuan.dear.work.qc.beans;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 14:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseProductBatchInfo {
    private String batchId;
    private List<ProductInfo> products;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}
