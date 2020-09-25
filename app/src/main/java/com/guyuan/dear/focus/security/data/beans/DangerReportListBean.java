package com.guyuan.dear.focus.security.data.beans;

import java.util.List;

/**
 * Created by TL
 * on 2019/12/2
 */
public class DangerReportListBean {

    /**
     * pageNum : 1
     * pageSize : 30
     * totalSize : 1
     * totalPages : 1
     * content : [{"id":352,"degree":1,"securityId":25,"reason":"安全点报警内容,安全点报警内容","imgUrl":null,"note":"",
     * "status":1,"createName":"admin","createPhone":"13889700023","createTime":1591846579000,
     * "tSecurityBaseVo":{"id":25,"name":"货梯","code":"AQ001","factoryName":"东莞厂区","workshopName":"5栋8楼",
     * "typeName":"电气危险区域","dutyName":"宣观秀","dutyPhone":"18124855679","urgentName":null,"urgentPhone":null,
     * "qrCode":"159178407883512708","status":1,"createTime":1591782705000,"imgUrl":"159178399565245136",
     * "cameraChannel":"15","dvrSeriesNum":"E22840701"}}]
     */

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPages;
    private List<SecurityContentBean> content;

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

    public List<SecurityContentBean> getContent() {
        return content;
    }

    public void setContent(List<SecurityContentBean> content) {
        this.content = content;
    }

}
