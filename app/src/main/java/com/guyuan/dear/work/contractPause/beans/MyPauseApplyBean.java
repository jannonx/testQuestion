package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:30
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyBean extends BaseContractExcptBean implements Parcelable {
    @Transient
    public static final int APPLY_REJECTED=0;
    @Transient
    public static final int APPLY_PENDING_FOR_START=1;
    @Transient
    public static final int APPLY_APPROVED=2;
    @Transient
    public static final int APPLY_PROCESSING=3;

    private int state;
    private String applier;

    public MyPauseApplyBean() {
    }

    protected MyPauseApplyBean(Parcel in) {
        super(in);
        state = in.readInt();
        applier = in.readString();
    }

    public static final Creator<MyPauseApplyBean> CREATOR = new Creator<MyPauseApplyBean>() {
        @Override
        public MyPauseApplyBean createFromParcel(Parcel in) {
            return new MyPauseApplyBean(in);
        }

        @Override
        public MyPauseApplyBean[] newArray(int size) {
            return new MyPauseApplyBean[size];
        }
    };

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeInt(state);
        dest.writeString(applier);
    }
}
