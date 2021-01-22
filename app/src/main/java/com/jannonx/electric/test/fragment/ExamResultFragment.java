package com.jannonx.electric.test.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.jannonx.electric.R;
import com.jannonx.electric.databinding.FragmentExamResultBinding;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.test.data.TestViewModel;

import java.util.ArrayList;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/30 11:25
 */
public class ExamResultFragment extends BaseDataBindingFragment<FragmentExamResultBinding, TestViewModel> {

    public static final String TAG = ExamResultFragment.class.getSimpleName();
//    private List<OperateAnalyseBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;

    public static ExamResultFragment newInstance(ArrayList<TestQuestionBean> dataList ) {
        Bundle args = new Bundle();
        ExamResultFragment fragment = new ExamResultFragment();
//        args.putSerializable(ConstantValue.KEY_CONTENT, bean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_exam_result;
    }

    @Override
    protected void initialization() {
//        OperateAnalyseBean analyseBean = (OperateAnalyseBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
//        binding.tvProjectName.setText(analyseBean.getProjectName());
//        binding.tvMoney.setText(analyseBean.getTotalCost());
//        binding.tvCustomerName.setText(analyseBean.getCusName());
//
//
//        OperateDetailAdapter detailAdapter = new OperateDetailAdapter(getContext(),
//                listData, R.layout.item_operate_detail);
//        adapter = new BaseRecyclerViewAdapter(detailAdapter);
//        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.baseRecycleView.setAdapter(adapter);
//        binding.baseRecycleView.setPullRefreshEnabled(false);
//        binding.baseRecycleView.setLoadMoreEnabled(false);



    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
