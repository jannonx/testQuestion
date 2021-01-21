package com.guyuan.dear.test.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentQuestionItemBinding;
import com.guyuan.dear.test.adapter.QuestionItemAdapter;
import com.guyuan.dear.test.bean.ItemQuestionBean;
import com.guyuan.dear.test.bean.TestQuestionBean;
import com.guyuan.dear.test.data.TestViewModel;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


public class QuestionItemFragment extends BaseDataBindingFragment<FragmentQuestionItemBinding, TestViewModel> {
    public static final String TAG = "QuestionItemFragment";
    private List<ItemQuestionBean> listData = new ArrayList<>();
    private QuestionItemAdapter checkGoodsAdapter;
    private BaseRecyclerViewAdapter adapter;

    public static QuestionItemFragment newInstance(ArrayList<TestQuestionBean> dataList, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantValue.KEY_CONTENT, dataList);
        args.putInt(ConstantValue.KEY_INDEX, position);
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
        Bundle arguments = getArguments();
        if (arguments != null && getContext() != null) {
            int index = arguments.getInt(ConstantValue.KEY_INDEX);
            ArrayList<TestQuestionBean> dataList = (ArrayList<TestQuestionBean>) arguments.getSerializable(ConstantValue.KEY_CONTENT);
            TestQuestionBean testQuestionBean = dataList.get(index);
            binding.tvTitle.setText(testQuestionBean.getTitle());

            binding.tvIndex.setText((index + 1) + "/" + dataList.size());
            listData.clear();
            List<ItemQuestionBean> itemList = testQuestionBean.getItemList();
            List<ItemQuestionBean> dealList = new ArrayList<>();
            HashMap<Integer, Integer> selectMap = CommonUtils.getSelectMap();
            LogUtils.showLog("index=" + index + "...contains=" + (selectMap.containsKey(index)));
            if (selectMap.containsKey(index)) {
                for (int ik = 0; ik < itemList.size(); ik++) {
                    ItemQuestionBean innerBean = new ItemQuestionBean();
                    ItemQuestionBean itemQuestionBean = itemList.get(ik);
                    innerBean.setItemType(itemQuestionBean.getItemType());
                    innerBean.setContent(itemQuestionBean.getContent());
                    innerBean.setSelectIndex(selectMap.get(index) == ik ? ik : -1);
                    dealList.add(innerBean);
                }
            } else {
                dealList.addAll(itemList);
            }

            listData.addAll(dealList);
            checkGoodsAdapter = new QuestionItemAdapter(getContext(), listData
                    , R.layout.item_answer_question);
            adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
            binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.baseRecycleView.setAdapter(adapter);
            binding.baseRecycleView.setPullRefreshEnabled(false);
            binding.baseRecycleView.setLoadMoreEnabled(false);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    CommonUtils.putSelectResult(index, position);
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
