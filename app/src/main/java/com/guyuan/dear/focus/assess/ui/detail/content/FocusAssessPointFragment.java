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

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :我的关注-评审点fragment
 * @since: 2020/10/21 11:36
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessPointFragment extends BaseListFragment<AuditContentBean, FragmentListBinding,
        FocusAssessViewModel> {


    public static final String TAG = "FocusAssessPointFragment";
    public static final String DATA = "data";

    public static FocusAssessPointFragment newInstance(List<AuditContentBean> pointList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA, new ArrayList<>(pointList));
        FocusAssessPointFragment fragment = new FocusAssessPointFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            List<AuditContentBean> pointList = getArguments().getParcelableArrayList(DATA);
            listData.addAll(pointList);
            AssessDetailPointAdapter pointAdapter = new AssessDetailPointAdapter(listData,
                    R.layout.item_focus_assess_detail_point);
            setDefaultAdapter(pointAdapter);
        }
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