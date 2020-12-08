package com.guyuan.dear.work.qc.beans;

import com.guyuan.dear.net.resultBeans.NetProductInfo;

import java.util.ArrayList;
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
    /**
     * 提交qc报告时用
     */
    private int submitId;

    public BaseProductBatchInfo(NetProductInfo src) {
        setBatchId(src.getSerialNumber());
        products = new ArrayList<>();
        ProductInfo info = new ProductInfo(src);
        products.add(info);
        setSubmitId(src.getId());
    }

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

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public long getTotalNumber(){
        long count=0;
        if(products!=null&&!products.isEmpty()){
            for (ProductInfo info : products) {
                count+=info.getQuantity();
            }
        }
        return count;
    }
}
