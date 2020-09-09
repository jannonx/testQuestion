package com.guyuan.dear.approve.bean;


import com.guyuan.dear.approve.constant.ApplyConstant;

/**
 * @description: 支付类型
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public enum PayType {

    /**
     * 支付类型：
     * 1.支票2.贷记3.电汇4.汇票5.现金6.银行卡
     * 7.其他
     */
    TYPE_PAY_CHEQUE("支票"),
    TYPE_PAY_CREDIT("贷记"),
    TYPE_PAY_WIRE_TRANSFER("电汇"),
    TYPE_PAY_MONEY_ORDER("汇票"),
    TYPE_PAY_CASH("现金"),
    TYPE_PAY_BANK_CARD("银行卡"),
    TYPE_PAY_OTHER("其他"),
    TYPE_UNKNOWN("未知类型");

    private String des;

    PayType() {
    }

    PayType(String des) {
        this.des = des;
    }


    public static PayType toType(int type) {
        switch (type) {
            case ApplyConstant.INT_PAY_CHEQUE:
                return TYPE_PAY_CHEQUE;
            case ApplyConstant.INT_PAY_CREDIT:
                return TYPE_PAY_CREDIT;
            case ApplyConstant.INT_PAY_WIRE_TRANSFER:
                return TYPE_PAY_WIRE_TRANSFER;
            case ApplyConstant.INT_PAY_MONEY_ORDER:
                return TYPE_PAY_MONEY_ORDER;
            case ApplyConstant.INT_PAY_CASH:
                return TYPE_PAY_CASH;
            case ApplyConstant.INT_PAY_BANK_CARD:
                return TYPE_PAY_BANK_CARD;
            case ApplyConstant.INT_PAY_OTHER:
                return TYPE_PAY_OTHER;
            default:
                return TYPE_UNKNOWN;
        }


    }

    public static String toText(int type) {
        return PayType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }
}
