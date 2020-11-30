package com.guyuan.dear.net.reqBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 10:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInRqBody {

    /**
     * clockType : 0
     * gpsLatitude : 0
     * gpsLongitude : 0
     * img :
     */

    /**
     * 1.正常上班打卡 2.正常下班打卡 3.外勤上班打卡 4.外勤下班打卡
     */
    private int clockType;
    /***
     * 正常上班打卡
     */
    public static final int CLOCK_IN_TYPE_CLOCK_IN_INSIDE_COMPANY_AREA=1;
    /**
     * 正常下班打卡
     */
    public static final int CLOCK_IN_TYPE_CHECK_OUT_INSIDE_COMPANY_AREA=2;
    /**
     * 外勤上班打卡
     */
    public static final int CLOCK_IN_TYPE_CLOCK_IN_OUTSIDE_COMPANY_AREA=3;
    /**
     * 外勤下班打卡
     */
    public static final int CLOCK_IN_TYPE_CHECK_OUT_OUTSIDE_COMPANY_AREA=4;
    private double gpsLatitude;
    private double gpsLongitude;

    public int getClockType() {
        return clockType;
    }

    public void setClockType(int clockType) {
        this.clockType = clockType;
    }

    public double getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public double getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }
}
