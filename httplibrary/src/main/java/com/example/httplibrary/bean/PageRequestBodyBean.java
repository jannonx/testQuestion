package com.example.httplibrary.bean;

import java.util.Map;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
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
