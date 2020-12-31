package com.guyuan.dear.focus.produce.bean;


import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.type.CustomerAcceptanceSatisfyType;

/**
 * @description: 子生产状态
 * @author: 许建宁
 * @since: 2020/11/9 12:28
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ProductStatusType {
    /**
     * 暂停
     */
    TYPE_PRODUCE_EXCEPTION(0, "暂停", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 待生产
     */
    TYPE_PRODUCE_WAIT(1, "待生产", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 生产中
     */
    TYPE_PRODUCE_ING(2, "生产中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 激活生产中
     */
    TYPE_PRODUCE_ACTIVATE(3, "激活生产中", R.color.color_blue_1677ff,
            R.drawable.bg_blue_e7f1ff_corner_2),
    /**
     * 生产完成
     */
    TYPE_PRODUCE_COMPLETE(4, "生产完成", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),
    /**
     * 拖期未完成
     */
    TYPE_PRODUCE_DELAY_FINISH(5, "拖期未完成", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 拖期已完成
     */
    TYPE_PRODUCE_DELAY_NOT_FINISH(6, "拖期已完成", R.color.color_green_00B578,
            R.drawable.bg_green_d4fff1_corner_2),

    /**
     * 合同状态：1.合同正常，2.合同暂停
     */
    TYPE_CONTRACT_PAUSE(7, "合同暂停", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2),
    /**
     * 未知类型
     */
    TYPE_UNKNOWN(8, "未知类型", R.color.color_orange_FF6010,
            R.drawable.bg_orange_ffece3_corner_2);

    private int code;
    private String des;
    private int textColor;
    private int textBgColor;

    ProductStatusType(int code, String des, int textColor, int textBgColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
        this.textBgColor = textBgColor;
    }

    /**
     * 根据枚举code获取实例，用于switchR
     */
    public static ProductStatusType toType(FocusProduceBean produceBean) {
        if (produceBean == null || produceBean.getStopStatus() == null) return TYPE_UNKNOWN;
        //合同状态：1.合同正常，2.合同暂停，显示合同暂停状态
        if (produceBean.getContractStatusType() == ContractStatusType.TYPE_CONTRACT_PAUSE) {
            return TYPE_CONTRACT_PAUSE;
        } else {
            //除了合同暂停，显示正常生产状态
            for (ProductStatusType type : ProductStatusType.values()) {
                if (type.getCode() == produceBean.getStatus()) {
                    return type;
                }
            }
            return TYPE_UNKNOWN;
        }

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
