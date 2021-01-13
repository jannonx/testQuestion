package com.guyuan.dear.customizeview.autoscrollrecyclerview;

import java.util.List;

/**
 * created by tl
 * created at 2020/4/25
 */
public class MessageBean {
    /**
     * id : 2
     * msgType : 1
     * sendType : 1
     * businessId : 1
     * msgTitle : [是谁呢下]我都钉钉
     * msgContent : 其实有时候我完不成任务或者工作，也不是因为懒什么的，我就是能力不行
     * createBy : 24
     * sendTime : 2019-12-17 02:48:12
     * createTime : 2019-12-17 02:48:16
     * lastUpdateBy : null
     * lastUpdateTime : 2019-12-17 02:48:21
     * myMsgStatus : null
     * messageInfos : null
     */

    private int id;                                    //编号
    private int msgType;                               //消息类型
    private int sendType;                              //推送类型
    private int businessId;                            //业务id
    private String msgTitle;                           //消息标题
    private String msgContent;                         //消息内容
    private int createBy;                              //创建人
    private String sendTime;                           //发送时间
    private String createTime;                         //创建时间
    private Object lastUpdateBy;                       //更新人
    private String lastUpdateTime;                     //更新时间
    private Object myMsgStatus;                        //自己的消息状态
    private int label;                                 //0:绿色 1:黄色  2:红色
    private int isClose;                               //是否已处理该条智能管控消息 1:已处理
    private int isCloseAll;                            //是否已处理该类型所有异常消息 1:已处理
    private String url;                                //消息跳转的地址
    private String urlWarnContent;                     //出现预警警告，智能管控模块菜单提示语
    private String urlName;                            //页面标题
    private int businessStatus=-100;                   //合同暂停/重启对应的最新状态

    private List<MessageInfosBean> messageInfos;

    public MessageBean() {
    }

    public MessageBean(int label, String url) {
        this.label = label;
        this.url = url;
    }

    public String getUrlWarnContent() {
        return urlWarnContent;
    }

    public void setUrlWarnContent(String urlWarnContent) {
        this.urlWarnContent = urlWarnContent;
    }

    public int getIsCloseAll() {
        return isCloseAll;
    }

    public void setIsCloseAll(int isCloseAll) {
        this.isCloseAll = isCloseAll;
    }

    public int getIsClose() {
        return isClose;
    }

    public void setIsClose(int isClose) {
        this.isClose = isClose;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Object getMyMsgStatus() {
        return myMsgStatus;
    }

    public void setMyMsgStatus(Object myMsgStatus) {
        this.myMsgStatus = myMsgStatus;
    }

    public List<MessageInfosBean> getMessageInfos() {
        return messageInfos;
    }

    public void setMessageInfos(List<MessageInfosBean> messageInfos) {
        this.messageInfos = messageInfos;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(int businessStatus) {
        this.businessStatus = businessStatus;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", msgType=" + msgType +
                ", sendType=" + sendType +
                ", businessId=" + businessId +
                ", msgTitle='" + msgTitle + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", createBy=" + createBy +
                ", sendTime='" + sendTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateBy=" + lastUpdateBy +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", myMsgStatus=" + myMsgStatus +
                ", messageInfos=" + messageInfos +
                ", label=" + label +
                ", url='" + url + '\'' +
                ", urlName='" + urlName + '\'' +
                '}';
    }
}
