package com.guyuan.dear.focus.qc.beans.verfifyLog;

import com.guyuan.dear.net.resultBeans.NetQcReportApproveFlow;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/1 14:25
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LogTypePendingForVerify {
    private String name;
    private String imgUrl;
    private String comment;
    private String dept;

    public LogTypePendingForVerify() {
    }

    public LogTypePendingForVerify(NetQcReportApproveFlow src) {
        setName(src.getCreateName());
        setImgUrl(src.getImgUrl());
        setDept(src.getCreateDept());
        setComment(src.getRemark());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
