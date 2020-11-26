package com.guyuan.dear.message.data.bean;

import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;

import java.util.List;

/**
 * Created by TL
 * on 2019/12/19
 */
public class MessageUnreadBean {

  /**
   * number : 2
   * newMessage : [{"id":2,"msgType":1,"sendType":1,"businessId":1,"msgTitle":"[是谁呢下]我都钉钉",
   * "msgContent":"其实有时候我完不成任务或者工作，也不是因为懒什么的，我就是能力不行","createBy":24,"sendTime":"2019-12-17
   * 02:48:12","createTime":"2019-12-17 02:48:16","lastUpdateBy":null,
   * "lastUpdateTime":"2019-12-17 02:48:21","myMsgStatus":null,"messageInfos":null}]
   */

  private int number;
  private List<MessageBean> newMessage;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public List<MessageBean> getNewMessage() {
    return newMessage;
  }

  public void setNewMessage(List<MessageBean> newMessage) {
    this.newMessage = newMessage;
  }

  @Override
  public String toString() {
    return "MessageUnreadBean{" +
        "number=" + number +
        ", newMessage=" + newMessage +
        '}';
  }
}
