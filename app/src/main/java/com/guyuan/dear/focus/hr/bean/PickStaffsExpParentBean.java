package com.guyuan.dear.focus.hr.bean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 14:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsExpParentBean {
    private String dept;
    private int staffTotal;
    private boolean isExpanded;
    private List<PickStaffBean> staffs;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getStaffTotal() {
        return staffTotal;
    }

    public void setStaffTotal(int staffTotal) {
        this.staffTotal = staffTotal;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public List<PickStaffBean> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<PickStaffBean> staffs) {
        this.staffs = staffs;
    }
}
