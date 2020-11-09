package com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail;

import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyDetailBean extends MyPauseApplyBean {
    private String detailCause;
    private List<BaseApproveLog> logs;

    public String getDetailCause() {
        return detailCause;
    }

    public void setDetailCause(String detailCause) {
        this.detailCause = detailCause;
    }

    public List<BaseApproveLog> getLogs() {
        return logs;
    }

    public void setLogs(List<BaseApproveLog> logs) {
        this.logs = logs;
    }
}
