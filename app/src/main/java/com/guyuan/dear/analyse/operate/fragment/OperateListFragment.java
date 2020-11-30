package com.guyuan.dear.analyse.operate.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.activity.OperateDetailActivity;
import com.guyuan.dear.analyse.operate.adpter.OperateListAdapter;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.bean.OperateType;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.databinding.FragmentAnalyseOperateOverviewBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.DateUtil;
import com.guyuan.dear.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--运营效益--概览
 * @author: 许建宁
 * @since: 2020/11/30 11:25
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateListFragment extends BaseDataBindingFragment<FragmentAnalyseOperateOverviewBinding, OperateViewModel> {

    public static final String TAG = OperateListFragment.class.getSimpleName();
    private BaseRecyclerViewAdapter adapter;
    private List<OperateAnalyseBean> listData = new ArrayList<>();
    private OperateOverViewBean overViewBean;

    public static OperateListFragment newInstance(OperateOverViewBean bean) {
        Bundle args = new Bundle();
        OperateListFragment fragment = new OperateListFragment();
        args.putSerializable(ConstantValue.KEY_CONTENT, bean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_analyse_operate_overview;
    }

    @Override
    protected void initialization() {

        overViewBean = (OperateOverViewBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        OperateListAdapter checkGoodsAdapter = new OperateListAdapter(getContext(),
                listData, R.layout.item_operate_overview);
        adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (OperateType.TYPE_TOTAL == overViewBean.getOperateType()) {
                    OperateDetailActivity.start(getContext(), listData.get(position));
                }
            }
        });

        binding.labelSum.setText(overViewBean.getOperateType().getDes());
        if (OperateType.TYPE_ACTUAL == overViewBean.getOperateType()) {
            binding.tvSum.setText(overViewBean.getTotalCollection());
            setDownAndUpText(overViewBean.getLastTotalCollection(), binding.ivSumArrow, binding.tvSumPercent);

            viewModel.getPaymentList(overViewBean.getSearchTime());
            viewModel.getActualEvent().observe(getActivity(), new Observer<List<OperateAnalyseBean>>() {
                @Override

                public void onChanged(List<OperateAnalyseBean> data) {
                    dealResult(data);
                }
            });
        } else {
            binding.tvSum.setText(overViewBean.getTotalCost());
            setDownAndUpText(overViewBean.getLastTotalCost(), binding.ivSumArrow, binding.tvSumPercent);

            viewModel.getCostList(overViewBean.getSearchTime());
            viewModel.getTotalEvent().observe(getActivity(), new Observer<List<OperateAnalyseBean>>() {
                @Override

                public void onChanged(List<OperateAnalyseBean> data) {
                    dealResult(data);
                }
            });
        }


    }

    /**
     * 数据处理
     *
     * @param data
     */
    private void dealResult(List<OperateAnalyseBean> data) {
        listData.clear();
        if (data == null || data.isEmpty()) return;
        List<OperateAnalyseBean> tempList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            OperateAnalyseBean bean = data.get(i);
            bean.setOperateType(overViewBean.getOperateType());
            tempList.add(bean);
        }
        listData.addAll(tempList);
        adapter.refreshData();
    }

    public void setDownAndUpText(String number, AppCompatImageView imageView, AppCompatTextView textView) {
        imageView.setImageResource(isDown(number) ? R.mipmap.ic_arrow_green_down : R.mipmap.ic_arrow_red_up);

        textView.setText(number);
        textView.setTextColor(getActivity().getResources().getColor(
                isDown(number) ? R.color.color_green_2fc25b : R.color.color_red_F04864));
    }


    public boolean isDown(String number) {
        if (StringUtils.isTextEmpty(number)) {
            return false;
        }

        return number.startsWith("-");
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


}
