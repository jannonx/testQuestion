package com.guyuan.dear.focus.hr.view.hrStaffAttendDetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.hr.bean.AbsentAttendRecords;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.bean.LateAttendRecords;
import com.guyuan.dear.focus.hr.bean.LeaveEarlyAttendRecords;
import com.guyuan.dear.focus.hr.bean.NormalAttendRecords.NormalAttendance;
import com.guyuan.dear.focus.hr.bean.StaffWorkInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:38
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendDetailViewModel extends BaseViewModel {
    private MutableLiveData<StaffWorkInfo> staffInfo = new MutableLiveData<>();
    private MutableLiveData<Integer> lateCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> leaveEarlyCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> absentCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> normalCount = new MutableLiveData<>(0);
    private MutableLiveData<View.OnClickListener> onClickSelectDate = new MutableLiveData<>();
    private MutableLiveData<Long> selectDate = new MutableLiveData<>();
    private MutableLiveData<List<NormalAttendance>> normalRecords = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<LateAttendRecords.LateIncident>> lateRecords = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<LeaveEarlyAttendRecords.LeaveEarlyIncident>> leaveEarlyRecords = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<AbsentAttendRecords.AbsentIncident>> absentRecords = new MutableLiveData<>(new ArrayList<>());

//    /**
//     * ！有坑注意！
//     * 子类必须要带有一个和父类一样的构造函数，如：LoginViewModel(Application app)。否则根据源码，在ViewModelProvider中生成时会报错。
//     * by Leo
//     *
//     * @param application
//     */
//    public StaffAttendDetailViewModel(@NonNull Application application) {
//        super(application);
//    }

    public void loadUserData(long staffId) {
        StaffWorkInfo info = new StaffWorkInfo();
        info.setName("张三");
        info.setContactNumber("13544169080");
        info.setCurrentStatus(HrStatusGroup.GRP_TYPE_NORMAL);
        info.setDeptName("软件开发部");
        Date enroll = new Date();
        enroll.setYear(2019 - 1900);
        enroll.setMonth(10);
        info.setEnrolledDate(enroll);
        info.setGender(1);
        info.setJobTitle("APP软件工程师");
        info.setId(staffId);
        info.setWorkId(9527);
        staffInfo.postValue(info);
    }

    public MutableLiveData<StaffWorkInfo> getStaffInfo() {
        return staffInfo;
    }

    public void updateNormalAttendList(long staffId, Date selectDate) {
        normalRecords.getValue().clear();
        int countValue = normalCount.getValue();
        for (int i = 0; i < countValue; i++) {
            NormalAttendance incident = new NormalAttendance();
            incident.setStartTime(selectDate.getTime());
            incident.setEndTime(selectDate.getTime());
            normalRecords.getValue().add(incident);
        }
        normalRecords.postValue(normalRecords.getValue());
    }

    public void updateLateAttendList(long staffId, Date selectDate) {
        lateRecords.getValue().clear();
        int count = lateCount.getValue();
        for (int i = 0; i < count; i++) {
            LateAttendRecords.LateIncident incident = new LateAttendRecords.LateIncident();
            incident.setBeginTime(selectDate.getTime());
            incident.setEndTime(selectDate.getTime());
            lateRecords.getValue().add(incident);
        }
        lateRecords.postValue(lateRecords.getValue());
    }

    public MutableLiveData<List<NormalAttendance>> getNormalRecords() {
        return normalRecords;
    }

    public MutableLiveData<List<LateAttendRecords.LateIncident>> getLateRecords() {
        return lateRecords;
    }

    public MutableLiveData<List<LeaveEarlyAttendRecords.LeaveEarlyIncident>> getLeaveEarlyRecords() {
        return leaveEarlyRecords;
    }

    public MutableLiveData<List<AbsentAttendRecords.AbsentIncident>> getAbsentRecords() {
        return absentRecords;
    }


    public void updateLeaveEarlyAttendList(long staffId, Date selectDate) {
        int count = leaveEarlyCount.getValue();
        leaveEarlyRecords.getValue().clear();
        for (int i = 0; i < count; i++) {
            LeaveEarlyAttendRecords.LeaveEarlyIncident incident = new LeaveEarlyAttendRecords.LeaveEarlyIncident();
            incident.setStartTime(selectDate.getTime());
            incident.setEndTime(selectDate.getTime());
            leaveEarlyRecords.getValue().add(incident);
        }
        leaveEarlyRecords.postValue(leaveEarlyRecords.getValue());
    }

    public void updateAbsentAttendList(long staffId, Date selectDate) {
        Integer count = absentCount.getValue();
        absentRecords.getValue().clear();
        for (int i = 0; i < count; i++) {
            AbsentAttendRecords.AbsentIncident incident = new AbsentAttendRecords.AbsentIncident();
            incident.setDate(selectDate.getTime());
            absentRecords.getValue().add(incident);
        }
        absentRecords.postValue(absentRecords.getValue());
    }


    public MutableLiveData<Integer> getLateCount() {
        return lateCount;
    }

    public void setLateCount(MutableLiveData<Integer> lateCount) {
        this.lateCount = lateCount;
    }

    public MutableLiveData<Integer> getLeaveEarlyCount() {
        return leaveEarlyCount;
    }

    public void setLeaveEarlyCount(MutableLiveData<Integer> leaveEarlyCount) {
        this.leaveEarlyCount = leaveEarlyCount;
    }

    public MutableLiveData<Integer> getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(MutableLiveData<Integer> absentCount) {
        this.absentCount = absentCount;
    }

    public MutableLiveData<Integer> getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(MutableLiveData<Integer> normalCount) {
        this.normalCount = normalCount;
    }

    public MutableLiveData<View.OnClickListener> getOnClickSelectDate() {
        return onClickSelectDate;
    }

    public void loadAttendSummary(long staffId, long millseconds) {
        Random random = new Random(System.currentTimeMillis());
        selectDate.setValue(millseconds);
        lateCount.setValue(random.nextInt(10));
        leaveEarlyCount.setValue(random.nextInt(5));
        normalCount.setValue(random.nextInt(30));
        absentCount.setValue(random.nextInt(5));
    }


    public MutableLiveData<Long> getSelectDate() {
        return selectDate;
    }

    public void updateAttendDetails(long staffId, long date) {
        Date selectDate = new Date(date);
        updateNormalAttendList(staffId, selectDate);
        updateAbsentAttendList(staffId, selectDate);
        updateLateAttendList(staffId, selectDate);
        updateLeaveEarlyAttendList(staffId, selectDate);
    }
}
