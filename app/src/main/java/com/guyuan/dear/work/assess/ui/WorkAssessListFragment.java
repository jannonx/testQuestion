package com.guyuan.dear.work.assess.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.assess.adapter.WorkAssessAdapter;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;
import com.guyuan.dear.work.assess.data.bean.WorkAssessItemBean;
import com.guyuan.dear.work.assess.ui.detail.WorkAssessDetailActivity;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 15:30
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessListFragment extends BaseListSearchFragment<WorkAssessItemBean,
        FragmentListSearchBinding, WorkAssessViewModel> {

    public static final String TAG = "WorkAssessListFragment";
    public static final int TOTAL = 0;   //总的
    public static final int CREATE = 1;  //我的新建
    private int type;


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
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    WorkAssessItemBean bean = listData.get(i);
                    WorkAssessDetailActivity.start(getContext(), bean.getCustomerName(), bean.getId());
                }
            });
            type = getArguments().getInt(ConstantValue.KEY_STATUS_TYPE);
            viewModel.getAssessList(FIRST_PAGE, searchContent, type);
        }
    }


    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        viewModel.getAssessList(currentPage, searchContent, type);
    }

    @Override
    protected void loadMore() {
        viewModel.getAssessList(++currentPage, searchContent, type);
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
        return 0;
    }
}