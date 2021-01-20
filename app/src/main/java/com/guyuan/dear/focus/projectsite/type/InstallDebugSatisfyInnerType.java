package com.guyuan.dear.focus.projectsite.type;


import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.ContractStatusType;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--安装调试--条件状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum InstallDebugSatisfyInnerType implements Serializable {
    /**
     *  状态（10待开始，20安装中，30完成,40暂停）
     */
    /**
     * 待开始
     */
    TYPE_INSTALL_WAIT(10, "待安装", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 安装中
     */
    TYPE_INSTALL_ING(20, "安装中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 完成
     */
    TYPE_INSTALL_COMPLETE(30, "完成", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 暂停
     */
    TYPE_INSTALL_PAUSE(40, "暂停", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 合同状态：1.合同正常，2.合同暂停
     */
    TYPE_CONTRACT_PAUSE(7, "合同暂停", R.color.color_orange_FF6010,
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

    InstallDebugSatisfyInnerType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static InstallDebugSatisfyInnerType toType(InstallDebugBean bean) {
//        if (bean == null || bean.getStopStatus() == null) return TYPE_UNKNOWN;
        //合同状态：1.合同正常，2.合同暂停，显示合同暂停状态

        for (InstallDebugSatisfyInnerType type : InstallDebugSatisfyInnerType.values()) {
            if (type.getCode() == bean.getStatus()) {
                if (TYPE_INSTALL_COMPLETE.getCode() != bean.getStatus()
                        && bean.getContractStatusType() == ContractStatusType.TYPE_CONTRACT_PAUSE) {
                    return TYPE_CONTRACT_PAUSE;
                }
                return type;
            }
        }
        return TYPE_UNKNOWN;

    }

//    public static String toText(int type) {
//        return InstallDebugSatisfyInnerType.toType(type).getDes();
//
//    }

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
