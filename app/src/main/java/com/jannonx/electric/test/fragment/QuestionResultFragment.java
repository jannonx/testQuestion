package com.jannonx.electric.test.fragment;


import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.jannonx.electric.R;
import com.jannonx.electric.databinding.FragmentQuestionItemBinding;
import com.jannonx.electric.test.adapter.QuestionResultAdapter;
import com.jannonx.electric.test.bean.ExamConditionBean;
import com.jannonx.electric.test.bean.ExamFunctionType;
import com.jannonx.electric.test.bean.ItemQuestionBean;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.test.data.TestViewModel;
import com.jannonx.electric.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

public class QuestionResultFragment extends BaseDataBindingFragment<FragmentQuestionItemBinding, TestViewModel> {
    public static final String TAG = "QuestionItemFragment";
    private List<ItemQuestionBean> listData = new ArrayList<>();
    private QuestionResultAdapter questionItemAdapter;
    private BaseRecyclerViewAdapter adapter;
    private Bundle arguments;
    private int examIndex;
    private ExamFunctionType functionType;
    private  TestQuestionBean examQuestionBean;

    public static QuestionResultFragment newInstance(ArrayList<TestQuestionBean> dataList, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantValue.KEY_CONTENT, dataList);
        args.putInt(ConstantValue.KEY_INDEX, position);
        QuestionResultFragment fragment = new QuestionResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_question_item;
    }

    @Override
    protected void initialization() {
        arguments = getArguments();

        if (arguments != null && getContext() != null) {
            examIndex = arguments.getInt(ConstantValue.KEY_INDEX);
            binding.llcParseContent.setVisibility(View.VISIBLE);
            ArrayList<TestQuestionBean> dataList = (ArrayList<TestQuestionBean>) arguments.getSerializable(ConstantValue.KEY_CONTENT);
            TestQuestionBean browseQuestionBean = dataList.get(examIndex);
            binding.tvTitle.setText(browseQuestionBean.getTitle());
            binding.tvIndex.setText((examIndex + 1) + "/" + dataList.size());

            ExamConditionBean conditionBean=new ExamConditionBean();
            conditionBean.setFunctionType(ExamFunctionType.TYPE_PARSE);
            conditionBean.setTestQuestionBean(browseQuestionBean);
            listData.clear();
            listData.addAll(browseQuestionBean.getItemList());

            questionItemAdapter = new QuestionResultAdapter(getContext(), browseQuestionBean
                    , R.layout.item_answer_question);
            adapter = new BaseRecyclerViewAdapter(questionItemAdapter);
            binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.baseRecycleView.setAdapter(adapter);
            binding.baseRecycleView.setPullRefreshEnabled(false);
            binding.baseRecycleView.setLoadMoreEnabled(false);


        }
    }





    @Override
    protected int getVariableId() {
        return 0;
    }

}