package com.guyuan.dear.office.approval.data.bean;

/**
 * @author : 唐力
 * @description :审批对应类型
 * @since: 2020/12/2 10:11
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalTypeBean {

    //==================== 销售合同审批 ====================
    /**
     * 销售合同审批
     **/
    public static final int CONTRACT_EXAMINE_MASTER_TYPE = 10001;
    /**
     * 合同状态暂停审批
     */
    public static final int CONTRACT_EXAMINE_STATUS_STOP_TYPE = 10002;
    /**
     * 合同状态重启审批 （重启就是 激活合同）
     */
    public static final int CONTRACT_EXAMINE_STATUS_RESTART_TYPE = 10003;

    //==================== 回款审批 ====================
    /**
     *回款审批
     **/
    public static final int PAYMENT_COLLECTION = 10101;

    //==================== 质保金审批 ====================
    /**
     *质保金审批
     **/
    public static final int QUALITY_DEPOSIT = 10201;

    //==================== 运输审批 ====================
    /**
     *运输审批
     **/
    public static final int TRANSPORT = 10301;

    //==================== 设备一览审批 ====================
    /**
     *编辑
     **/
    public static final int EQUIPMENT_EDIT = 10401;
    /**
     *核对
     **/
    public static final int EQUIPMENT_CHECK = 10402;

    //==================== 生产审批 ====================
    /**
     *制定生产计划
     **/
    public static final int MAKING_PLAN = 10501;
    /**
     *生产计划暂停
     **/
    public static final int PAUSE_PLAN = 10502;
    /**
     *生产计划激活
     **/
    public static final int ACTIVATE_PLAN = 10503;


    /**
     * 设备任务分解
     **/
    public static final int PROJECT_TASK_SPLIT = 11504;

    /**
     * 运输管理模块
     **/
    public static final int TRANSPORT_PROJECT = 11505;

    //==================== 采购审批 ====================
    /**
     *制定采购合同
     **/
    public static final int MAKING_CONTRACT = 12001;
    //==================== 质检审批 ====================
    /**
     *制定质检出厂编号
     **/
    public static final int MAKING_CODE = 12501;
    /**
     *制定质检报告
     **/
    public static final int MAKING_REPORT = 12502;

    //==================== 成本核算 ====================
    /**
     *成本核算
     **/
    public static final int COST_ACCOUNTING = 12501;

    //==================== 库存审批 ====================
    /**
     *物料申请
     **/
    public static final int MAKING_REQUEST = 13501;
}