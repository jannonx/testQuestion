package com.guyuan.dear.work.contractPause.beans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 14:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffBean {
    private String name;
    private String id;
    private String imgUrl;
    private String dept;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
