package com.guyuan.dear.focus.security.data.beans;

import java.util.List;

/**
 * created by tl
 * created at 2020/6/12
 */
public class DangerTypeBean {

    /**
     * pageNum : 1
     * pageSize : 100
     * totalSize : 1
     * totalPages : 1
     * content : [{"id":25,"name":"货梯","code":"AQ001","factoryName":"东莞厂区","workshopName":"5栋8楼",
     * "typeName":"电气危险区域","dutyName":"宣观秀","dutyPhone":"18124855679","urgentName":null,"urgentPhone":null,
     * "qrCode":"159178407883512708","status":1,"createTime":1591782705000,"imgUrl":"159178399565245136",
     * "cameraChannel":"15","dvrSeriesNum":"E22840701"}]
     */

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPages;
    private List<SecurityBaseBean> content;

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

    public List<SecurityBaseBean> getContent() {
        return content;
    }

    public void setContent(List<SecurityBaseBean> content) {
        this.content = content;
    }

}
