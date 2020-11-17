package com.guyuan.dear.focus.produce.bean;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/10 16:05
 * @company: 固远（深圳）信息技术有限公司
 */
public enum DisPlayTimeType {
    /**
     * 实际完成时间
     */
    END_TIME(1, "实际完成时间："),
    /**
     * 拖期完成时间
     */
    DELAY_TIME(2, "拖期完成时间："),
    /**
     * 实际开始时间
     */
    START_TIME(3, "实际开始时间："),
    /**
     * 实际激活时间
     */
    ACTIVE_TIME(4, "实际激活时间："),
    /**
     * 实际暂停时间
     */
    PAUSE_TIME(5, "实际暂停时间："),
    /**
     * 审批驳回时间
     */
    REJECT_TIME(6, "审批驳回时间："),
    /**
     * 审批驳回时间
     */
    APPLY_PAUSE_TIME(7, "申请暂停时间："),
    /**
     * 审批通过时间
     */
    PASS_TIME(8, "审批通过时间：");

    private int code;

    private String message;

    DisPlayTimeType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static DisPlayTimeType toType(Integer index) {
        for (DisPlayTimeType c : DisPlayTimeType.values()) {
            if (c.getCode() == index) {
                return c;
            }
        }
        return null;
    }
}
