package com.guyuan.dear.focus.hr.bean;

import java.util.Date;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/24 16:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AttendRecord {
    private Date date;
    /**
     * {@link HrStatusGroup#GRP_TYPE_ABSENT}, {@link HrStatusGroup#GRP_TYPE_LATE},
     * {@link HrStatusGroup#GRP_TYPE_ABSENT}, {@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY}
     */
    private int attendanceType;
    private List<Attendance> list;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(int attendanceType) {
        this.attendanceType = attendanceType;
    }

    public List<Attendance> getList() {
        return list;
    }

    public void setList(List<Attendance> list) {
        this.list = list;
    }

    public class Attendance {
        /**
         * {@link HrStatusGroup#GRP_TYPE_ABSENT}, {@link HrStatusGroup#GRP_TYPE_LATE},
         * {@link HrStatusGroup#GRP_TYPE_ABSENT}, {@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY}
         */
        private int attendanceType;
        private Date from;
        private Date to;

        public int getAttendanceType() {
            return attendanceType;
        }

        public void setAttendanceType(int attendanceType) {
            this.attendanceType = attendanceType;
        }

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTo() {
            return to;
        }

        public void setTo(Date to) {
            this.to = to;
        }
    }

}
