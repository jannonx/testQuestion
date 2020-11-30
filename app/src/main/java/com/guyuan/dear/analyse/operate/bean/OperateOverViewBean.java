package com.guyuan.dear.analyse.operate.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/30 14:03
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateOverViewBean implements Serializable {


    /**
     * totalSales : 10335217222
     * totalCollection : 0
     * totalCost : 0
     * lastTotalSales : ∞%
     * lastTotalCollection : 0%
     * lastTotalCost : 0%
     */

    private long totalSales;
    private long totalCollection;
    private long totalCost;
    private String lastTotalSales;
    private String lastTotalCollection;
    private String lastTotalCost;
    private OperateType operateType;
    /**
     * 搜索时间
     */
    private String searchTime;

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public String getTotalSales() {
        return String.valueOf(totalSales);
    }

    public void setTotalSales(long totalSales) {
        this.totalSales = totalSales;
    }

    public String getTotalCollection() {
        return String.valueOf(totalCollection);
    }

    public void setTotalCollection(long totalCollection) {
        this.totalCollection = totalCollection;
    }

    public String getTotalCost() {
        return String.valueOf(totalCost);
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public String getLastTotalSales() {
        return lastTotalSales;
    }

    public void setLastTotalSales(String lastTotalSales) {
        this.lastTotalSales = lastTotalSales;
    }

    public String getLastTotalCollection() {
        return lastTotalCollection;
    }

    public void setLastTotalCollection(String lastTotalCollection) {
        this.lastTotalCollection = lastTotalCollection;
    }

    public String getLastTotalCost() {
        return lastTotalCost;
    }

    public void setLastTotalCost(String lastTotalCost) {
        this.lastTotalCost = lastTotalCost;
    }
}
