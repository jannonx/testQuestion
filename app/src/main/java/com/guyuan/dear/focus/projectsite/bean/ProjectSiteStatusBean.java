package com.guyuan.dear.focus.projectsite.bean;

import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--回复意见集
 * @author: 许建宁
 * @since: 2020/11/19 13:55
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteStatusBean implements Serializable {


    private String createTime;
    private String departmentName;
    private int id;
    private String idea;
    private String imgUrl;
    private String name;
    private String title;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        if ("安装中".endsWith(title)) {
            return "继续安装";
        } else if ("暂停".endsWith(title)) {
            return "暂停安装";
        } else {
            return "完工";
        }
    }

    /**
     * 圆点的背景
     */
    public int getBallBg() {
        if ("安装中".endsWith(title)) {
            return R.drawable.bg_blue_1890ff_round;
        } else if ("暂停".endsWith(title)) {
            return R.drawable.bg_red_f04864_round;
        } else {
            return R.drawable.bg_green_2fc25b_round;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
