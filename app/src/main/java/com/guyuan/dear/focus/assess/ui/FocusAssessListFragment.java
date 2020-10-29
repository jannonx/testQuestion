package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.assess.adapter.AssessListAdapter;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 10:24
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessListFragment extends BaseListSearchFragment<AssessListBean.ContentBean,
        FragmentListSearchBinding, FocusAssessViewModel> {

    public static final String TAG = "FocusListFragment";
    public static final String TYPE = "type";
    public static final int TOTAL = 0;       //所有评审
    public static final int PASS = 30;       //已通过评审
    public static final int NOT_PASS = 40;   //未通过评审
    private int type;

    public static FocusAssessListFragment newInstance(int type, String searchContent) {

        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        args.putString(ConstantValue.KEY_CONTENT, searchContent);
        FocusAssessListFragment fragment = new FocusAssessListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            type = getArguments().getInt(TYPE);
            String searchContent = getArguments().getString(ConstantValue.KEY_CONTENT);
            etSearch.setText(searchContent);
            viewModel.getAssessList(ConstantValue.FIRST_PAGE, ConstantValue.PAGE_SIZE, searchContent, type);
            AssessListAdapter listAdapter = new AssessListAdapter(listData,
                    R.layout.item_focus_assess_list);
            adapter = new BaseRecyclerViewAdapter(listAdapter);
            recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            recycleView.setAdapter(adapter);
        }

    }

    @Override
    protected void refresh() {
        String searchContent = etSearch.getText() == null ? "" : etSearch.getText().toString();
        currentPage = ConstantValue.FIRST_PAGE;
        viewModel.getAssessList(currentPage, ConstantValue.PAGE_SIZE, searchContent, type);
    }

    @Override
    protected void loadMore() {
        String searchContent = etSearch.getText() == null ? "" : etSearch.getText().toString();
        viewModel.getAssessList(++currentPage, ConstantValue.PAGE_SIZE, searchContent, type);
    }

    @Override
    protected boolean isPullEnable() {
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return BR.assessViewModel;
    }
}