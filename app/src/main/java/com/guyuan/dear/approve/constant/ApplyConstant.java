package com.guyuan.dear.approve.constant;


/**
 * @description: 审批--静态变量
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyConstant {
    /**
     * 审批类型:
     * 1.请假2.出差3.外出4.加班5.招聘6.离职
     * 7.报销8.付款9.备用金10.用印11.通用审批12.采购
     */
    public static final int INT_LEAVE = 1;
    public static final int INT_BUSINESS = 2;
    public static final int INT_OUTING = 3;
    public static final int INT_OVERTIME = 4;
    public static final int INT_EMPLOY = 5;
    public static final int INT_LEAVE_OFFICE = 6;
    public static final int INT_EXPENSE_REIMBURSEMENT = 7;
    public static final int INT_PAY = 8;
    public static final int INT_PRETTY_CASH = 9;
    public static final int INT_SEAL = 10;
    public static final int INT_COMMON = 11;
    public static final int INT_PROCUREMENT = 12;

    /**
     * 审批状态：
     * 1.审批中2.审批通过3.未通过
     */
    public static final int INT_STATUS_ING = 0;
    public static final int INT_STATUS_PASS = 1;
    public static final int INT_STATUS_REJECT = 2;


    /**
     * 请假类型：
     * 1.年假2.事假3.病假4.调休5.产假6.陪产假
     * 7.婚假8.例假9.丧假10.哺乳假
     */
    public static final int INT_LEAVE_ANNUAL = 1;
    public static final int INT_LEAVE_PERSONAL_AFFAIRS = 2;
    public static final int INT_LEAVE_SICK = 3;
    public static final int INT_LEAVE_DAYS_OFF = 4;
    public static final int INT_LEAVE_MATERNITY = 5;
    public static final int INT_LEAVE_PATERNITY = 6;
    public static final int INT_LEAVE_MARRIAGE = 7;
    public static final int INT_LEAVE_MENSES = 8;
    public static final int INT_LEAVE_FUNERAL = 9;
    public static final int INT_LEAVE_BREASTFEEDING = 10;

    /**
     * 支付类型：
     * 1.支票2.贷记3.电汇4.汇票5.现金6.银行卡
     * 7.其他
     */
    public static final int INT_PAY_CHEQUE = 1;
    public static final int INT_PAY_CREDIT= 2;
    public static final int INT_PAY_WIRE_TRANSFER = 3;
    public static final int INT_PAY_MONEY_ORDER = 4;
    public static final int INT_PAY_CASH = 5;
    public static final int INT_PAY_BANK_CARD = 6;
    public static final int INT_PAY_OTHER = 7;

}
