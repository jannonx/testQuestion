package com.guyuan.dear.focus.projectsite.type;


import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--安装调试--条件状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum InstallDebugSatisfyType implements Serializable {
    /**
     *  状态（10待开始，20,40调试中，30完工）
     */
    /**
     * 待开始
     */
    TYPE_INSTALL_WAIT(10, "待开始", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 调试中
     */
    TYPE_INSTALL_ING(20, "调试中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 完工
     */
    TYPE_INSTALL_COMPLETE(30, "完工", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 异常
     */
    TYPE_INSTALL_PAUSE(40, "异常", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 未知状态
     */
    TYPE_UNKNOWN(50, "未知状态", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    InstallDebugSatisfyType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     * 状态（10待开始，20,40调试中，30完工）
     */
    public static InstallDebugSatisfyType toType(int index) {
//        if (index==10){
//            return InstallDebugSatisfyType.TYPE_INSTALL_WAIT;
//        }else if (index==20||index==40){
//            return InstallDebugSatisfyType.TYPE_INSTALL_WAIT;
//        }
        for (InstallDebugSatisfyType type : InstallDebugSatisfyType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return TYPE_UNKNOWN;
    }

    public static String toText(int type) {
        return InstallDebugSatisfyType.toType(type).getDes();

    }

    /**
     * 有没有判定满足条件
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
