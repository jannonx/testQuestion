package com.guyuan.dear.focus.projectsite.bean;


import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--报告类型
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum ProjectReportType implements Serializable {

    /**
     * 现场勘查报告
     */
    TYPE_SITE_EXPLORATION(0, "现场勘查报告"),
    /**
     * 货物清点报告
     */
    TYPE_CHECK_GOODS(1, "货物清点报告"),
    /**
     * 安全排查报告
     */
    TYPE_CHECK_SAFE(2, "安全排查报告"),
    /**
     * 安装调试报告
     */
    TYPE_INSTALLATION_DEBUG(3, "安装调试报告"),
    /**
     * 客户验收报告
     */
    TYPE_CUSTOMER_ACCEPTANCE(4, "客户验收报告");


    private int code;
    private String des;

    ProjectReportType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static ProjectReportType toType(int index) {
        for (ProjectReportType type : ProjectReportType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return ProjectReportType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
