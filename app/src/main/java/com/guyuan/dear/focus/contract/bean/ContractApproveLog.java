package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 11:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApproveLog extends Vote {
    private String dept;
    private String comment;
    private long date;

    public ContractApproveLog() {
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
