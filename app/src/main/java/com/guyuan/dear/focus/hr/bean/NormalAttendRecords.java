package com.guyuan.dear.focus.hr.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/24 16:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NormalAttendRecords implements Parcelable {
    private long date;
    private List<NormalAttendance> normalAttendances;

    public NormalAttendRecords() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<NormalAttendance> getNormalAttendances() {
        return normalAttendances;
    }

    public void setNormalAttendances(List<NormalAttendance> normalAttendances) {
        this.normalAttendances = normalAttendances;
    }

    public static class NormalAttendance implements Parcelable {

        private long startTime;
        private long endTime;

        public NormalAttendance() {
        }

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

        protected NormalAttendance(Parcel in) {
            startTime = in.readLong();
            endTime = in.readLong();
        }

        public static final Creator<NormalAttendance> CREATOR = new Creator<NormalAttendance>() {
            @Override
            public NormalAttendance createFromParcel(Parcel in) {
                return new NormalAttendance(in);
            }

            @Override
            public NormalAttendance[] newArray(int size) {
                return new NormalAttendance[size];
            }
        };

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

    protected NormalAttendRecords(Parcel in) {
        date = in.readLong();
        normalAttendances = in.createTypedArrayList(NormalAttendance.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date);
        dest.writeTypedList(normalAttendances);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NormalAttendRecords> CREATOR = new Creator<NormalAttendRecords>() {
        @Override
        public NormalAttendRecords createFromParcel(Parcel in) {
            return new NormalAttendRecords(in);
        }

        @Override
        public NormalAttendRecords[] newArray(int size) {
            return new NormalAttendRecords[size];
        }
    };
}
