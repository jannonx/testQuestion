package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 19:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetStaffAttendRecord {

    /**
     * amStartTime :
     * amStatus : 0
     * id : 0
     * pmEndTime :
     * pmStatus : 0
     * todayDate :
     * userId : 0
     */

    private String amStartTime;
    /**
     * 上午打卡状态：1.正常 2.迟到
     */
    private int amStatus;
    private String pmEndTime;
    /**
     * 下午打卡状态：1.正常 2.早退
     */
    private int pmStatus;
    private String todayDate;
    private int isAttendDay;

    public String getAmStartTime() {
        return amStartTime;
    }

    public void setAmStartTime(String amStartTime) {
        this.amStartTime = amStartTime;
    }

    public int getAmStatus() {
        return amStatus;
    }

    public void setAmStatus(int amStatus) {
        this.amStatus = amStatus;
    }

    public String getPmEndTime() {
        return pmEndTime;
    }

    public void setPmEndTime(String pmEndTime) {
        this.pmEndTime = pmEndTime;
    }

    public int getPmStatus() {
        return pmStatus;
    }

    public void setPmStatus(int pmStatus) {
        this.pmStatus = pmStatus;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public int isAttendDay() {
        return isAttendDay;
    }

    public void setAttendDay(int attendDay) {
        isAttendDay = attendDay;
    }


}
