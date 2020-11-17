package com.guyuan.dear.focus.produce.bean;


/**
 * @description: 生产状态原因
 * @author: 许建宁
 * @since: 2020/11/9 12:28
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ProduceReasonType {

    TYPE_REASON_PAUSE("暂停原因："),
    TYPE_REASON_ACTIVATE("激活原因："),
    TYPE_REASON_REJECT("驳回原因："),
    TYPE_REASON_PASS("通过原因："),
    TYPE_UNKNOWN("未知原因：");

    private String des;

    ProduceReasonType(String des) {
        this.des = des;
    }

    public static ProduceReasonType toType(int type) {
        switch (type) {
            case ProduceConstant.INT_REASON_PAUSE:
                return TYPE_REASON_PAUSE;
            case ProduceConstant.INT_REASON_ACTIVATE:
                return TYPE_REASON_ACTIVATE;
            case ProduceConstant.INT_REASON_REJECT:
                return TYPE_REASON_REJECT;
            case ProduceConstant.INT_REASON_PASS:
                return TYPE_REASON_PASS;
            default:
                return TYPE_UNKNOWN;
        }


    }

    /**
     //暂停原因
     public static final int INT_REASON_PAUSE = 1;
     //激活原因
     public static final int INT_REASON_ACTIVATE = 2;
     //驳回原因
     public static final int INT_REASON_REJECT = 3;
     //通过原因
     public static final int INT_REASON_PASS = 4;
     */
    public int toInt() {
        switch (this) {
            case TYPE_REASON_PAUSE:
                return 0;
            case TYPE_REASON_ACTIVATE:
                return 1;
            case TYPE_REASON_REJECT:
                return 2;
            case TYPE_REASON_PASS:
                return 4;
            default:
                return 7;
        }


    }

    public static String toText(int type) {
        return ProduceReasonType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }


}
