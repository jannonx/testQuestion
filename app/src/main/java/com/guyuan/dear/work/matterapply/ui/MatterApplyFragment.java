package com.guyuan.dear.work.matterapply.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentWorkMatterApplyBinding;
import com.guyuan.dear.work.matterapply.data.MatterApplyViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 19:20
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyFragment extends BaseDataBindingFragment<FragmentWorkMatterApplyBinding, MatterApplyViewModel> {

    public static final String TAG = "ApplyFragment";

    public static MatterApplyFragment newInstance() {

        Bundle args = new Bundle();

        MatterApplyFragment fragment = new MatterApplyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_matter_apply;
    }

    @Override
    protected void initialization() {

    }
}