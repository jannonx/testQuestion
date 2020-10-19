package com.guyuan.dear.focus.sales.bean;

import java.util.List;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同列表
 * @since: 2020/10/12 10:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractBaseBean {
    /**
     * 合同编号
     */
    private String contractId;
    /**
     * 销售人员
     */
    private String salesPerson;
    /**
     * 买方
     */
    private String buyer;
    /**
     * 合同签订日期
     */
    private long date;
    /**
     * 设备名称
     */
    private String productName;
    /**
     * 设备型号
     */
    private String productModel;
    /**
     * 合同订单标签,如：生产中，生产完成，评审中....等
     */
    private List<String> tags;

    @Transient
    public static final int CONTRACT_TYPE_PRE_ANNUAL_DELIVERS = 0;
    @Transient
    public static final int CONTRACT_TYPE_NEW_CONTRACTS = 1;
    @Transient
    public static final int CONTRACT_TYPE_FINISHED_CONTRACTS = 2;
    @Transient
    public static final int CONTRACT_TYPE_EXECUTING_CONTRACTS = 3;
    @Transient
    public static final int CONTRACT_TYPE_UNFINISHED_CONTRACTS = 4;
    @Transient
    public static final int CONTRACT_TYPE_EXCEPTION_CONTRACTS = 5;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }
}
