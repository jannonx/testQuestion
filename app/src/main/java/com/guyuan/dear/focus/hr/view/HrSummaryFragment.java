package com.guyuan.dear.focus.hr.view;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrSummaryBinding;
import com.guyuan.dear.focus.hr.viewmodel.HrSummaryViewModel;

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
    protected void initialization() {

    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_summary_vm;
    }
}
