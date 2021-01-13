package com.guyuan.dear.umeng.beans;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/13 11:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseOrRestartApplyStateUpdate {

    private int examineId;
    /**
     * 0:驳回 1：待审批 2：通过
     */
    private int newState;

    public int getExamineId() {
        return examineId;
    }

    public void setExamineId(int examineId) {
        this.examineId = examineId;
    }

    public int getNewState() {
        return newState;
    }

    public void setNewState(int newState) {
        this.newState = newState;
    }
}
