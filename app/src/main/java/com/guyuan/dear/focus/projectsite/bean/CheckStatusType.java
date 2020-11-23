package com.guyuan.dear.focus.projectsite.bean;


import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--验收状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum CheckStatusType implements Serializable {
    /**
     *  是否满足条件、是否安全(1:是，2:否)
     */
    /**
     * 运输中
     */
    TYPE_CHECK_OK(0, "运输中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 已到达,待清点
     */
    TYPE_GOODS_CHECK_ING(2, "待清点", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 清点正常
     */
    TYPE_GOODS_CHECK_OK(3, "清点正常", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 清点异常
     */
    TYPE_GOODS_CHECK_EXCEPTION(4, "清点异常", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 未知状态
     */
    TYPE_GOODS_CHECK_UNKNOWN(5, "未知状态", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    CheckStatusType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static CheckStatusType toType(int index) {
        for (CheckStatusType type : CheckStatusType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static CheckStatusType toType(SiteExploreBean bean) {
       return null;
    }

    /**
     * 有没有判定满足条件
     *
     * @return
     */
    public boolean isJudgingCondition() {
        return this != TYPE_GOODS_CHECK_UNKNOWN;
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

    public int getTextColor() {
        return textColor;
    }

    public int getTextBgColor() {
        return textBgColor;
    }
}
