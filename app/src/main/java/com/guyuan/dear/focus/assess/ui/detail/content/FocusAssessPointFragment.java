package com.guyuan.dear.focus.assess.ui.detail.content;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.assess.adapter.AssessDetailPointAdapter;
import com.guyuan.dear.focus.assess.adapter.AssessListAdapter;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AuditContentBean;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 11:36
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessPointFragment extends BaseListFragment<AuditContentBean, FragmentListBinding, FocusAssessViewModel> {


    public static final String TAG = "FocusAssessPointFragment";

    public static FocusAssessPointFragment newInstance() {

        Bundle args = new Bundle();

        FocusAssessPointFragment fragment = new FocusAssessPointFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        AssessDetailPointAdapter pointAdapter = new AssessDetailPointAdapter(listData,
                R.layout.item_focus_assess_detail_point);
        setDefaultAdapter(pointAdapter);
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