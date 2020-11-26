package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 19:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetClockInConfig {

    /**
     * tuserWorkTimeInfoVo : {"id":33,"userId":1,"todayDate":"2020-11-26","amStartTime":"2020-11-26 09:45:14","amStatus":1,"pmEndTime":"2020-11-26 16:45:22","pmStatus":2}
     * tcompanyWorkTime : {"id":1,"name":"秋天打卡规则","companyId":1,"gpsConfig":14,"startTime":"10:00:00","endTime":"10:00:00","shiftTime":"10:00:00","status":1}
     * tclockGpsConfig : {"id":14,"configName":"你妈妈","gpsLongitude":333.22,"gpsLatitude":10.001,"localtion":"DS","distance":100,"createTime":null,"createBy":0,"updateTime":"2020-11-24 14:13:32","lastUpdateBy":0}
     */

    private TuserWorkTimeInfoVoBean tuserWorkTimeInfoVo;
    private TcompanyWorkTimeBean tcompanyWorkTime;
    private TclockGpsConfigBean tclockGpsConfig;

    public TuserWorkTimeInfoVoBean getTuserWorkTimeInfoVo() {
        return tuserWorkTimeInfoVo;
    }

    public void setTuserWorkTimeInfoVo(TuserWorkTimeInfoVoBean tuserWorkTimeInfoVo) {
        this.tuserWorkTimeInfoVo = tuserWorkTimeInfoVo;
    }

    public TcompanyWorkTimeBean getTcompanyWorkTime() {
        return tcompanyWorkTime;
    }

    public void setTcompanyWorkTime(TcompanyWorkTimeBean tcompanyWorkTime) {
        this.tcompanyWorkTime = tcompanyWorkTime;
    }

    public TclockGpsConfigBean getTclockGpsConfig() {
        return tclockGpsConfig;
    }

    public void setTclockGpsConfig(TclockGpsConfigBean tclockGpsConfig) {
        this.tclockGpsConfig = tclockGpsConfig;
    }

    public static class TuserWorkTimeInfoVoBean {
        /**
         * id : 33
         * userId : 1
         * todayDate : 2020-11-26
         * amStartTime : 2020-11-26 09:45:14
         * amStatus : 1
         * pmEndTime : 2020-11-26 16:45:22
         * pmStatus : 2
         */

        private int userId;
        private String amStartTime;
        private int amStatus;
        private String pmEndTime;
        private int pmStatus;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

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
    }

    public static class TcompanyWorkTimeBean {
        /**
         * id : 1
         * name : 秋天打卡规则
         * companyId : 1
         * gpsConfig : 14
         * startTime : 10:00:00
         * endTime : 10:00:00
         * shiftTime : 10:00:00
         * status : 1
         */

        private String startTime;
        private String endTime;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

    public static class TclockGpsConfigBean {
        /**
         * id : 14
         * configName : 你妈妈
         * gpsLongitude : 333.22
         * gpsLatitude : 10.001
         * localtion : DS
         * distance : 100
         * createTime : null
         * createBy : 0
         * updateTime : 2020-11-24 14:13:32
         * lastUpdateBy : 0
         */

        private double gpsLongitude;
        private double gpsLatitude;
        private String localtion;
        private int distance;

        public double getGpsLongitude() {
            return gpsLongitude;
        }

        public void setGpsLongitude(double gpsLongitude) {
            this.gpsLongitude = gpsLongitude;
        }

        public double getGpsLatitude() {
            return gpsLatitude;
        }

        public void setGpsLatitude(double gpsLatitude) {
            this.gpsLatitude = gpsLatitude;
        }

        public String getLocaltion() {
            return localtion;
        }

        public void setLocaltion(String localtion) {
            this.localtion = localtion;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
