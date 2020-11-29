package com.guyuan.dear.focus.hr.bean;

import com.guyuan.dear.work.contractPause.beans.DeptBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class ChildDept extends DeptBean {
    private List<StaffWorkStatusInfo> staffs = new ArrayList<>();

    public List<StaffWorkStatusInfo> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffWorkStatusInfo> staffs) {
        this.staffs = staffs;
    }

    public ChildDept() {
        setLevel((short) 1);
    }

    @Override
    public int getStaffTotal(){
        return staffs.size();
    }
}
