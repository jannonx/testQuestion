package com.guyuan.dear.focus.projectsite.bean;


import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--安全排查--条件状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum CheckSafeSatisfyType implements Serializable {
    /**
     *  是否满足条件、是否安全(1:是，2:否)
     */
    /**
     * 满足条件
     */
    TYPE_UNKNOWN(0, "未知", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 满足条件
     */
    TYPE_CONDITION_OK(1, "安全", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 满足条件
     */
    TYPE_CONDITION_EXCEPTION(2, "异常", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    CheckSafeSatisfyType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static CheckSafeSatisfyType toType(int index) {
        for (CheckSafeSatisfyType type : CheckSafeSatisfyType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return TYPE_UNKNOWN;
    }

    public static String toText(int type) {
        return CheckSafeSatisfyType.toType(type).getDes();

    }

    /**
     * 有没有判定安全条件
     *
     * @return
     */
    public boolean isJudgingCondition() {
        return this != TYPE_UNKNOWN;
    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextBgColor() {
        return textBgColor;
    }
}
