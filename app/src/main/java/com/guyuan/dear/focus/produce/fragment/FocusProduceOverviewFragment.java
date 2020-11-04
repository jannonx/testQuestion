package com.guyuan.dear.focus.produce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProduceOverviewBinding;
import com.guyuan.dear.focus.contract.bean.ComContractsBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.ui.FocusProduceActivity;
import com.guyuan.dear.focus.produce.ui.FocusProduceClassifyActivity;
import com.guyuan.dear.focus.produce.util.ChartViewUtils;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceOverviewFragment extends BaseDataBindingFragment<FragmentFocusProduceOverviewBinding, FocusProduceViewModel>
        implements View.OnClickListener {

    public static final String TAG = "FocusProduceOverviewFragment";

    public static FocusProduceOverviewFragment newInstance() {
        Bundle args = new Bundle();
        FocusProduceOverviewFragment fragment = new FocusProduceOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_overview;
    }

    @Override
    protected void initialization() {
        binding.clProduceWait.setOnClickListener(this);
        binding.clProduceComplete.setOnClickListener(this);
        binding.clProduceIng.setOnClickListener(this);
        binding.clProduceException.setOnClickListener(this);
        binding.clProduceDelay.setOnClickListener(this);

        ComContractsBean comContractsBean = new ComContractsBean();
        comContractsBean.setExecutingContracts(30);
        comContractsBean.setExceptionContracts(20);
        comContractsBean.setFinishedContracts(25);

        ChartViewUtils.setFocusChartView(binding.chartView, comContractsBean);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_produce_wait:
                FocusProduceClassifyActivity.start(getContext());

                break;
            case R.id.cl_produce_complete:
                FocusProduceClassifyActivity.start(getContext());

                break;
            case R.id.cl_produce_ing:

                FocusProduceClassifyActivity.start(getContext());
                break;
            case R.id.cl_produce_exception:
                FocusProduceClassifyActivity.start(getContext());

                break;
            case R.id.cl_produce_delay:
                FocusProduceClassifyActivity.start(getContext());

                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            FocusProduceActivity activity = (FocusProduceActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

}
