package com.guyuan.dear.busbean;

import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;


/**
 * created by tl
 * created at 2020/7/15
 * 消息推送bean
 */
public class MessagePushBusBean extends MessageBusBean{

    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }
}
