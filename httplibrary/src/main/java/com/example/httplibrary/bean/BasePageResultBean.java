package com.example.httplibrary.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 14:10
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BasePageResultBean<T> {
    @SerializedName("pageNum")
    private int pageIndex;
    private int pageSize;
    private int totalSize;
    private int totalPages;
    private List<T> content;
    private String updateTime;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
