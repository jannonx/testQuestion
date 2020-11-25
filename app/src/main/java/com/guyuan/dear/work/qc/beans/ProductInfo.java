package com.guyuan.dear.work.qc.beans;

import com.guyuan.dear.net.resultBeans.NetProductInfo;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 14:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductInfo {
    private String productId;
    private String productName;
    private int quantity;
    private String unit;

    public ProductInfo(NetProductInfo src) {
        setProductId(src.getProductCode());
        setProductName(src.getProductName());
        setQuantity(src.getProductNum());
        setUnit(src.getProductUnit());
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
