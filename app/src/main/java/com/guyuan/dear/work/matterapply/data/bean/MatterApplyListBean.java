package com.guyuan.dear.work.matterapply.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 21:05
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyListBean {

    private int pageNum;
    private int pageSize;
    private int totalPages;
    private int totalSize;
    private String updateTime;

    private List<MatterApplyBean> content;

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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<MatterApplyBean> getContent() {
        return content;
    }

    public void setContent(List<MatterApplyBean> content) {
        this.content = content;
    }

}