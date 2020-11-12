package com.guyuan.dear.focus.produce.bean;

/**
 * @description: 操作者的角色
 * @author: Jannonx
 * @since: 2020/11/11 12:17
 * @company: 固远（深圳）信息技术有限公司
 */
public enum OperatorType {
    /**
     * 开始生产人
     */
    START_PRODUCTION(1, "开始生产人"),
    /**
     * 激活人
     */
    ACTIVATOR(2, "激活人"),
    /**
     * 审批人
     */
    APPROVER(3, "审批人"),
    /**
     * 完成生产人
     */
    END_PRODUCTION(4, "完成生产人");

    private int code;

    private String des;

    OperatorType(int code, String des) {
        this.code = code;
        this.des = des;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static OperatorType getEnum(Integer index) {
        for (OperatorType c : OperatorType.values()) {
            if (c.getCode() == index) {
                return c;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
