package com.guyuan.dear.focus.produce.bean;


/**
 * @description: 子生产状态
 * @author: 许建宁
 * @since: 2020/11/9 12:28
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ProductStatusType {

    TYPE_PRODUCE_EXCEPTION("生产异常"),
    TYPE_PRODUCE_WAIT("待生产"),
    TYPE_PRODUCE_ING("生产中"),
    TYPE_PRODUCE_COMPLETE("生产完成"),
    TYPE_PRODUCE_DELAY_FINISH("生产拖期已完成"),
    TYPE_PRODUCE_DELAY_NOT_FINISH("生产拖期未完成"),
    TYPE_UNKNOWN("未知类型");

    private String des;

    ProductStatusType(String des) {
        this.des = des;
    }

    public static ProductStatusType toType(int type) {
        switch (type) {
            case ProduceConstant.INT_PRODUCE_EXCEPTION:
                return TYPE_PRODUCE_EXCEPTION;
            case ProduceConstant.INT_PRODUCE_WAIT:
                return TYPE_PRODUCE_WAIT;
            case ProduceConstant.INT_PRODUCE_ING:
                return TYPE_PRODUCE_ING;
            case ProduceConstant.INT_PRODUCE_COMPLETE:
                return TYPE_PRODUCE_COMPLETE;
            case ProduceConstant.INT_PRODUCE_DELAY_NOT_FINISH:
                return TYPE_PRODUCE_DELAY_NOT_FINISH;
            case ProduceConstant.INT_PRODUCE_DELAY_FINISHED:
                return TYPE_PRODUCE_DELAY_FINISH;
            default:
                return TYPE_UNKNOWN;
        }


    }

    /**
     * //    //生产异常
     * //    public static final int TYPE_PRODUCE_EXCEPTION = 0;
     * //    //待生产
     * //    public static final int TYPE_PRODUCE_WAIT = 1;
     * //    //生产中
     * //    public static final int TYPE_PRODUCE_ING = 2;
     * //    //生产完成
     * //    public static final int TYPE_PRODUCE_COMPLETE = 4;
     * //    //生产拖期
     * //    public static final int TYPE_PRODUCE_DELAY = 6;
     */
    public int toInt() {
        switch (this) {
            case TYPE_PRODUCE_EXCEPTION:
                return 0;
            case TYPE_PRODUCE_WAIT:
                return 1;
            case TYPE_PRODUCE_ING:
                return 2;
            case TYPE_PRODUCE_COMPLETE:
                return 4;
            case TYPE_PRODUCE_DELAY_NOT_FINISH:
                return 5;
            case TYPE_PRODUCE_DELAY_FINISH:
                return 6;
            default:
                return 7;
        }


    }

    public static String toText(int type) {
        return ProductStatusType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }


}
