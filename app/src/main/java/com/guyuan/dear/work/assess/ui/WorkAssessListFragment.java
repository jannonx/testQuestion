package com.guyuan.dear.work.assess.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
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

    public static WorkAssessListFragment newInstance() {

        Bundle args = new Bundle();

        WorkAssessListFragment fragment = new WorkAssessListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void init() {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}