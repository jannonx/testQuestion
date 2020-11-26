package com.guyuan.dear.message.data.bean;

import java.util.List;

/**
 * Created by TL
 * on 2019/12/18
 */
public class MessageDetailBean {

  /**
   * id : 1
   * msgType : 1
   * sendType : 1
   * businessId : 1
   * msgTitle : [合同究极爆炸]我打算大苏打
   * msgContent :
   * 大蒜能治疗各种疾病，含有一定量的微量元素，对心脑血管疾病有一定的预防作用，同时还能一定程度上抑制肿瘤的产生。大蒜能控制“三高”，可以起到降低胆固醇的效果，同时达到降血脂的目的。用醋泡大蒜，每天早晚吃几瓣，还能起到缓解高血压的作用。同时平时生吃大蒜还能起到控制血糖的效果。
   * createBy : 24
   * sendTime : null
   * createTime : null
   * lastUpdateBy : null
   * lastUpdateTime : null
   * messageInfos : [{"id":1,"messageId":1,"sendUser":1,"createBy":24,"sendTime":1576549481000,
   * "getTime":null,"msgStatus":1,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null}]
   */

  private int id;
  private int msgType;
  private int sendType;
  private int businessId;
  private String msgTitle;
  private String msgContent;
  private int createBy;
  private int label;             //0:办公消息 1:智能管控——警告消息 2:智能管控——预警消息 3:我的工作消息
  private String sendTime;
  private String createTime;
  private Object lastUpdateBy;
  private Object lastUpdateTime;
  private List<MessageInfosBean> messageInfos;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMsgType() {
    return msgType;
  }

  public void setMsgType(int msgType) {
    this.msgType = msgType;
  }

  public int getSendType() {
    return sendType;
  }

  public void setSendType(int sendType) {
    this.sendType = sendType;
  }

  public int getBusinessId() {
    return businessId;
  }

  public void setBusinessId(int businessId) {
    this.businessId = businessId;
  }

  public String getMsgTitle() {
    return msgTitle;
  }

  public void setMsgTitle(String msgTitle) {
    this.msgTitle = msgTitle;
  }

  public int getLabel() {
    return label;
  }

  public void setLabel(int label) {
    this.label = label;
  }

  public String getMsgContent() {
    return msgContent;
  }

  public void setMsgContent(String msgContent) {
    this.msgContent = msgContent;
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

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
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

  public List<MessageInfosBean> getMessageInfos() {
    return messageInfos;
  }

  public void setMessageInfos(List<MessageInfosBean> messageInfos) {
    this.messageInfos = messageInfos;
  }

  public static class MessageInfosBean {
    /**
     * id : 1
     * messageId : 1
     * sendUser : 1
     * createBy : 24
     * sendTime : 1576549481000
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
    private int msgStatus;
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
}
