package com.guyuan.dear.umeng.beans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/30 19:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class UmengMessageWrapper {
    private int msgType;
    private String json;
    public static final int MSG_TYPE_PUSH_MSG_CENTER=10;
    public static final int MSG_TYPE_PUSH_LOG_OUT = 11;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
