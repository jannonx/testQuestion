package com.guyuan.dear.busbean;


import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;

import java.util.List;

/**
 * created by tl
 * created at 2020/7/15
 * 接口获取未读或未处理消息列表
 */
public class MessageUnreadBusBean extends MessageBusBean{
    private int unreadNumber;
    private List<MessageBean> messageBeanList;

    public int getUnreadNumber() {
        return unreadNumber;
    }

    public void setUnreadNumber(int unreadNumber) {
        this.unreadNumber = unreadNumber;
    }

    public List<MessageBean> getMessageBeanList() {
        return messageBeanList;
    }

    public void setMessageBeanList(List<MessageBean> messageBeanList) {
        this.messageBeanList = messageBeanList;
    }
}
