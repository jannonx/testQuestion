package com.guyuan.dear.work.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/13 11:18
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MeetingRoomBean {

    /**
     * pageNum : 1
     * pageSize : 0
     * totalSize : 8
     * totalPages : 0
     * content : []
     * updateTime : null
     */

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPages;
    private Object updateTime;
    private List<RoomBean> content;

    public List<RoomBean> getContent() {
        return content;
    }

    public void setContent(List<RoomBean> content) {
        this.content = content;
    }

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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }


}