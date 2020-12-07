package com.guyuan.dear.focus.projectsite.type;


import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--现场勘查
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SiteExploreStatusType implements Serializable {
    /**
     *  状态（10:待操作(待勘察等...)、20:执行中（勘查中...）、30:完成（勘查完成...）、40:其他）
     */

    /**
     * 货物清点报告
     */
    TYPE_EXPLORE_WAIT(10, "待勘察"),
    /**
     * 安全排查报告
     */
    TYPE_EXPLORE_ING(20, "勘查中"),
    /**
     * 现场勘查报告
     */
    TYPE_EXPLORE_FINISH(30, "勘查完成"),
    /**
     * 安装调试报告
     */
    TYPE_UNKNOWN(40, "其他");



    private int code;
    private String des;

    SiteExploreStatusType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static SiteExploreStatusType toType(int index) {
        for (SiteExploreStatusType type : SiteExploreStatusType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return SiteExploreStatusType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
