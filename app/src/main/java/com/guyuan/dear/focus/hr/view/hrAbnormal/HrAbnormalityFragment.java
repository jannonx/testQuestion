package com.guyuan.dear.focus.hr.view.hrAbnormal;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrAbnormalityBinding;

/**
 * @author: 廖华凯
 * @description: 我的关注-人员-人员异常
 * @since: 2020/9/17 17:14
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrAbnormalityFragment extends BaseDataBindingFragment<FragmentHrAbnormalityBinding, BaseViewModel> {

    public static HrAbnormalityFragment getInstance(){
        return new HrAbnormalityFragment();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_abnormality;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
