package com.guyuan.dear.focus.aftersale.bean;

import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 售后服务--验收阶段
 * @author: 许建宁
 * @since: 2020/11/25 18:42
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SaleAcceptedType implements Serializable {
//    验收状态 0.待验收 1.验收合格 2.验收不合格 3.验收中
    /**
     * 未知状态
     */
    TYPE_ACCEPTED_UNKNOWN(-1, "未知状态", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 待验收
     */
    TYPE_ACCEPTED_WAIT(0, "待验收", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 验收合格
     */
    TYPE_ACCEPTED_QUALIFIED(1, "验收合格", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 验收不合格
     */
    TYPE_ACCEPTED_UNQUALIFIED(2, "验收不合格", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 验收中
     */
    TYPE_ACCEPTED_ING(3, "验收中", R.color.color_blue_1677ff,
                              R.drawable.bg_blue_e7f1ff_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    SaleAcceptedType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static SaleAcceptedType toType(int index) {
        for (SaleAcceptedType type : SaleAcceptedType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return TYPE_ACCEPTED_UNKNOWN;
    }


    public static String toText(int type) {
        return SaleAcceptedType.toType(type).getDes();

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

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextBgColor() {
        return textBgColor;
    }

    public void setTextBgColor(int textBgColor) {
        this.textBgColor = textBgColor;
    }
}
