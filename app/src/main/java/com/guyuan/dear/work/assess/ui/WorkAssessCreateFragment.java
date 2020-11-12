package com.guyuan.dear.work.assess.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.editListView.EditListViewBean;
import com.guyuan.dear.databinding.FragmentWorkAssessCreateBinding;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 15:33
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessCreateFragment extends BaseDataBindingFragment<FragmentWorkAssessCreateBinding, WorkAssessViewModel> {

    public static final String TAG = "WorkAssessCreateFragment";

    public static WorkAssessCreateFragment newInstance() {

        Bundle args = new Bundle();

        WorkAssessCreateFragment fragment = new WorkAssessCreateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_assess_create;
    }

    @Override
    protected void initialization() {
        if (viewModel != null) {
            viewModel.getCustomerList();
        }
        setElv();
    }

    private void setElv() {
        binding.workAssessElv.setEditable(true);
        binding.workAssessElv.setFragmentManager(childFragmentManager);
    }
}