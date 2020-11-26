package com.guyuan.dear.focus.aftersale.bean;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.io.Serializable;

/**
 * @description: 验收状态
 * @author: 许建宁
 * @since: 2020/11/25 17:39
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SaleQualifiedType implements Serializable {
    /**
     *  验收状态 0.待验收 1.验收合格 2.验收不合格 3.验收中
     */
    /**
     * 验收合格
     */
    TYPE_QUALIFIED(1, "验收合格"),

    /**
     * 验收不合格
     */
    TYPE_UNQUALIFIED(2, "验收不合格");


    private int code;
    private String des;

    SaleQualifiedType(int code, String des) {
        this.code = code;
        this.des = des;
    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static CheckGoodsSatisfyType toType(int index) {
        for (CheckGoodsSatisfyType type : CheckGoodsSatisfyType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }


    public static String toText(int type) {
        return CheckGoodsSatisfyType.toType(type).getDes();

    }


    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }


}
