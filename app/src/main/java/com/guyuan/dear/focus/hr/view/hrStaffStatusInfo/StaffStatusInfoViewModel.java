package com.guyuan.dear.focus.hr.view.hrStaffStatusInfo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.hr.bean.AttendRecord;
import com.guyuan.dear.focus.hr.bean.StaffWorkInfo;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:38
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffStatusInfoViewModel extends BaseViewModel {
    private MutableLiveData<StaffWorkInfo> workInfo = new MutableLiveData<>();
    private MutableLiveData<AttendRecord> attendRecords = new MutableLiveData<>();



    public void loadUserData(long staffId) {

    }
}
