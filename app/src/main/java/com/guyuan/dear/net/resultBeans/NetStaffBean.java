package com.guyuan.dear.net.resultBeans;

import com.google.gson.annotations.SerializedName;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/6 12:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetStaffBean {

    /**
     * userId : 4
     * name : 唐力
     * imgUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160379026584005989.jpeg
     * workId : 4
     * userDepartment : 760,764
     * userDepartmentName : 研发部,研发二部
     * status : 1
     */

    private long userId;
    private String name;
    private String imgUrl;
    private String workId;
    @SerializedName("userDepartment")
    private String deptIds;
    @SerializedName("userDepartmentName")
    private String deptNames;
    /**
     * 1表示未删除，0表示已经删除
     */
    @SerializedName("status")
    private short deleteFlag;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }

    public short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
