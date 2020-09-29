package com.guyuan.dear.focus.hr.bean;

import java.util.Date;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/24 15:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffWorkInfo {
    private String name;
    private long id;
    private int gender;
    private long workId;
    private String deptName;
    private String jobTitle;
    private Date enrolledDate;
    private String contactNumber;
    /**
     * {@link HrStatusGroup#GRP_TYPE_ABSENT}, {@link HrStatusGroup#GRP_TYPE_LATE},
     * {@link HrStatusGroup#GRP_TYPE_NORMAL}, {@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY}
     */
    private int currentStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }
}
