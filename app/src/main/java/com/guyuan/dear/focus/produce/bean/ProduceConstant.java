package com.guyuan.dear.focus.produce.bean;

/**
 * @description:
 * @author: 许建宁
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

    //生产拖期未完成
    public static final int INT_PRODUCE_DELAY_NOT_FINISH = 5;

    //生产拖期已完成
    public static final int INT_PRODUCE_DELAY_FINISHED = 6;


    //----------------------生产状态原因

    //暂停原因
    public static final int INT_REASON_PAUSE = 1;
    //激活原因
    public static final int INT_REASON_ACTIVATE = 2;
    //驳回原因
    public static final int INT_REASON_REJECT = 3;
    //通过原因
    public static final int INT_REASON_PASS = 4;

    //----------------------生产审批状态

    //暂停审批中
    public static final int INT_APPROVAL_PAUSE_ING = 0;
    //暂停审批通过
    public static final int INT_APPROVAL_PAUSE_PASS = 2;
    //暂停审批被驳回
    public static final int INT_APPROVAL_PAUSE_REJECT = 3;
    //激活审批中
    public static final int INT_APPROVAL_ACTIVATE_ING = 4;
    //激活审批通过
    public static final int INT_APPROVAL_ACTIVATE_PASS = 6;
    //激活审批被驳回
    public static final int INT_APPROVAL_ACTIVATE_REJECT = 7;

}
