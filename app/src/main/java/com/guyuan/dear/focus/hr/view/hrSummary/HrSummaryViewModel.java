package com.guyuan.dear.focus.hr.view.hrSummary;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.hr.bean.HrSummaryBean;
import com.guyuan.dear.focus.hr.repos.HrSummaryRepo;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description: 人员概况主界面的视图模型
 * @since: 2020/9/18 11:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryViewModel extends BaseDearViewModel {
    private HrSummaryRepo repo = new HrSummaryRepo();
    private MutableLiveData<HrSummaryBean> staffSummary = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowNormalStaffs = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowAbsentStaffs = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowLateStaffs = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowEarlyLeaveStaffs = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowOnLeaveStaffs = new MutableLiveData<>();


    public Disposable updateStaffSum(){
        return repo.getStaffSummary(new BaseNetCallback<HrSummaryBean>() {
            @Override
            protected void handleResult(HrSummaryBean result) {
                staffSummary.postValue(result);
            }
        });
    }

    public MutableLiveData<HrSummaryBean> getStaffSummary() {
        return staffSummary;
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowNormalStaffs() {
        return onClickShowNormalStaffs;
    }

    public void setOnClickShowNormalStaffs(View.OnClickListener onClickShowNormalStaffs) {
        this.onClickShowNormalStaffs.postValue(onClickShowNormalStaffs);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowAbsentStaffs() {
        return onClickShowAbsentStaffs;
    }

    public void setOnClickShowAbsentStaffs(View.OnClickListener onClickShowAbsentStaffs) {
        this.onClickShowAbsentStaffs.postValue(onClickShowAbsentStaffs);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowLateStaffs() {
        return onClickShowLateStaffs;
    }

    public void setOnClickShowLateStaffs(View.OnClickListener onClickShowLateStaffs) {
        this.onClickShowLateStaffs.postValue(onClickShowLateStaffs);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowEarlyLeaveStaffs() {
        return onClickShowEarlyLeaveStaffs;
    }

    public void setOnClickShowEarlyLeaveStaffs(View.OnClickListener onClickShowEarlyLeaveStaffs) {
        this.onClickShowEarlyLeaveStaffs.postValue(onClickShowEarlyLeaveStaffs);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowOnLeaveStaffs() {
        return onClickShowOnLeaveStaffs;
    }

    public void setOnClickShowOnLeaveStaffs(View.OnClickListener onClickShowOnLeaveStaffs) {
        this.onClickShowOnLeaveStaffs.postValue(onClickShowOnLeaveStaffs);
    }
}
