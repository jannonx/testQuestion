package com.guyuan.dear.focus.hr.view.hrAbnormalSum;

import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.searchview.HrSearchView;
import com.guyuan.dear.databinding.FragmentHrAnormalSumBinding;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail.StaffMonthlyDetailActivity;
import com.guyuan.dear.focus.hr.view.hrStatusGrp.HrStatusGroupActivity;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 人员异常列表
 * Author: 廖华凯
 * Date: 2020/11/28
 * Project: Dear
 * Description:
 */
public class HrAbnormalSumFragment extends BaseMvvmFragment<FragmentHrAnormalSumBinding, HrAbnormalSumViewModel> {

    public static HrAbnormalSumFragment getInstance() {
        return new HrAbnormalSumFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_abnormal_sum_vm;
    }

    @Override
    protected void initData() {
        getViewModel().getDataFromNet(System.currentTimeMillis());

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().onClickSelectDate.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtils.pickTime(getParentFragmentManager(), "请选择日期", Type.YEAR_MONTH_DAY, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getViewModel().getDataFromNet(millseconds);
                    }
                });
            }
        });

        getViewModel().onClickLeaveEarlyList.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(
                        getContext(),
                        "早退人员",
                        HrStatusGroup.GRP_TYPE_LEAVE_EARLY,
                        getViewModel().currentSelectedData.getValue());

            }
        });

        getViewModel().onClickShowAbsentList.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(
                        getContext(),
                        "缺席人员",
                        HrStatusGroup.GRP_TYPE_ABSENT,
                        getViewModel().currentSelectedData.getValue());
            }
        });

        getViewModel().onClickShowLateList.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(
                        getContext(),
                        "迟到人员",
                        HrStatusGroup.GRP_TYPE_LATE,
                        getViewModel().currentSelectedData.getValue());
            }
        });

        getViewModel().onClickShowOnLeaveList.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(
                        getContext(),
                        "请假人员",
                        HrStatusGroup.GRP_TYPE_ON_LEAVE,
                        getViewModel().currentSelectedData.getValue());
            }
        });

        getViewModel().onSelectSearchStaff.postValue(new HrSearchView.SelectStaffCallback() {
            @Override
            public void onStaffSelected(StaffBean staff) {
                StaffMonthlyDetailActivity.start(getActivity(), staff.getName(), staff.getId().intValue());
            }
        });

    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_anormal_sum;
    }
}
