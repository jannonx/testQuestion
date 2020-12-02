package com.guyuan.dear.focus.projectsite.bean;


import java.io.Serializable;

/**
 * @description: 工程模块(focus : 0, work : 1)
 * @author: 许建宁
 * @since: 2020/11/21 17:54
 * @company: 固远（深圳）信息技术有限公司
 */
public enum FunctionModuleType implements Serializable {

    /**
     * 货物清点报告
     */
    TYPE_FOCUS(0, "我的关注"),
    /**
     * 客户验收报告
     */
    TYPE_WORK(1, "我的工作");


    private int code;
    private String des;

    FunctionModuleType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static FunctionModuleType toType(int index) {
        for (FunctionModuleType type : FunctionModuleType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return FunctionModuleType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
