package com.guyuan.dear.focus.hr.view.hrStruct;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrStructBinding;

/**
 * @author: 廖华凯
 * @description: 我的关注-人员-人员详情
 * @since: 2020/9/17 17:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStructFragment extends BaseDataBindingFragment<FragmentHrStructBinding, BaseViewModel> {

    public static HrStructFragment getInstance(){
        return new HrStructFragment();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_struct;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
