package com.guyuan.dear.focus.produce.bean;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/9 12:09
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProduceConstant {
    //----------------------生产状态

    //生产异常
    public static final int INT_PRODUCE_EXCEPTION = 0;
    //待生产
    public static final int INT_PRODUCE_WAIT = 1;
    //生产中
    public static final int INT_PRODUCE_ING = 2;
    //生产完成
    public static final int INT_PRODUCE_COMPLETE = 4;
    //生产拖期
    public static final int INT_PRODUCE_DELAY = 6;
}
