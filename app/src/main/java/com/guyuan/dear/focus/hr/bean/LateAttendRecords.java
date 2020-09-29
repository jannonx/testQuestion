package com.guyuan.dear.focus.hr.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.utils.CalenderUtils;

import java.util.Date;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/25 17:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LateAttendRecords implements Parcelable {
    private long date;
    private List<LateIncident> records;

    public LateAttendRecords() {
    }

    protected LateAttendRecords(Parcel in) {
        date = in.readLong();
        records = in.createTypedArrayList(LateIncident.CREATOR);
    }

    public static final Creator<LateAttendRecords> CREATOR = new Creator<LateAttendRecords>() {
        @Override
        public LateAttendRecords createFromParcel(Parcel in) {
            return new LateAttendRecords(in);
        }

        @Override
        public LateAttendRecords[] newArray(int size) {
            return new LateAttendRecords[size];
        }
    };

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<LateIncident> getRecords() {
        return records;
    }

    public void setRecords(List<LateIncident> records) {
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


    public static class LateIncident implements Parcelable {
        private long beginTime;
        private long endTime;

        public LateIncident() {
        }


        protected LateIncident(Parcel in) {
            beginTime = in.readLong();
            endTime = in.readLong();
        }

        public static final Creator<LateIncident> CREATOR = new Creator<LateIncident>() {
            @Override
            public LateIncident createFromParcel(Parcel in) {
                return new LateIncident(in);
            }

            @Override
            public LateIncident[] newArray(int size) {
                return new LateIncident[size];
            }
        };

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getDescription() {
            Date legitimateWorkTime = new Date(beginTime);
            legitimateWorkTime.setHours(8);
            legitimateWorkTime.setMinutes(30);
            long lateTime = beginTime - legitimateWorkTime.getTime();
            if(lateTime >=0){
                return CalenderUtils.getInstance().getHourAndMinuteDescription(lateTime);
            }
            return "";
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(beginTime);
            dest.writeLong(endTime);
        }
    }
}
