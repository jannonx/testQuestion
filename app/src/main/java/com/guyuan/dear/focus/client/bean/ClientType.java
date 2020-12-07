package com.guyuan.dear.focus.client.bean;

import com.guyuan.dear.focus.projectsite.type.CheckStatusType;

import java.io.Serializable;

/**
 * @description: 客户--客户类型
 * @author: 许建宁
 * @since: 2020/12/2 11:24
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ClientType implements Serializable {
    /**
     *  是否满足条件、是否安全(1:是，2:否)
     */
    /**
     * 全部用户
     */
    TYPE_CLIENT_ALL(0, "全部用户"),
    /**
     * 我的跟进
     */
    TYPE_CLIENT_MY(1, "我的跟进");


    private int code;
    private String des;

    ClientType(int code, String des) {
        this.code = code;
        this.des = des;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ClientType toType(int index) {
        for (ClientType type : ClientType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }


    public static String toText(int type) {
        return CheckStatusType.toType(type).getDes();

    }


    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }

}
