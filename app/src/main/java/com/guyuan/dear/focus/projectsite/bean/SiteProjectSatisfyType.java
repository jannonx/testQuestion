package com.guyuan.dear.focus.projectsite.bean;


import com.guyuan.dear.R;
import com.guyuan.dear.utils.LogUtils;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--现场勘查/安全排查--条件状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SiteProjectSatisfyType implements Serializable {

    /**
     * 未知条件
     */
    TYPE_UNKNOWN(40, "未知条件", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 待勘察
     */
    TYPE_EXPLORE_WAIT(10, "待勘察", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 勘查中
     */
    TYPE_EXPLORE_ING(20, "勘查中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 勘查完成
     */
    TYPE_EXPLORE_COMPLETE(30, "勘查完成", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 满足条件
     */
    TYPE_CONDITION_OK(1, "满足条件", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 满足条件
     */
    TYPE_CONDITION_EXCEPTION(2, "不满足条件", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    SiteProjectSatisfyType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static SiteProjectSatisfyType toType(int index) {
        for (SiteProjectSatisfyType type : SiteProjectSatisfyType.values()) {
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
    public static SiteProjectSatisfyType toType(SiteExploreBean bean) {

        if (bean == null) return SiteProjectSatisfyType.TYPE_UNKNOWN;
        //勘查状态
        if (bean.getSatisfyFlag() == 0) {
            if (bean.getStatus() == 10) {
                return SiteProjectSatisfyType.TYPE_EXPLORE_WAIT;
            } else if (bean.getStatus() == 20) {
                return SiteProjectSatisfyType.TYPE_EXPLORE_ING;
            }
        } else if (bean.getStatus() == 30) {
            //勘查完成，条件状态
            if (bean.getSatisfyFlag() == 1) {
                return SiteProjectSatisfyType.TYPE_CONDITION_OK;
            } else if (bean.getSatisfyFlag() == 2) {
                return SiteProjectSatisfyType.TYPE_CONDITION_EXCEPTION;
            }
        }
        return SiteProjectSatisfyType.TYPE_UNKNOWN;
    }

    public static String toText(int type) {
        return SiteProjectSatisfyType.toType(type).getDes();

    }

    /**
     * 有没有判定满足条件
     *
     * @return
     */
    public boolean isJudgingCondition() {
        LogUtils.showLog("isJudgingCondition=" + this.getDes());
        return this != SiteProjectSatisfyType.TYPE_UNKNOWN;
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
