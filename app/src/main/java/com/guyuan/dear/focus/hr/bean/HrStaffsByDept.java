package com.guyuan.dear.focus.hr.bean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 12:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStaffsByDept {
    private String grpLabel;
    private int grpType;
    private List<StaffBean> staffs;
    private long deptId;

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getGrpLabel() {
        return grpLabel;
    }

    public void setGrpLabel(String grpLabel) {
        this.grpLabel = grpLabel;
    }

    public int getGrpType() {
        return grpType;
    }

    public void setGrpType(int grpType) {
        this.grpType = grpType;
    }

    public List<StaffBean> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffBean> staffs) {
        this.staffs = staffs;
    }
}
