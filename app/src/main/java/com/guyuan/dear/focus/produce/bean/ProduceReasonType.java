package com.guyuan.dear.focus.produce.bean;


import com.guyuan.dear.focus.projectsite.type.CheckGoodsSatisfyType;

/**
 * @description: 生产状态原因
 * @author: 许建宁
 * @since: 2020/11/9 12:28
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ProduceReasonType {


    TYPE_REASON_PAUSE(1, "暂停原因："),
    TYPE_REASON_ACTIVATE(2, "激活原因："),
    TYPE_REASON_REJECT(3, "驳回原因："),
    TYPE_REASON_PASS(4, "通过原因："),
    TYPE_UNKNOWN(-1, "未知原因：");

    private String des;
    private int code;

    ProduceReasonType(int code, String des) {
        this.code = code;
        this.des = des;
    }

    public static ProduceReasonType toType(int index) {
        for (ProduceReasonType type : ProduceReasonType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return TYPE_UNKNOWN;
    }


    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
