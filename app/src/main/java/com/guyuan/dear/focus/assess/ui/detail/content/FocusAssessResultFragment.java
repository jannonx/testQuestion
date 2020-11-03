package com.guyuan.dear.focus.assess.ui.detail.content;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.assess.adapter.AssessDetailResultAdapter;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AuditFormResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 11:24
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessResultFragment extends BaseListFragment<AuditFormResultBean, FragmentListBinding, FocusAssessViewModel> {

    public static final String TAG = "FocusAssessResultFragment";
    public static final String DATA = "data";

    public static FocusAssessResultFragment newInstance(List<AuditFormResultBean> resultList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA, new ArrayList<>(resultList));
        FocusAssessResultFragment fragment = new FocusAssessResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            List<AuditFormResultBean> resultList = getArguments().getParcelableArrayList(DATA);
            listData.addAll(resultList);
            AssessDetailResultAdapter resultAdapter = new AssessDetailResultAdapter(listData,
                    R.layout.item_focus_assess_detail_result);
            setDefaultAdapter(resultAdapter);
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