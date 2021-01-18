package com.guyuan.dear.focus.projectsite.type;


import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.ContractStatusType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--客户验收--条件状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum CustomerAcceptanceSatisfyType implements Serializable {

    /**
     * 待开始
     */
    TYPE_INSTALL_WAIT(10, "待开始", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 安装中
     */
    TYPE_INSTALL_ING(20, "安装中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 安装暂停
     */
    TYPE_INSTALL_PAUSE(30, "安装暂停", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 待验收
     */
    TYPE_ACCEPTANCE_WAIT(40, "待验收", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 验收合格
     */
    TYPE_ACCEPTANCE_OK(50, "验收合格", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 验收不合格
     */
    TYPE_ACCEPTANCE_EXCEPTION(60, "验收不合格", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 合同状态：1.合同正常，2.合同暂停
     */
    TYPE_CONTRACT_PAUSE(7, "合同暂停", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 未知状态
     */
    TYPE_UNKNOWN(70, "未知状态", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2);


    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    CustomerAcceptanceSatisfyType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static CustomerAcceptanceSatisfyType toType(int index) {
        for (CustomerAcceptanceSatisfyType type : CustomerAcceptanceSatisfyType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return TYPE_UNKNOWN;
    }

    public static CustomerAcceptanceSatisfyType toType(SiteExploreBean bean) {

        //合同状态：1.合同正常，2.合同暂停，显示合同暂停状态
        if (bean.getCheckStatus() != 10 && bean.getCheckStatus() != 20
                && bean.getContractStatusType() == ContractStatusType.TYPE_CONTRACT_PAUSE) {
            return TYPE_CONTRACT_PAUSE;
        }
        //安装状态
        if (bean.getStatus() == 10) {
            return CustomerAcceptanceSatisfyType.TYPE_INSTALL_WAIT;
        } else if (bean.getStatus() == 20) {
            return CustomerAcceptanceSatisfyType.TYPE_INSTALL_ING;
            //安装完成，验收状态
        } else if (bean.getStatus() == 30) {
            if (bean.getCheckStatus() == 0) {
                return CustomerAcceptanceSatisfyType.TYPE_ACCEPTANCE_WAIT;
            } else if (bean.getCheckStatus() == 10) {
                return CustomerAcceptanceSatisfyType.TYPE_ACCEPTANCE_OK;
            } else if (bean.getCheckStatus() == 20) {
                return CustomerAcceptanceSatisfyType.TYPE_ACCEPTANCE_EXCEPTION;
            }
        } else if (bean.getStatus() == 40) {
            return CustomerAcceptanceSatisfyType.TYPE_INSTALL_PAUSE;
        }
        return CustomerAcceptanceSatisfyType.TYPE_UNKNOWN;

    }

    /**
     * 有没有判定满足条件
     *
     * @return
     */
    public boolean isJudgingCondition() {
        return this != TYPE_UNKNOWN;
    }

    public static String toText(int type) {
        return CustomerAcceptanceSatisfyType.toType(type).getDes();

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
