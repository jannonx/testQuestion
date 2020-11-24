package com.guyuan.dear.focus.projectsite.bean;

import android.text.TextUtils;

import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 18:47
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallDebugStatusBean implements Serializable {

    /**
     * createTime : 2020-11-24 18:41:46
     * departmentName : 研发部,研发一部
     * id : 34
     * idea : 不回家斤斤计较
     * imgList : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/.png160612221432475
     * imgUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160525832425769996.jpg
     * name : 许建宁
     * title : 安装中
     */

    private String createTime;
    private String departmentName;
    private long id;
    private String idea;
    private String imgList;
    private String imgUrl;
    private String name;
    private String title;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
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

    public void setTitle(String title) {
        this.title = title;
    }
}
