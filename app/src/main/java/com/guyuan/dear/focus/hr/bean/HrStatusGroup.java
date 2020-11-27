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
    /**
     * {@link HrStatusGroup#GRP_TYPE_NORMAL},{@link HrStatusGroup#GRP_TYPE_LATE},{@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY},
     * {@link HrStatusGroup#GRP_TYPE_ABSENT},{@link HrStatusGroup#GRP_TYPE_ON_LEAVE}
     */
    private int grpType;
    //1.正常 2.未到岗 3.迟到 4.早退 5.请假
    public static final int GRP_TYPE_NORMAL=1;
    public static final int GRP_TYPE_LATE=3;
    public static final int GRP_TYPE_LEAVE_EARLY=4;
    public static final int GRP_TYPE_ABSENT=2;
    public static final int GRP_TYPE_ON_LEAVE=5;


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
