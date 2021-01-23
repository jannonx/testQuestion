package com.jannonx.electric.test.bean;


import java.io.Serializable;

/**
 * @description: 浏览题目类型
 * @author: Jannonx
 * @since: 2020/11/10 15:31
 */
public enum ExamFunctionType implements Serializable {
    /**
     * 答题考试
     */
    TYPE_EXAM(0, "答题考试"),
    /**
     * 答案解析
     */
    TYPE_PARSE(1, "答案解析");

    private int code;
    private String des;

    ExamFunctionType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ExamFunctionType toType(int index) {
        for (ExamFunctionType type : ExamFunctionType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ExamFunctionType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
