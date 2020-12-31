package com.guyuan.dear.focus.produce.bean;


import java.io.Serializable;

/**
 * @description: 合同状态
 * @author: 许建宁
 * @since: 2020/12/30 16:20
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ContractStatusType implements Serializable {
    /**
     * 销售合同状态：0.正常 1.暂停 2.被激活 3审批中
     */
    TYPE_CONTRACT_OK(0, "合同正常"),
    TYPE_CONTRACT_PAUSE(1, "合同暂停"),
    TYPE_CONTRACT_ACTIVATE(2, "合同被激活"),
    TYPE_CONTRACT_APPROVAL(3, "合同审批中"),
    TYPE_UNKNOWN(4, "未知操作");

    private int code;
    private String des;

    ContractStatusType(int code, String des) {
        this.code = code;
        this.des = des;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ContractStatusType toType(int index) {
        for (ContractStatusType type : ContractStatusType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ContractStatusType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
