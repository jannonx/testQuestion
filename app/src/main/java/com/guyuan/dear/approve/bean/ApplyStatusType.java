package com.guyuan.dear.approve.bean;


import com.guyuan.dear.approve.constant.ApplyConstant;

/**
 * @description: 审批状态
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ApplyStatusType {
    /**
     * 审批状态
     */
    TYPE_STATUS_ING("审批中"),
    TYPE_STATUS_PASS("审批通过"),
    TYPE_STATUS_REJECT("未通过"),
    TYPE_STATUS_UNKNOWN("未知状态");


    private String des;

    ApplyStatusType() {
    }

    ApplyStatusType(String des) {
        this.des = des;
    }

    public static ApplyStatusType toType(int type) {
        switch (type) {
            case ApplyConstant.INT_STATUS_ING:
                return TYPE_STATUS_ING;
            case ApplyConstant.INT_STATUS_PASS:
                return TYPE_STATUS_PASS;
            case ApplyConstant.INT_STATUS_REJECT:
                return TYPE_STATUS_REJECT;
            default:
                return TYPE_STATUS_UNKNOWN;
        }
    }

    public static String toText(int type) {
        return ApplyStatusType.toType(type).getDes();
    }

    public String getDes() {
        return des;
    }
}
