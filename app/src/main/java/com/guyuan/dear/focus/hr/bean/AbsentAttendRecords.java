package com.guyuan.dear.focus.hr.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/25 18:07
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AbsentAttendRecords implements Parcelable{
    private long date;
    private List<AbsentIncident> records;


    public AbsentAttendRecords() {
    }

    protected AbsentAttendRecords(Parcel in) {
        date = in.readLong();
        records = in.createTypedArrayList(AbsentIncident.CREATOR);
    }

    public static final Creator<AbsentAttendRecords> CREATOR = new Creator<AbsentAttendRecords>() {
        @Override
        public AbsentAttendRecords createFromParcel(Parcel in) {
            return new AbsentAttendRecords(in);
        }

        @Override
        public AbsentAttendRecords[] newArray(int size) {
            return new AbsentAttendRecords[size];
        }
    };

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<AbsentIncident> getRecords() {
        return records;
    }

    public void setRecords(List<AbsentIncident> records) {
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

    public static class AbsentIncident implements Parcelable {
        private long date;

        public AbsentIncident() {
        }

        protected AbsentIncident(Parcel in) {
            date = in.readLong();
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public static final Creator<AbsentIncident> CREATOR = new Creator<AbsentIncident>() {
            @Override
            public AbsentIncident createFromParcel(Parcel in) {
                return new AbsentIncident(in);
            }

            @Override
            public AbsentIncident[] newArray(int size) {
                return new AbsentIncident[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(date);
        }
    }
}
