package com.guyuan.dear.office.clockIn.view;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentClockInBinding;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 10:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInFragment extends BaseMvvmFragment<FragmentClockInBinding, ClockInViewModel> {

    public static ClockInFragment getInstance() {
        return new ClockInFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return com.guyuan.dear.BR.fragment_clock_in_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        getViewModel().initViews();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_clock_in;
    }
}
