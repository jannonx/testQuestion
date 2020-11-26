package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 17:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetHrSummary {

    /**
     * companyAbsentStaffNumber : 0
     * companyAllStaffNumber : 0
     * companyLateStaffNumber : 0
     * companyNormalStaffNumber : 0
     * compayEarlyStaffNumber : 0
     * todayStr :
     * weekTodayStr :
     */

    private int companyAbsentStaffNumber;
    private int companyAllStaffNumber;
    private int companyLateStaffNumber;
    private int companyNormalStaffNumber;
    private int compayEarlyStaffNumber;
    private int companyLeaveStaffNumber;
    private String todayStr;
    private String weekTodayStr;

    public int getCompanyAbsentStaffNumber() {
        return companyAbsentStaffNumber;
    }

    public void setCompanyAbsentStaffNumber(int companyAbsentStaffNumber) {
        this.companyAbsentStaffNumber = companyAbsentStaffNumber;
    }

    public int getCompanyAllStaffNumber() {
        return companyAllStaffNumber;
    }

    public void setCompanyAllStaffNumber(int companyAllStaffNumber) {
        this.companyAllStaffNumber = companyAllStaffNumber;
    }

    public int getCompanyLateStaffNumber() {
        return companyLateStaffNumber;
    }

    public void setCompanyLateStaffNumber(int companyLateStaffNumber) {
        this.companyLateStaffNumber = companyLateStaffNumber;
    }

    public int getCompanyNormalStaffNumber() {
        return companyNormalStaffNumber;
    }

    public void setCompanyNormalStaffNumber(int companyNormalStaffNumber) {
        this.companyNormalStaffNumber = companyNormalStaffNumber;
    }

    public int getCompayEarlyStaffNumber() {
        return compayEarlyStaffNumber;
    }

    public void setCompayEarlyStaffNumber(int compayEarlyStaffNumber) {
        this.compayEarlyStaffNumber = compayEarlyStaffNumber;
    }

    public int getCompanyLeaveStaffNumber() {
        return companyLeaveStaffNumber;
    }

    public void setCompanyLeaveStaffNumber(int companyLeaveStaffNumber) {
        this.companyLeaveStaffNumber = companyLeaveStaffNumber;
    }

    public String getTodayStr() {
        return todayStr;
    }

    public void setTodayStr(String todayStr) {
        this.todayStr = todayStr;
    }

    public String getWeekTodayStr() {
        return weekTodayStr;
    }

    public void setWeekTodayStr(String weekTodayStr) {
        this.weekTodayStr = weekTodayStr;
    }
}
