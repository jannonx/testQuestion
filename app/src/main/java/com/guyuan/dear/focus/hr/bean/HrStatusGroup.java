package com.guyuan.dear.focus.hr.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 17:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGroup {
    private String name;
    private int staffTotal;
    private int grpType;
    public static final int GRP_TYPE_NORMAL=0;
    public static final int GRP_TYPE_LATE=1;
    public static final int GRP_TYPE_LEAVE_EARLY=2;
    public static final int GRP_TYPE_ABSENT=4;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStaffTotal() {
        return staffTotal;
    }

    public void setStaffTotal(int staffTotal) {
        this.staffTotal = staffTotal;
    }

    public int getGrpType() {
        return grpType;
    }

    public void setGrpType(int grpType) {
        this.grpType = grpType;
    }
}
