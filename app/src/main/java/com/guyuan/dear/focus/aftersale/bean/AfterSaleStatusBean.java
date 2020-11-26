package com.guyuan.dear.focus.aftersale.bean;

import com.guyuan.dear.R;
import com.guyuan.dear.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 我的关注--售后服务--排查记录
 * @author: 许建宁
 * @since: 2020/11/19 13:55
 * @company: 固远（深圳）信息技术有限公司
 */
public class AfterSaleStatusBean implements Serializable {
    /**
     * 答复主键ID
     */
    private long id;
    /**
     * 时间
     */
    private String createTime;
    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 意见内容
     */
    private String idea;
    /**
     * 头像图片
     */
    private String imgUrl;
    /**
     * 图片附件(多个逗号分隔)
     */
    private String imgList;
    /**
     * 头像图片
     */
    private String name;
    /**
     * 部分有标题描述的
     */
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<String> getImgUrlList() {
        return StringUtils.splitPhotoUrl(imgList);
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
