package com.guyuan.dear.focus.hr.bean;

import com.guyuan.dear.net.resultBeans.NetHrSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 17:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryBean {
    private int staffTotal;
    private long date;
    private int normaAttendance;
    private int absentAttendance;
    private int onLeaveAttendance;
    private int lateAttendance;
    private int earlyLeaveAttendance;

    public HrSummaryBean() {
    }

    public HrSummaryBean(NetHrSummary src) {
        this.staffTotal =src.getCompanyAllStaffNumber();
        this.normaAttendance = src.getCompanyNormalStaffNumber();
        this.absentAttendance = src.getCompanyAbsentStaffNumber();
        this.onLeaveAttendance =src.getCompanyLeaveStaffNumber();
        this.lateAttendance = src.getCompanyLateStaffNumber();
        this.earlyLeaveAttendance =src.getCompayEarlyStaffNumber();
        this.date = System.currentTimeMillis();
    }

    public int getStaffTotal() {
        return staffTotal;
    }

    public void setStaffTotal(int staffTotal) {
        this.staffTotal = staffTotal;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getNormaAttendance() {
        return normaAttendance;
    }

    public void setNormaAttendance(int normaAttendance) {
        this.normaAttendance = normaAttendance;
    }

    public int getAbsentAttendance() {
        return absentAttendance;
    }

    public void setAbsentAttendance(int absentAttendance) {
        this.absentAttendance = absentAttendance;
    }

    public int getOnLeaveAttendance() {
        return onLeaveAttendance;
    }

    public void setOnLeaveAttendance(int onLeaveAttendance) {
        this.onLeaveAttendance = onLeaveAttendance;
    }

    public int getLateAttendance() {
        return lateAttendance;
    }

    public void setLateAttendance(int lateAttendance) {
        this.lateAttendance = lateAttendance;
    }

    public int getEarlyLeaveAttendance() {
        return earlyLeaveAttendance;
    }

    public void setEarlyLeaveAttendance(int earlyLeaveAttendance) {
        this.earlyLeaveAttendance = earlyLeaveAttendance;
    }
}
