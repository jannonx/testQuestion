package com.jannonx.electric.service;

/**
 * @description: 启动后台服务类型
 * @author: Jannonx
 * @since: 2021/1/21 20:57
 */
public enum BackTaskType {
    /**
     * 读取数据库，并写入本地
     */
    TYPE_WR_DB(10, "rb_db"),
    /**
     * 客户验收报告
     */
    TYPE_CUSTOMER_ACCEPTANCE(20, "客户验收报告");


    private int code;
    private String des;

    BackTaskType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static BackTaskType toType(int index) {
        for (BackTaskType type : BackTaskType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return BackTaskType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
