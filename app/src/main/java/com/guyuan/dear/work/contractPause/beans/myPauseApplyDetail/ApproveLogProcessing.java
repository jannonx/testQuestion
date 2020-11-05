package com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail;

import com.guyuan.dear.work.contractPause.beans.StaffBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:51
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ApproveLogProcessing {
    private StaffBean currentTurn;

    public StaffBean getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(StaffBean currentTurn) {
        this.currentTurn = currentTurn;
    }
}
