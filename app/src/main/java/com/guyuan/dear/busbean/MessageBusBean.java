package com.guyuan.dear.busbean;

/**
 * created by tl
 * created at 2020/7/15
 */
public class MessageBusBean {
    public static final int SMART_CONTROL_NOT_HANDLE = -100;    //智能管控未处理
    public static final int SMART_CONTROL_UNREAD = -101;          //智能管控未读
    public static final int OFFICE = 0;//移动办公
    public static final int SMART_CONTROL_WARNING = 1;//警告
    public static final int SMART_CONTROL_SERIOUS = 2;//严重
    public static final int WORK = 3; //我的工作

    private int messageType;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

}
