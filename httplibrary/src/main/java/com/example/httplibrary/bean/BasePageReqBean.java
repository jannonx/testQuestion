package com.example.httplibrary.bean;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public class BasePageReqBean {

    /**
     * pageNum : 1
     * pageSize : 50
     * updateTime :"2020-11-09 14:33:03"
     */

    private int pageNum;
    private int pageSize;
    private String updateTime;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
