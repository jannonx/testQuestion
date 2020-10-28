package com.guyuan.dear.focus.client.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/26 16:52
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientContactBean implements Serializable {
    /**
     * 主键id
     */
    private int id;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 客户姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 职位
     */
    private String position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
