package com.guyuan.dear.approve.bean;


import com.guyuan.dear.approve.constant.ApplyConstant;

/**
 * @description: 请假的类型
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public enum LeaveType {
    /**
     * 请假类型
     */
    TYPE_LEAVE_ANNUAL("年假"),
    TYPE_LEAVE_PERSONAL_AFFAIRS("事假"),
    TYPE_LEAVE_SICK("病假"),
    TYPE_LEAVE_DAYS_OFF("调休"),
    TYPE_LEAVE_MATERNITY("产假"),
    TYPE_LEAVE_PATERNITY("陪产假"),
    TYPE_LEAVE_MARRIAGE("婚假"),
    TYPE_LEAVE_MENSES("例假"),
    TYPE_LEAVE_FUNERAL("丧假"),
    TYPE_LEAVE_BREASTFEEDING("哺乳假"),
    TYPE_LEAVE_UNKNOWN("未知类型");


    private String des;

    LeaveType(String des) {
        this.des = des;
    }

    public static LeaveType toType(int type) {
        switch (type) {
            case ApplyConstant.INT_LEAVE_ANNUAL:
                return TYPE_LEAVE_ANNUAL;
            case ApplyConstant.INT_LEAVE_PERSONAL_AFFAIRS:
                return TYPE_LEAVE_PERSONAL_AFFAIRS;
            case ApplyConstant.INT_LEAVE_SICK:
                return TYPE_LEAVE_SICK;
            case ApplyConstant.INT_LEAVE_DAYS_OFF:
                return TYPE_LEAVE_DAYS_OFF;
            case ApplyConstant.INT_LEAVE_MATERNITY:
                return TYPE_LEAVE_MATERNITY;
            case ApplyConstant.INT_LEAVE_PATERNITY:
                return TYPE_LEAVE_PATERNITY;
            case ApplyConstant.INT_LEAVE_MARRIAGE:
                return TYPE_LEAVE_MARRIAGE;
            case ApplyConstant.INT_LEAVE_MENSES:
                return TYPE_LEAVE_MENSES;
            case ApplyConstant.INT_LEAVE_FUNERAL:
                return TYPE_LEAVE_FUNERAL;
            case ApplyConstant.INT_LEAVE_BREASTFEEDING:
                return TYPE_LEAVE_BREASTFEEDING;
            default:
                return TYPE_LEAVE_UNKNOWN;
        }
    }

    public static String toText(int type) {
        return LeaveType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }


}
