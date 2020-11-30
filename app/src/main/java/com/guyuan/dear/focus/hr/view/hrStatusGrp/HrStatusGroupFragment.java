package com.guyuan.dear.focus.hr.view.hrStatusGrp;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrGroupBinding;
import com.guyuan.dear.focus.hr.adapter.HrStaffAdapter;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail.StaffMonthlyDetailActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/21 18:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGroupFragment extends BaseMvvmFragment<FragmentHrGroupBinding, HrStatusGrpViewModel> {

    private int grpType;
    private long date = -1;

    /**
     * @param grpType {@link HrStatusGroup#GRP_TYPE_NORMAL},{@link HrStatusGroup#GRP_TYPE_LATE},{@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY},
     *                {@link HrStatusGroup#GRP_TYPE_ABSENT},{@link HrStatusGroup#GRP_TYPE_ON_LEAVE}
     */
    public static HrStatusGroupFragment getInstance(int grpType) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, grpType);
        HrStatusGroupFragment fragment = new HrStatusGroupFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * @param grpType {@link HrStatusGroup#GRP_TYPE_NORMAL},{@link HrStatusGroup#GRP_TYPE_LATE},{@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY},
     *                {@link HrStatusGroup#GRP_TYPE_ABSENT},{@link HrStatusGroup#GRP_TYPE_ON_LEAVE}
     */
    public static HrStatusGroupFragment getInstance(int grpType, long date) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, grpType);
        bundle.putLong(ConstantValue.KEY_DATE, date);
        HrStatusGroupFragment fragment = new HrStatusGroupFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_group_vm;
    }

    @Override
    protected void initData() {
        grpType = getArguments().getInt(ConstantValue.KEY_GRP_TYPE);
        date = getArguments().getLong(ConstantValue.KEY_DATE, -1);


    }

    @Override
    protected void initViews() {
        if (date > 0) {
            getViewModel().loadStaffListByType(grpType);
        } else {
            getViewModel().loadStaffListByDateAndType(date, grpType);
        }
        getViewModel().onItemClickListener.postValue(new HrStaffAdapter.HrStaffAdapterItemClickListener() {
            @Override
            public void onItemClick(StaffBean staffBean, int pos) {
                //                StaffAttendDetailActivity.start(getActivity(),staffBean.getId());
                StaffMonthlyDetailActivity.start(getContext(), "", staffBean.getId().intValue());
            }
        });


    }

    @Override
    protected void initListeners() {


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_group;
    }
}
