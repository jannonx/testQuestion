package com.guyuan.dear.focus.projectsite.bean;

import android.text.TextUtils;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.type.InstallDebugStatusType;
import com.guyuan.dear.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

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
    private String imgList;
    private String name;
    private String title;
    private InstallDebugStatusType statusType;

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

    public List<String> getImgList() {
        return StringUtils.splitPhotoUrl(imgList);
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public InstallDebugStatusType getStatusType() {
        return InstallDebugStatusType.toType(getTitle());
    }

    public void setStatusType(InstallDebugStatusType statusType) {
        this.statusType = statusType;
    }

    public String getTitle() {
        return title;
    }

    /**
     * 圆点的背景
     */
    public int getBallBg() {
        return getStatusType().getBallBg();
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
