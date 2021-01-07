package com.guyuan.dear.base.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2021/1/7 15:24
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ContractQueryType implements Serializable {
    /**
     *    String ID = "id";
     *     String CONTRACT_ID = "contractId";            //合同ID
     *     String CONTRACT_NUMBER = "contractNumber";    //合同编号
     *     String FLAG = "flag";                         //flag标识
     *     String PROJECT_ID = "projectId";              //项目ID
     */

    /**
     * 标识id
     */
    TYPE_ID(0, "标识id"),
    /**
     * 合同ID
     */
    TYPE_CONTRACT_ID(1, "合同ID"),
    /**
     * 合同编号
     */
    TYPE_CONTRACT_NUMBER(2, "合同编号"),
    /**
     * flag标识
     */
    TYPE_FLAG(4, "flag标识"),

    /**
     * 客户验收报告
     */
    TYPE_PROJECT_ID(5, "项目ID");


    private int code;
    private String des;

    ContractQueryType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ContractQueryType toType(int index) {
        for (ContractQueryType type : ContractQueryType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ContractQueryType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
