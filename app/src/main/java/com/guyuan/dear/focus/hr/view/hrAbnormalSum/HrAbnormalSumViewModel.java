package com.guyuan.dear.focus.hr.view.hrAbnormalSum;

import android.view.View;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.customizeview.searchview.HrSearchView;
import com.guyuan.dear.focus.hr.bean.HrSummaryBean;
import com.guyuan.dear.focus.hr.repos.HrAbnormalSumRepo;
import com.guyuan.dear.message.data.bean.MessageUnreadBean;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.internal.operators.parallel.ParallelFromPublisher;

/**
 * Author: 廖华凯
 * Date: 2020/11/28
 * Project: Dear
 * Description:
 */
public class HrAbnormalSumViewModel extends BaseDearViewModel {
    public MutableLiveData<Integer> absentTotal = new MediatorLiveData<>();
    public MutableLiveData<Integer> onLeaveTotal = new MutableLiveData<>();
    public MutableLiveData<Integer> lateTotal = new MutableLiveData<>();
    public MutableLiveData<Integer> earlyLeaveTotal = new MediatorLiveData<>();
    public MutableLiveData<Long> currentSelectedData = new MutableLiveData<>(System.currentTimeMillis());

    public MutableLiveData<View.OnClickListener> onClickSelectDate = new MutableLiveData<>();

    public MutableLiveData<View.OnClickListener> onClickShowAbsentList = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickShowOnLeaveList = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickShowLateList = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickLeaveEarlyList = new MutableLiveData<>();
    public MutableLiveData<HrSearchView.SelectStaffCallback> onSelectSearchStaff = new MutableLiveData<>();

    private HrAbnormalSumRepo mRepo= new HrAbnormalSumRepo();

    public void getDataFromNet(long date) {
        currentSelectedData.postValue(date);
        mRepo.getHrAbnormalSum(date, new BaseNetCallback<HrSummaryBean>() {
            @Override
            protected void handleResult(HrSummaryBean result) {
                absentTotal.postValue(result.getAbsentAttendance());
                onLeaveTotal.postValue(result.getOnLeaveAttendance());
                earlyLeaveTotal.postValue(result.getEarlyLeaveAttendance());
                lateTotal.postValue(result.getLateAttendance());
            }
        });
    }
}
