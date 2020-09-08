package com.example.httplibrary.bean;

import java.util.Map;

/**
 * @author 廖华凯
 * @since 2019/11/25 17:20 部分SmartFactory网络请求需要在body中提供一个空的PageRequest对象 by奶茶宝宝
 **/
public class PageRequestBodyBean {

    /**
     * columnFilters : {"additionalProp1":{"name":"string","value":"string"},"additionalProp2":{"name":"string","value":"string"},"additionalProp3":{"name":"string","value":"string"}}
     * pageNum : 0
     * pageSize : 0
     */

    private Map<String, Map<String, String>> columnFilters;
    private int pageNum;
    private int pageSize;


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Map<String, String>> getColumnFilters() {
        return columnFilters;
    }

    public void setColumnFilters(Map<String, Map<String, String>> columnFilters) {
        this.columnFilters = columnFilters;
    }
}
