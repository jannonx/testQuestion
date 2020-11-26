package com.guyuan.dear.focus.aftersale.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/25 18:42
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SaleSectionType implements Serializable {

    /**
     * 故障排查
     */
    TYPE_SECTION_CHECK(51, "故障排查"),

    /**
     * 客户验收
     */
    TYPE_SECTION_ACCEPT(52, "客户验收");


    private int code;
    private String des;

    SaleSectionType(int code, String des) {
        this.code = code;
        this.des = des;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static SaleSectionType toType(int index) {
        for (SaleSectionType type : SaleSectionType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }


    public static String toText(int type) {
        return SaleSectionType.toType(type).getDes();

    }


    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
