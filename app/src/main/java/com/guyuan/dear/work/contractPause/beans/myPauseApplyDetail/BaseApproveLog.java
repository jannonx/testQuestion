package com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:44
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseApproveLog {
    private int type;
    private String json;
    public static final int LOG_TYPE_FIRST_CREATE_DATE=0;
    public static final int LOG_TYPE_TO_BE_PROCESS=1;
    public static final int LOG_TYPE_PROCESSING=2;
    public static final int LOG_TYPE_REJECT=3;
    public static final int LOG_TYPE_APPROVED=4;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
