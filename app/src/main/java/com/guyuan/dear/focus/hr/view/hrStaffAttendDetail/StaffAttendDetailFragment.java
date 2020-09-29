package com.guyuan.dear.focus.hr.view.hrStaffAttendDetail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.example.mvvmlibrary.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentStaffAttendDetailBinding;
import com.guyuan.dear.focus.hr.adapter.StaffAttendDetailPagerAdapter;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Date;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:41
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendDetailFragment extends BaseMvvmFragment<FragmentStaffAttendDetailBinding, StaffAttendDetailViewModel> {
    private long staffId;

    public static StaffAttendDetailFragment getInstance(long staffId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantValue.KEY_STAFF_ID, staffId);
        StaffAttendDetailFragment fragment = new StaffAttendDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_staff_attend_detail_vm;
    }

    @Override
    protected void initData() {
        staffId = getArguments().getLong(ConstantValue.KEY_STAFF_ID);
        StaffAttendDetailViewModel viewModel = getViewModel();
        viewModel.loadUserData(staffId);
        viewModel.loadAttendSummary(staffId,System.currentTimeMillis());
        viewModel.updateAttendDetails(staffId,System.currentTimeMillis());
    }

    @Override
    protected void initViews() {
        StaffAttendDetailPagerAdapter adapter = new StaffAttendDetailPagerAdapter(
                getParentFragmentManager(),
                getLifecycle()
        );
        getViewDataBinding().fragmentStaffStatusInfoViewPager.setAdapter(adapter);
        getViewDataBinding().fragmentStaffStatusInfoViewPager.setOffscreenPageLimit(4);
        TabLayoutMediator mediator = new TabLayoutMediator(
                getViewDataBinding().fragmentStaffStatusInfoTabLayout,
                getViewDataBinding().fragmentStaffStatusInfoViewPager,
                true,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(adapter.getTitle(position));
                    }
                }
        );
        mediator.attach();
    }

    @Override
    protected void initListeners() {
        getViewModel().getOnClickSelectDate().postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long selectDate = System.currentTimeMillis();
                long minDate = CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(selectDate,12);
                long maxDate = selectDate;
                AlertDialogUtils.pickTime(
                        getParentFragmentManager(),
                        "请选择日期", minDate, maxDate, selectDate, Type.YEAR_MONTH, new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                Date date = new Date(millseconds);
                                date.setHours(12);
                                date.setMinutes(30);
                                millseconds = date.getTime();
                                getViewModel().loadAttendSummary(staffId,millseconds);
                                getViewModel().updateAttendDetails(staffId,millseconds);
                            }
                        }
                );
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_staff_attend_detail;
    }
}
