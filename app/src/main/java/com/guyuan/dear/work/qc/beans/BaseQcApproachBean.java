package com.guyuan.dear.work.qc.beans;

import com.guyuan.dear.net.resultBeans.NetQcApproach;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 14:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseQcApproachBean {
    private String approachId;
    private String approachName;

    public BaseQcApproachBean() {
    }

    public BaseQcApproachBean(NetQcApproach src) {
        this.approachId = src.getKey();
        this.approachName = src.getValue();
    }

    public String getApproachId() {
        return approachId;
    }

    public void setApproachId(String approachId) {
        this.approachId = approachId;
    }

    public String getApproachName() {
        return approachName;
    }

    public void setApproachName(String approachName) {
        this.approachName = approachName;
    }
}
