package com.example.httplibrary.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 14:22
 * @company: 固远（深圳）信息技术有限公司
 **/
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
