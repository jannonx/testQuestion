package com.jannonx.electric.test.bean;


import java.io.Serializable;

/**
 * @description: 选择题结果类型
 * @author: Jannonx
 * @since: 2020/11/10 15:31
 */
public enum ItemQuestionResultType implements Serializable {
    /**
     * 选择错误
     */
    TYPE_WRONG(0, "选择错误"),
    /**
     * 选择正确
     */
    TYPE_RIGHT(1, "选择正确"),

    /**
     * 没有做选择
     */
    TYPE_NO_SELECT(2, "没有做选择");

    private int code;
    private String des;

    ItemQuestionResultType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ItemQuestionResultType toType(int index) {
        for (ItemQuestionResultType type : ItemQuestionResultType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ItemQuestionResultType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
