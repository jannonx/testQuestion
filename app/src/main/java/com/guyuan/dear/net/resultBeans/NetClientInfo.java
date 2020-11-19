package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/18 14:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetClientInfo {

    /**
     * id : 15
     * cusName : 智能机器人信息有限公司
     * contactName : 王晨
     * contactPhone : 18740112832
     * followUp : 谢俊杰
     * followUpTime : 2020-11-18 11:38
     */

    private int id;
    private String cusName;
    private String contactName;
    private String contactPhone;
    private String followUp;
    private String followUpTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getFollowUpTime() {
        return followUpTime;
    }

    public void setFollowUpTime(String followUpTime) {
        this.followUpTime = followUpTime;
    }
}
