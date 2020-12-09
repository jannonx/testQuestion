package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.assess.adapter.AssessListAdapter;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.focus.assess.ui.detail.FocusAssessDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :我的关注-评审列表fragment
 * @since: 2020/10/21 10:24
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessListFragment extends BaseListSearchFragment<AssessListBean.ContentBean,
        FragmentListSearchBinding, FocusAssessViewModel> {

    public static final String TAG = "FocusListFragment";
    public static final String TYPE = "type";
    public static final String ENTRY_TYPE = "entryType";
    public static final int TOTAL = 0;       //所有评审
    public static final int NOT_START_ASSESS = 10;  //待评审
    public static final int ASSESSING = 20;    //评审中
    public static final int PASS = 30;       //已通过评审
    public static final int NOT_PASS = 40;   //未通过评审
    public static final int FROM_OVERVIEW = 100;  //从概览进入
    public static final int FROM_OTHER = 101;   //从其他进入
    private int type;
    private int entryType;

    public static FocusAssessListFragment newInstance(int type, String searchContent, int entryType) {

        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        args.putString(ConstantValue.KEY_CONTENT, searchContent);
        args.putInt(ENTRY_TYPE, entryType);
        FocusAssessListFragment fragment = new FocusAssessListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            searchBar.setHint("输入客户名称、合同编号");
            type = getArguments().getInt(TYPE);
            entryType = getArguments().getInt(ENTRY_TYPE);
            String searchContent = getArguments().getString(ConstantValue.KEY_CONTENT);
            searchBar.setSearchContent(searchContent);
            getListData(entryType, ConstantValue.FIRST_PAGE, ConstantValue.PAGE_SIZE, searchContent, type);
            AssessListAdapter listAdapter = new AssessListAdapter(listData,
                    R.layout.item_focus_assess_list);
            adapter = new BaseRecyclerViewAdapter(listAdapter);
            recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            recycleView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    AssessListBean.ContentBean contentBean = listData.get(i);
                    FocusAssessDetailActivity.start(getContext(), contentBean.getCustomerName(),
                            contentBean.getId(), contentBean.getContractNumber());
                }
            });

        }

    }


    @Override
    protected void refresh() {
        currentPage = ConstantValue.FIRST_PAGE;
        getListData(entryType, currentPage, ConstantValue.PAGE_SIZE, searchContent, type);
    }

    @Override
    protected void loadMore() {
        getListData(entryType, ++currentPage, ConstantValue.PAGE_SIZE, searchContent, type);
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


    private void getListData(int entryType, int pageIndex, int pageSize, String content, int type) {
        switch (entryType) {
            case FROM_OVERVIEW:
                viewModel.getAssessSearchList(pageIndex, pageSize, content, type);
                break;

            case FROM_OTHER:
                viewModel.getAssessList(pageIndex, pageSize, content, type);
                break;
        }
    }
}