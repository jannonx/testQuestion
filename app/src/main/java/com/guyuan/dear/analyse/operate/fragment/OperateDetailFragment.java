package com.guyuan.dear.analyse.operate.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.databinding.FragmentAnalyseOperateDetailBinding;

/**
 * @description: 我的关注--运营效益--概览
 * @author: 许建宁
 * @since: 2020/11/30 11:25
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateDetailFragment extends BaseDataBindingFragment<FragmentAnalyseOperateDetailBinding, OperateViewModel> {

    public static final String TAG = OperateDetailFragment.class.getSimpleName();

    public static OperateDetailFragment newInstance() {
        Bundle args = new Bundle();
        OperateDetailFragment fragment = new OperateDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_analyse_operate_detail;
    }

    @Override
    protected void initialization() {


//        viewModel.getProjectSiteOverViewData();
//        viewModel.getProjectSiteOverViewEvent().observe(getActivity(), new Observer<ProjectOverViewBean>() {
//            @Override
//
//            public void onChanged(ProjectOverViewBean data) {
//                setData(data);
//            }
//        });
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
