package com.guyuan.dear.approve.bean;


import com.guyuan.dear.approve.constant.ApplyConstant;

/**
 * @description: 请假类型
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ApplyType {

    /**
     * 审批类型:
     * 1.请假2.出差3.外出4.加班5.招聘6.离职
     * 7.报销8.付款9.备用金10.用印11.通用审批12.采购
     */
    TYPE_LEAVE("请假"),
    TYPE_BUSINESS("出差"),
    TYPE_OUTING("外出"),
    TYPE_OVERTIME("加班"),
    TYPE_EMPLOY("招聘"),
    TYPE_LEAVE_OFFICE("离职"),
    TYPE_EXPENSE_REIMBURSEMENT("报销"),
    TYPE_PAY("付款"),
    TYPE_PRETtY_CASH("备用金"),
    TYPE_SEAL("用印"),
    TYPE_COMMON("通用"),
    TYPE_PROCUREMENT("采购"),
    TYPE_UNKNOWN("未知类型");

    private String des;

    ApplyType() {
    }

    ApplyType(String des) {
        this.des = des;
    }


    public static ApplyType toType(int type) {
        switch (type) {
            case ApplyConstant.INT_LEAVE:
                return TYPE_LEAVE;
            case ApplyConstant.INT_BUSINESS:
                return TYPE_BUSINESS;
            case ApplyConstant.INT_OUTING:
                return TYPE_OUTING;
            case ApplyConstant.INT_EMPLOY:
                return TYPE_EMPLOY;
            case ApplyConstant.INT_OVERTIME:
                return TYPE_OVERTIME;
            case ApplyConstant.INT_LEAVE_OFFICE:
                return TYPE_LEAVE_OFFICE;
            case ApplyConstant.INT_EXPENSE_REIMBURSEMENT:
                return TYPE_EXPENSE_REIMBURSEMENT;
            case ApplyConstant.INT_PAY:
                return TYPE_PAY;
            case ApplyConstant.INT_PRETTY_CASH:
                return TYPE_PRETtY_CASH;
            case ApplyConstant.INT_SEAL:
                return TYPE_SEAL;
            case ApplyConstant.INT_COMMON:
                return TYPE_COMMON;
            case ApplyConstant.INT_PROCUREMENT:
                return TYPE_PROCUREMENT;
            default:
                return TYPE_UNKNOWN;
        }


    }

    public static String toText(int type) {
        return ApplyType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }
}
