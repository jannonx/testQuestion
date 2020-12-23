package com.guyuan.dear.analyse.operate.fragment;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.adpter.OperateDetailAdapter;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.databinding.FragmentOperateDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;


/**
 * @description: 我的关注--运营效益--概览
 * @author: 许建宁
 * @since: 2020/11/30 11:25
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateDetailFragment extends BaseDataBindingFragment<FragmentOperateDetailBinding, OperateViewModel> {

    public static final String TAG = OperateDetailFragment.class.getSimpleName();
    private List<OperateAnalyseBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;

    public static OperateDetailFragment newInstance(OperateAnalyseBean bean) {
        Bundle args = new Bundle();
        OperateDetailFragment fragment = new OperateDetailFragment();
        args.putSerializable(ConstantValue.KEY_CONTENT, bean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_operate_detail;
    }

    @Override
    protected void initialization() {
        OperateAnalyseBean analyseBean = (OperateAnalyseBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        binding.tvProjectName.setText(analyseBean.getProjectName());
        binding.tvMoney.setText(analyseBean.getTotalCost());
        binding.tvCustomerName.setText(analyseBean.getCusName());


        OperateDetailAdapter detailAdapter = new OperateDetailAdapter(getContext(),
                listData, R.layout.item_operate_detail);
        adapter = new BaseRecyclerViewAdapter(detailAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);


        viewModel.getOperateDetailData(analyseBean.getId());
        viewModel.getDetailEvent().observe(getActivity(), new Observer<List<OperateAnalyseBean>>() {
            @Override

            public void onChanged(List<OperateAnalyseBean> data) {
                listData.clear();
                listData.addAll(data);
                adapter.refreshData();
            }
        });
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
