package com.jannonx.electric.test.fragment;


import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.jannonx.electric.R;
import com.jannonx.electric.databinding.FragmentQuestionItemBinding;
import com.jannonx.electric.test.adapter.QuestionItemAdapter;
import com.jannonx.electric.test.bean.ExamFunctionType;
import com.jannonx.electric.test.bean.ItemQuestionBean;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.test.data.TestViewModel;
import com.jannonx.electric.utils.CommonUtils;
import com.jannonx.electric.utils.ConstantValue;
import com.jannonx.electric.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class QuestionItemFragment extends BaseDataBindingFragment<FragmentQuestionItemBinding, TestViewModel> {
    public static final String TAG = "QuestionItemFragment";
    private List<ItemQuestionBean> listData = new ArrayList<>();
    private QuestionItemAdapter questionItemAdapter;
    private BaseRecyclerViewAdapter adapter;
    private Bundle arguments;
    private int examIndex;
    private ExamFunctionType functionType;

    public static QuestionItemFragment newInstance(ArrayList<TestQuestionBean> dataList, int position, ExamFunctionType type) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantValue.KEY_CONTENT, dataList);
        args.putInt(ConstantValue.KEY_INDEX, position);
        args.putSerializable(ConstantValue.KEY_TYPE, type);
        QuestionItemFragment fragment = new QuestionItemFragment();
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
            functionType = (ExamFunctionType) arguments.getSerializable(ConstantValue.KEY_TYPE);
            switch (functionType) {
                case TYPE_EXAM:
                    dealExamQuestion();
                    break;
                case TYPE_PARSE:
                    browseAnswerData();
                    break;
                default:
            }


        }
    }

    private void browseAnswerData() {
        binding.llcParseContent.setVisibility(View.VISIBLE);
        ArrayList<TestQuestionBean> dataList = (ArrayList<TestQuestionBean>) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        TestQuestionBean testQuestionBean = dataList.get(examIndex);
        binding.tvTitle.setText(testQuestionBean.getTitle());
        binding.tvIndex.setText((examIndex + 1) + "/" + dataList.size());
        listData.clear();
        listData.addAll(testQuestionBean.getItemList());

        questionItemAdapter = new QuestionItemAdapter(getContext(), listData
                , R.layout.item_answer_question);
        adapter = new BaseRecyclerViewAdapter(questionItemAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);
    }

    private void dealExamQuestion() {

        ArrayList<TestQuestionBean> dataList = (ArrayList<TestQuestionBean>) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        TestQuestionBean testQuestionBean = dataList.get(examIndex);
        binding.tvTitle.setText(testQuestionBean.getTitle());

        binding.tvIndex.setText((examIndex + 1) + "/" + dataList.size());
        listData.clear();
        List<ItemQuestionBean> itemList = testQuestionBean.getItemList();
        List<ItemQuestionBean> dealList = new ArrayList<>();
        HashMap<Integer, Integer> selectMap = CommonUtils.getSelectMap();
        LogUtils.showLog("examIndex=" + examIndex + "...contains=" + (selectMap.containsKey(examIndex)));
        if (selectMap.containsKey(examIndex)) {
            for (int ik = 0; ik < itemList.size(); ik++) {
                ItemQuestionBean innerBean = new ItemQuestionBean();
                ItemQuestionBean itemQuestionBean = itemList.get(ik);
                innerBean.setItemType(itemQuestionBean.getItemType());
                innerBean.setContent(itemQuestionBean.getContent());
                innerBean.setSelectIndex(selectMap.get(examIndex) == ik ? ik : -1);
                dealList.add(innerBean);
            }
        } else {
            dealList.addAll(itemList);
        }
        setExamData(dealList);
    }


    private void setExamData(List<ItemQuestionBean> dealList) {
        listData.addAll(dealList);
        questionItemAdapter = new QuestionItemAdapter(getContext(), listData
                , R.layout.item_answer_question);
        adapter = new BaseRecyclerViewAdapter(questionItemAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonUtils.putSelectResult(examIndex, position);
                List<ItemQuestionBean> tempList = new ArrayList<>();
                for (int i = 0; i < listData.size(); i++) {
                    ItemQuestionBean newBean = new ItemQuestionBean();
                    ItemQuestionBean itemQuestionBean = listData.get(i);
                    newBean.setContent(itemQuestionBean.getContent());
                    newBean.setItemType(itemQuestionBean.getItemType());
                    newBean.setSelectIndex(i == position ? position : -1);
//                        LogUtils.showLog("position="+position+"..."+newBean.toString());
                    tempList.add(newBean);
                }
                setListData(tempList);
            }
        });

    }

    /**
     * 更新数据
     *
     * @param dataList
     */
    public void setListData(List<ItemQuestionBean> dataList) {
        listData.clear();
        listData.addAll(dataList);
        adapter.refreshData();
        binding.baseRecycleView.refreshComplete(1);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

}