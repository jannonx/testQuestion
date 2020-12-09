package com.guyuan.dear.focus.produce.bean;


import android.text.TextUtils;

import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 子生产状态
 * @author: 许建宁
 * @since: 2020/11/9 12:28
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ProductInnerStatusType implements Serializable {


    /**
     * 暂停申请
     */
    TYPE_PAUSE(10, "暂停申请", R.drawable.bg_blue_1890ff_round),
    /**
     * 暂停申请通过
     */
    TYPE_PAUSE_ING(20, "暂停申请审批中", R.drawable.bg_blue_1890ff_round),
    /**
     * 暂停申请通过
     */
    TYPE_PAUSE_PASS(30, "暂停申请通过", R.drawable.bg_green_2fc25b_round),
    /**
     * 暂停申请被驳回
     */
    TYPE_PAUSE_REJECT(40, "暂停申请被驳回", R.drawable.bg_red_f04864_round),
    /**
     * 激活申请
     */
    TYPE_ACTIVATE(50, "激活申请", R.drawable.bg_blue_1890ff_round),
    /**
     * 激活申请审批中
     */
    TYPE_ACTIVATE_ING(60, "激活申请审批中", R.drawable.bg_blue_1890ff_round),
    /**
     * 激活申请通过
     */
    TYPE_ACTIVATE_PASS(70, "激活申请通过", R.drawable.bg_green_2fc25b_round),
    /**
     * 激活申请被驳回
     */
    TYPE_ACTIVATE_REJECT(80, "激活申请被驳回", R.drawable.bg_red_f04864_round),
    /**
     * 生产开始
     */
    TYPE_PRODUCT_START(90, "生产开始", R.drawable.bg_blue_1890ff_round),
    /**
     * 生产完工
     */
    TYPE_PRODUCT_COMPLETE(100, "生产完成", R.drawable.bg_green_2fc25b_round),
    /**
     * 未知类型
     */
    TYPE_UNKNOWN(110, "未知类型", R.drawable.bg_red_f04864_round);


    private int code;
    private String des;
    private int textColor;

    ProductInnerStatusType(int code, String des, int textColor) {
        this.code = code;
        this.des = des;
        this.textColor = textColor;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ProductInnerStatusType toType(int index) {
        for (ProductInnerStatusType type : ProductInnerStatusType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return TYPE_UNKNOWN;
    }


    //10502，暂停；10503，激活
    public static final int PAUSE = 10502;
    public static final int ACTIVATE = 10503;
    //0.审批中 1.已同意 2.已拒绝
    public static final int APPLY_ING = 0;
    public static final int APPLY_PASS = 1;
    public static final int APPLY_REJECT = 2;

    public static ProductInnerStatusType toType(ProduceStateBean bean) {
        if (bean == null) return TYPE_UNKNOWN;
        //开始--完工
        if (!TextUtils.isEmpty(bean.getProdStatus())) {
            if (TYPE_PRODUCT_COMPLETE.getDes().equals(bean.getProdStatus())) {
                return TYPE_PRODUCT_COMPLETE;
            } else if (TYPE_PRODUCT_START.getDes().equals(bean.getProdStatus())) {
                return TYPE_PRODUCT_START;
            }
            //非空判断
        } else if (bean.getArType() != 0) {
            //暂停
            if (PAUSE == bean.getArType()) {
                if (bean.getBusinessId()!=0) {
                    return TYPE_PAUSE;
                } else if (APPLY_ING == bean.getStatus()) {
                    return TYPE_PAUSE_ING;
                }else if (APPLY_PASS == bean.getStatus()) {
                    return TYPE_PAUSE_PASS;
                } else if (APPLY_REJECT == bean.getStatus()) {
                    return TYPE_PAUSE_REJECT;
                }
                //激活
            } else if (ACTIVATE == bean.getArType()) {
                if (bean.getBusinessId()!=0) {
                    return TYPE_ACTIVATE;
                } else if (APPLY_ING == bean.getStatus()) {
                    return TYPE_ACTIVATE_ING;
                }else if (APPLY_PASS == bean.getStatus()) {
                    return TYPE_ACTIVATE_PASS;
                } else if (APPLY_REJECT == bean.getStatus()) {
                    return TYPE_ACTIVATE_REJECT;
                }
            }
        }
        return TYPE_UNKNOWN;
    }


    public static String toText(int type) {
        return ProductInnerStatusType.toType(type).getDes();

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




}
