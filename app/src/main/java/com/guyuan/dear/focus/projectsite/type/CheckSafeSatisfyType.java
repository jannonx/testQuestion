package com.guyuan.dear.focus.projectsite.type;


import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.ContractStatusType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--安全排查--条件状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum CheckSafeSatisfyType implements Serializable {
    /**
     * 状态（10:待操作(待勘察等...)、20:执行中（勘查中...）、30:完成（勘查完成...）、40:其他）
     *  是否满足条件、是否安全(1:是，2:否)
     */
    /**
     * 未知条件
     */
    TYPE_UNKNOWN(40, "未知条件", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 待排查
     */
    TYPE_CHECK_WAIT(10, "待排查", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 排查中
     */
    TYPE_CHECK_ING(20, "排查中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 完成排查
     */
    TYPE_CHECK_COMPLETE(30, "完成排查", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 合同状态：1.合同正常，2.合同暂停
     */
    TYPE_CONTRACT_PAUSE(7, "合同暂停", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
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

    /**
     * 状态（10:待操作(待勘察等...)、20:执行中（勘查中...）、30:完成（勘查完成...）、40:其他）
     * 是否满足条件、是否安全(1:是，2:否)
     */
    public static CheckSafeSatisfyType toType(SiteExploreBean bean) {


        if (bean == null || bean.getStopStatus() == null) return TYPE_UNKNOWN;
        //合同状态：1.合同正常，2.合同暂停，显示合同暂停状态
        if (bean.getContractStatusType() == ContractStatusType.TYPE_CONTRACT_PAUSE) {
            return TYPE_CONTRACT_PAUSE;
        } else {
            //勘查状态
            if (bean.getSatisfyFlag() == 0) {
                if (bean.getStatus() == 10) {
                    return CheckSafeSatisfyType.TYPE_CHECK_WAIT;
                } else if (bean.getStatus() == 20) {
                    return CheckSafeSatisfyType.TYPE_CHECK_ING;
                }
            } else if (bean.getStatus() == 30) {
                //勘查完成，条件状态
                if (bean.getModuleType() == null) return CheckSafeSatisfyType.TYPE_UNKNOWN;
                //我的关注
                if (FunctionModuleType.TYPE_FOCUS == bean.getModuleType()) {
                    if (bean.getSatisfyFlag() == 1) {
                        return CheckSafeSatisfyType.TYPE_CONDITION_OK;
                    } else if (bean.getSatisfyFlag() == 2) {
                        return CheckSafeSatisfyType.TYPE_CONDITION_EXCEPTION;
                    }
                    //我的工作
                } else if (FunctionModuleType.TYPE_WORK == bean.getModuleType()) {
                    return CheckSafeSatisfyType.TYPE_CHECK_COMPLETE;
                }

            }
            return CheckSafeSatisfyType.TYPE_UNKNOWN;
        }
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
