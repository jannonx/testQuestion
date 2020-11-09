package com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail;

import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:48
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ApproveLogToBePrc {
    private List<StaffBean> sendList;

    public List<StaffBean> getSendList() {
        return sendList;
    }

    public void setSendList(List<StaffBean> sendList) {
        this.sendList = sendList;
    }
}
