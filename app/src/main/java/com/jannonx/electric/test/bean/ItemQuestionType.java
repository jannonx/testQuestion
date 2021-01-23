package com.jannonx.electric.test.bean;


import java.io.Serializable;

/**
 * @description: 题目类型
 * @author: Jannonx
 * @since: 2020/11/10 15:31
 */
public enum ItemQuestionType implements Serializable {

    /**
     * 选择A
     */
    TYPE_A(0, "A","选择A"),
    /**
     * 选择B
     */
    TYPE_B(1, "B","选择B"),
    /**
     * 选择C
     */
    TYPE_C(2, "C","选择C"),
    /**
     * 选择D
     */
    TYPE_D(3, "D","选择D");


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
