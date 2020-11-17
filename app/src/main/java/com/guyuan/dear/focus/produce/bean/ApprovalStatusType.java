package com.guyuan.dear.focus.produce.bean;


/**
 * @description: 生产--申请状态
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ApprovalStatusType {
    TYPE_APPROVAL_NOT_APPLY(-1, "从未提交过暂停和激活审批"),
    TYPE_APPROVAL_PAUSE_PENDING(0, "暂停审批中"),
    TYPE_APPROVAL_PAUSE_ING(1, "暂停审批中"),
    TYPE_APPROVAL_PAUSE_PASS(2, "暂停审批通过"),
    TYPE_APPROVAL_PAUSE_REJECT(3, "暂停审批被驳回"),
    TYPE_APPROVAL_ACTIVATE_PENDING(4, "激活审批中"),
    TYPE_APPROVAL_ACTIVATE_ING(5, "激活审批中"),
    TYPE_APPROVAL_ACTIVATE_PASS(6, "激活审批通过"),
    TYPE_APPROVAL_ACTIVATE_REJECT(7, "激活审批被驳回"),
    TYPE_UNKNOWN(8, "未知状态");

    private int code;
    private String des;

    ApprovalStatusType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ApprovalStatusType toType(int index) {
        for (ApprovalStatusType type : ApprovalStatusType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ApprovalStatusType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
