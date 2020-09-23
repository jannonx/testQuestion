package com.guyuan.dear.focus.hr.view.hrGrp;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrGroupBinding;
import com.guyuan.dear.focus.hr.adapter.HrStaffAdapter;
import com.guyuan.dear.focus.hr.bean.StaffBean;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/21 18:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrGroupFragment extends BaseMvvmFragment<FragmentHrGroupBinding, HrGrpViewModel> {

    private int grpType;

    public static HrGroupFragment getInstance(int grpType) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, grpType);
        HrGroupFragment fragment = new HrGroupFragment();
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


    }

    @Override
    protected void initViews() {
        getViewModel().setGrpType(grpType);
        getViewModel().update();

    }

    @Override
    protected void initListeners() {
        getViewModel().setCallback(new HrStaffAdapter.HrStaffAdapterCallback() {
            @Override
            public void onClickLoadMore(int grpType, int index, int size) {
                showLoading(getParentFragmentManager());
                getViewModel().loadMoreStaffs( grpType,  index,  size);
                hideLoading();
            }

            @Override
            public void onClickStaff(StaffBean item, int position) {
                //todo 跳转到个人详情

            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_group;
    }
}
