package com.guyuan.dear.focus.hr.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.utils.CalenderUtils;

import java.util.Date;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/25 17:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LeaveEarlyAttendRecords implements Parcelable {
    private long date;
    private List<LeaveEarlyIncident> records;

    public LeaveEarlyAttendRecords() {
    }

    protected LeaveEarlyAttendRecords(Parcel in) {
        date = in.readLong();
        records = in.createTypedArrayList(LeaveEarlyIncident.CREATOR);
    }

    public static final Creator<LeaveEarlyAttendRecords> CREATOR = new Creator<LeaveEarlyAttendRecords>() {
        @Override
        public LeaveEarlyAttendRecords createFromParcel(Parcel in) {
            return new LeaveEarlyAttendRecords(in);
        }

        @Override
        public LeaveEarlyAttendRecords[] newArray(int size) {
            return new LeaveEarlyAttendRecords[size];
        }
    };

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<LeaveEarlyIncident> getRecords() {
        return records;
    }

    public void setRecords(List<LeaveEarlyIncident> records) {
        this.records = records;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date);
        dest.writeTypedList(records);
    }

    public static class LeaveEarlyIncident implements Parcelable {
        private long startTime;
        private long endTime;

        public LeaveEarlyIncident() {
        }

        protected LeaveEarlyIncident(Parcel in) {
            startTime = in.readLong();
            endTime = in.readLong();
        }

        public static final Creator<LeaveEarlyIncident> CREATOR = new Creator<LeaveEarlyIncident>() {
            @Override
            public LeaveEarlyIncident createFromParcel(Parcel in) {
                return new LeaveEarlyIncident(in);
            }

            @Override
            public LeaveEarlyIncident[] newArray(int size) {
                return new LeaveEarlyIncident[size];
            }
        };

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getDescription(){
            Date legitimateLeaveTime = new Date(endTime);
            legitimateLeaveTime.setHours(17);
            legitimateLeaveTime.setMinutes(30);
            long leaveEarlyTime = legitimateLeaveTime.getTime()-endTime;
            if(leaveEarlyTime>0){
                return CalenderUtils.getInstance().getHourAndMinuteDescription(leaveEarlyTime);
            }
            return "";
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(startTime);
            dest.writeLong(endTime);
        }
    }
}
