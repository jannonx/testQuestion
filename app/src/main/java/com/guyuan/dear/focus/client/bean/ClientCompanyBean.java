package com.guyuan.dear.focus.client.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 客户模块--公司
 * @author: 许建宁
 * @since: 2020/10/28 14:46
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientCompanyBean implements Serializable {
    /**
     * 主键id
     */
    private int id;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 客户名称
     */
    private String cusName;
    /**
     * 最后跟进人员
     */
    private String followUp;
    /**
     * 最后跟进时间
     */
    private String followUpTime;

    /**
     * 客户详细地址
     */
    private String cusAddress;


    /**
     * 销售人员
     */
    private String salesman;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 客户联系人
     */
    private List<ClientContactBean> list;

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

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
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

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public List<ClientContactBean> getList() {
        return list;
    }

    public void setList(List<ClientContactBean> list) {
        this.list = list;
    }
}
