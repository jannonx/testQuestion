package com.guyuan.dear.focus.hr.view.hrStaffStatusInfo;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentStaffStatusInfoBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:41
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffStatusInfoFragment extends BaseMvvmFragment<FragmentStaffStatusInfoBinding,StaffStatusInfoViewModel> {

    public static StaffStatusInfoFragment getInstance(long staffId){
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantValue.KEY_STAFF_ID,staffId);
        StaffStatusInfoFragment fragment = new StaffStatusInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_staff_status_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_staff_status_info;
    }
}
