package com.guyuan.dear.work.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/11 10:30
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessListBean {
    private int pageNum;
    private int pageSize;
    private int totalSize;
    private List<WorkAssessItemBean> content;

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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<WorkAssessItemBean> getContent() {
        return content;
    }

    public void setContent(List<WorkAssessItemBean> content) {
        this.content = content;
    }
}