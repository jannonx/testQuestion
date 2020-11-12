package com.guyuan.dear.focus.hr.bean;

import com.guyuan.dear.work.contractPause.beans.StaffBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 14:35
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffBean extends StaffBean {
    private boolean isPick;
    private boolean isHidden;
    private boolean isDisabled;
    private long pickTime;

    public PickStaffBean(){}

    public boolean isPick() {
        return isPick;
    }

    public void setPick(boolean pick) {
        isPick = pick;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public long getPickTime() {
        return pickTime;
    }

    public void setPickTime(long pickTime) {
        this.pickTime = pickTime;
    }

    @Override
    public String toString() {
        return super.toString()+"PickStaffBean{" +
                "isPick=" + isPick +
                ", isHidden=" + isHidden +
                '}';
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
