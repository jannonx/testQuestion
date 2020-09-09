package com.guyuan.dear.customizeview.autoscrollrecyclerview;

/**
 * created by tl
 * created at 2020/6/7
 */
public class MessageInfosBean {
    /**
     * id : 1
     * messageId : 1
     * sendUser : 1
     * createBy : 24
     * sendTime : 2019-12-17 02:24:41
     * getTime : null
     * msgStatus : 1
     * createTime : null
     * lastUpdateBy : null
     * lastUpdateTime : null
     */

    private int id;
    private int messageId;
    private int sendUser;
    private int createBy;
    private String sendTime;
    private Object getTime;
    private int msgStatus;        //1:已读 其他：未读
    private Object createTime;
    private Object lastUpdateBy;
    private Object lastUpdateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSendUser() {
        return sendUser;
    }

    public void setSendUser(int sendUser) {
        this.sendUser = sendUser;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Object getGetTime() {
        return getTime;
    }

    public void setGetTime(Object getTime) {
        this.getTime = getTime;
    }

    public int getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(int msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Object lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Object getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Object lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
