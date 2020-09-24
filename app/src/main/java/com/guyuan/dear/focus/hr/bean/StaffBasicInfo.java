package com.guyuan.dear.focus.hr.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 12:10
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffBasicInfo {
    private long id;
    private String name;
    private String dept;
    private long deptId;
    private String imgUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
