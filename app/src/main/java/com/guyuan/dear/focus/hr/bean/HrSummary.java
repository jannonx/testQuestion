package com.guyuan.dear.focus.hr.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 17:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummary {
    private int staffTotal;
    private List<HrStatusGroup> groupList=new ArrayList<>();

    public int getStaffTotal() {
        return staffTotal;
    }


    public List<HrStatusGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<HrStatusGroup> groupList) {
        this.groupList.clear();
        this.groupList.addAll(groupList);
        for (HrStatusGroup temp : groupList) {
            staffTotal += temp.getStaffTotal();
        }
    }
}
