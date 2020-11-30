package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 18:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetStaffAttendStatus {

    /**
     * deptId : 0
     * deptIdName :
     * factoryName :
     * id : 0
     * imgUrl :
     * name :
     * nowStatus : 0
     * userDepartment :
     * userDepartmentName :
     * userPhone :
     * userPositionName :
     * userSex : 0
     * workId :
     */

    private int deptId;
    private String deptIdName;
    private String factoryName;
    private int id;
    private String imgUrl;
    private String name;
    /**
     * 实时状态：1.未到岗 2.迟到 3.请假 4.到岗 5.下班打卡异常
     */
    private String nowStatus;
    private String userPhone;
    private String userPositionName;
    private int userSex;
    private String workId;
    private String roleName;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptIdName() {
        return deptIdName;
    }

    public void setDeptIdName(String deptIdName) {
        this.deptIdName = deptIdName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNowStatus() {
        return nowStatus;
    }

    public void setNowStatus(String nowStatus) {
        this.nowStatus = nowStatus;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPositionName() {
        return userPositionName;
    }

    public void setUserPositionName(String userPositionName) {
        this.userPositionName = userPositionName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
