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
public class ParentDept extends DeptBean {
    private List<ChildDept> childDeptList = new ArrayList<>();

    public ParentDept() {
        setLevel((short) 0);
    }

    public List<ChildDept> getChildDeptList() {
        return childDeptList;
    }

    public void setChildDeptList(List<ChildDept> childDeptList) {
        this.childDeptList = childDeptList;
    }

    @Override
    public int getStaffTotal() {
        int total = 0;
        for (ChildDept dept : childDeptList) {
            total = total + dept.getStaffTotal();
        }
        return total;
    }
}
