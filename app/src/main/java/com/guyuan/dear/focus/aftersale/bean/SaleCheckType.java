package com.guyuan.dear.focus.aftersale.bean;

import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 售后服务--排查阶段
 * @author: 许建宁
 * @since: 2020/11/25 18:42
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SaleCheckType implements Serializable {
//    验收状态   0.待验收 1.验收中 2.验收合格  3.验收不合格
    /**
     * 未知状态
     */
    TYPE_CHECK_UNKNOWN(-1, "未知状态", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 待排查
     */
    TYPE_CHECK_WAIT(0, "待排查", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),

    /**
     * 排查完成
     */
    TYPE_CHECK_COMPLETE(2, "排查完成", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 排查中
     */
    TYPE_CHECK_ING(3, "排查中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    SaleCheckType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static SaleCheckType toType(int index) {
        if (index == 0) {
            return TYPE_CHECK_WAIT;
        } else if (index == 1) {
            return TYPE_CHECK_ING;
        } else if (index == 2 || index == 3) {
            return TYPE_CHECK_COMPLETE;
        }
        return TYPE_CHECK_UNKNOWN;
    }


    public static String toText(int type) {
        return SaleCheckType.toType(type).getDes();

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
