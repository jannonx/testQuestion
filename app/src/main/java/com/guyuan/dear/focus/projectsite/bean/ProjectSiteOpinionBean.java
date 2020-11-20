package com.guyuan.dear.focus.projectsite.bean;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--内容描述集
 * @author: 许建宁
 * @since: 2020/11/19 14:06
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteOpinionBean implements Serializable {

    /**
     * 事项ID
     */
    private int id;
    /**
     * 事项细则等级(如有需要可以填写)
     */
    private int level;
    /**
     * 评审项内容描述
     */
    private String name;
    /**
     * 单个事项评审结果(0:未评审；1:通过；2:不通过),如果纯展示事项明细，就可忽略次字段。
     */
    private String singleItemResult;
    /**
     * 评审点
     */
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSingleItemResult() {
        return singleItemResult;
    }

    public void setSingleItemResult(String singleItemResult) {
        this.singleItemResult = singleItemResult;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
