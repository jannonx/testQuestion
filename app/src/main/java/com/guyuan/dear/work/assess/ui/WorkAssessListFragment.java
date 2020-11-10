package com.guyuan.dear.work.assess.ui;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.assess.adapter.WorkAssessAdapter;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;
import com.guyuan.dear.work.assess.data.bean.WorkAssessListBean;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 15:30
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessListFragment extends BaseListSearchFragment<WorkAssessListBean,
        FragmentListSearchBinding, WorkAssessViewModel> {

    public static final String TAG = "WorkAssessListFragment";
    public static final int TOTAL = 0;   //总的
    public static final int CREATE = 1;  //我的新建
    private int type;
    private String currentContent = "";

    public static WorkAssessListFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_STATUS_TYPE, type);
        WorkAssessListFragment fragment = new WorkAssessListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null && viewModel != null) {
            WorkAssessAdapter workAssessAdapter = new WorkAssessAdapter(listData, R.layout.item_work_assess);
            setDefaultAdapter(workAssessAdapter);
            type = getArguments().getInt(ConstantValue.KEY_STATUS_TYPE);
            viewModel.getAssessList(FIRST_PAGE, currentContent, type);
        }
    }


    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        viewModel.getAssessList(currentPage, currentContent, type);
    }

    @Override
    protected void loadMore() {
        viewModel.getAssessList(++currentPage, currentContent, type);
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
    protected void onSearch(String text) {
        super.onSearch(text);
        refresh();
    }

    @Override
    protected void editTextChanged(String text) {
        super.editTextChanged(text);
        currentContent = text;
    }

    @Override
    protected void editEmptyChange() {
        super.editEmptyChange();
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}