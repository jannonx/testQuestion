package com.guyuan.dear.test.bean;


import java.io.Serializable;

/**
 * @description: 题目类型
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 */
public enum ItemQuestionType implements Serializable {

    /**
     * 选择A
     */
    TYPE_A(1, "A","选择A"),
    /**
     * 选择B
     */
    TYPE_B(2, "B","选择B"),
    /**
     * 选择C
     */
    TYPE_C(3, "C","选择C"),
    /**
     * 选择D
     */
    TYPE_D(4, "D","选择D");


    private int code;
    private String sign;
    private String des;

    ItemQuestionType(int code, String sign, String des) {
        this.code = code;
        this.sign = sign;
        this.des = des;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */

    public static ItemQuestionType toType(int index) {
        for (ItemQuestionType type : ItemQuestionType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static ItemQuestionType toType(String sign) {
        for (ItemQuestionType type : ItemQuestionType.values()) {
            if (type.getSign().equals(sign)) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ItemQuestionType.toType(type).getDes();

    }

    public int getCode() {
        return code;
    }

    public String getSign() {
        return sign;
    }

    public String getDes() {
        return des;
    }
}
