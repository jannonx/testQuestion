package com.guyuan.dear.work.assess.ui.detail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.databinding.FragmentWorkAssessDetailBinding;
import com.guyuan.dear.service.BackService;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 16:08
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessDetailFragment extends BaseDataBindingFragment<FragmentWorkAssessDetailBinding, WorkAssessViewModel> {

    public static final String TAG = "WorkAssessDetailFragment";

    public static WorkAssessDetailFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_ID, id);
        WorkAssessDetailFragment fragment = new WorkAssessDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return BR.workAssessViewModel;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_assess_detail;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            int id = getArguments().getInt(ConstantValue.KEY_ID);
            if (viewModel != null) {
                viewModel.getAssessDetail(id);
            }
            CommonUtils.getContractStatus(BaseApiService.ID, String.valueOf(id));
        }
    }
}