package com.guyuan.dear.focus.produce.bean;


/**
 * @description: 生产状态原因
 * @author: Jannonx
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ApprovalStatusType {

    TYPE_APPROVAL_PAUSE_ING(0, "暂停审批中"),
    TYPE_APPROVAL_PAUSE_PASS(2, "暂停审批通过"),
    TYPE_APPROVAL_PAUSE_REJECT(3, "暂停审批被驳回"),
    TYPE_APPROVAL_ACTIVATE_ING(4, "激活审批中"),
    TYPE_APPROVAL_ACTIVATE_PASS(6, "激活审批通过"),
    TYPE_APPROVAL_ACTIVATE_REJECT(7, "激活审批被驳回"),
    TYPE_UNKNOWN(8, "未知状态");

    private int status;
    private String des;

    ApprovalStatusType(int status, String des) {
        this.status = status;
        this.des = des;
    }

    public static ApprovalStatusType toType(int type) {
        switch (type) {
            case ProduceConstant.INT_APPROVAL_PAUSE_ING:
                return TYPE_APPROVAL_PAUSE_ING;
            case ProduceConstant.INT_APPROVAL_PAUSE_PASS:
                return TYPE_APPROVAL_PAUSE_PASS;
            case ProduceConstant.INT_APPROVAL_PAUSE_REJECT:
                return TYPE_APPROVAL_PAUSE_REJECT;
            case ProduceConstant.INT_APPROVAL_ACTIVATE_ING:
                return TYPE_APPROVAL_ACTIVATE_ING;
            case ProduceConstant.INT_APPROVAL_ACTIVATE_PASS:
                return TYPE_APPROVAL_ACTIVATE_PASS;
            case ProduceConstant.INT_APPROVAL_ACTIVATE_REJECT:
                return TYPE_APPROVAL_ACTIVATE_REJECT;
            default:
                return TYPE_UNKNOWN;
        }


    }

    public static String toText(int type) {
        return ApprovalStatusType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getStatus() {
        return status;
    }
}
