package com.guyuan.dear.focus.hr.bean;

import com.guyuan.dear.work.contractPause.beans.DeptBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/24 15:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffWorkStatusInfo {
    private String name;
    private long id;
    private String imgUrl;
    private String lv1DeptName;
    private int lv1DeptId;
    private String lv2DeptName;
    private int lv2DeptId;

    //最新打卡状态：0，未到岗 1.正常上班 2.迟到 3.早退 4.请假
    /**
     * {@link StaffWorkStatusInfo#WORK_STATUS_ABSENT},{@link StaffWorkStatusInfo#WORK_STATUS_EARLY_LEAVE},
     * {@link StaffWorkStatusInfo#WORK_STATUS_LATE},{@link StaffWorkStatusInfo#WORK_STATUS_NORMAL},
     * {@link StaffWorkStatusInfo#WORK_STATUS_ON_LEAVE}
     */
    private int currentStatus;

    /**
     * 缺席
     */
    public static final int WORK_STATUS_ABSENT=0;
    /**
     * 正常
     */
    public static final int WORK_STATUS_NORMAL=1;
    /**
     * 迟到
     */
    public static final int WORK_STATUS_LATE=2;
    /**
     * 早退
     */
    public static final int WORK_STATUS_EARLY_LEAVE=3;
    /**
     * 请假
     */
    public static final int WORK_STATUS_ON_LEAVE=4;

    public StaffWorkStatusInfo(StaffBean bean) {
        setName(bean.getName());
        setImgUrl(bean.getImgUrl());
        setId(bean.getId());
        List<DeptBean> depts = bean.getDepts();
        if(!depts.isEmpty()){
            DeptBean parentDept = depts.get(0);
            setLv1DeptName(parentDept.getDeptName());
            setLv1DeptId((int) parentDept.getId());
            if(depts.size()>1){
                DeptBean childDept = depts.get(1);
                setLv2DeptName(childDept.getDeptName());
                setLv2DeptId((int) childDept.getId());
            }
        }

    }

    public StaffWorkStatusInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLv1DeptName() {
        return lv1DeptName;
    }

    public void setLv1DeptName(String lv1DeptName) {
        this.lv1DeptName = lv1DeptName;
    }

    public String getLv2DeptName() {
        return lv2DeptName;
    }

    public void setLv2DeptName(String lv2DeptName) {
        this.lv2DeptName = lv2DeptName;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLv1DeptId() {
        return lv1DeptId;
    }

    public void setLv1DeptId(int lv1DeptId) {
        this.lv1DeptId = lv1DeptId;
    }

    public int getLv2DeptId() {
        return lv2DeptId;
    }

    public void setLv2DeptId(int lv2DeptId) {
        this.lv2DeptId = lv2DeptId;
    }
}
