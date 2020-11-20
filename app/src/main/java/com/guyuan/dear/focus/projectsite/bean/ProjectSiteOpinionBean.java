package com.guyuan.dear.focus.projectsite.bean;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--内容描述集
 * @author: 许建宁
 * @since: 2020/11/19 14:06
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteOpinionBean implements Serializable {


    private int id;
    private int level;
    private String name;
    private String singleItemResult;
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
