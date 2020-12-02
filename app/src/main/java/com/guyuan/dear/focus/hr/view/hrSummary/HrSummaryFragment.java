package com.guyuan.dear.focus.hr.view.hrSummary;

import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.searchview.HrSearchView;
import com.guyuan.dear.databinding.FragmentHrSummaryBinding;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail.StaffMonthlyDetailActivity;
import com.guyuan.dear.focus.hr.view.hrStatusGrp.HrStatusGroupActivity;
import com.guyuan.dear.work.contractPause.beans.StaffBean;


/**
 * @author: 廖华凯
 * @description: 我的关注-人员-人员概况界面
 * @since: 2020/9/17 16:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryFragment extends BaseMvvmFragment<FragmentHrSummaryBinding, HrSummaryViewModel> {


    public static HrSummaryFragment getInstance() {
        return new HrSummaryFragment();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_summary;
    }


    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_summary_vm;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initViews() {
        getViewModel().updateStaffSum();
    }


    @Override
    protected void initListeners() {
        HrSummaryViewModel viewModel = getViewModel();
        //显示正常出勤人员列表
        viewModel.setOnClickShowNormalStaffs(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(getActivity(), "正常出勤", HrStatusGroup.GRP_TYPE_NORMAL);

            }
        });

        //显示缺勤人员列表
        viewModel.setOnClickShowAbsentStaffs(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(getActivity(), "本日缺勤", HrStatusGroup.GRP_TYPE_ABSENT);
            }
        });

        //显示迟到人员列表
        viewModel.setOnClickShowLateStaffs(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(getActivity(), "本日迟到", HrStatusGroup.GRP_TYPE_LATE);
            }
        });

        //显示早退人员列表
        viewModel.setOnClickShowEarlyLeaveStaffs(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(getActivity(), "本日早退", HrStatusGroup.GRP_TYPE_LEAVE_EARLY);
            }
        });

        //显示请假人员列表
        viewModel.setOnClickShowOnLeaveStaffs(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HrStatusGroupActivity.start(getActivity(), "本日请假", HrStatusGroup.GRP_TYPE_ON_LEAVE);
            }
        });

        //点击搜索栏进入人员搜索界面
        viewModel.onSelectSearchStaff.postValue(new HrSearchView.SelectStaffCallback() {
            @Override
            public void onStaffSelected(StaffBean staff) {
                StaffMonthlyDetailActivity.start(getActivity(), staff.getName(), staff.getId().intValue());
            }
        });


    }

}
