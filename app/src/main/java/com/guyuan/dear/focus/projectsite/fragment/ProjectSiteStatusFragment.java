package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceStatusAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.projectsite.adapter.ProjectSiteStatusAdapter;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description: 我的关注--生产详情
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteStatusFragment extends BaseListFragment<SimpleTabBean, FragmentListBinding, FocusProjectSiteViewModel> {

    public static final String TAG = ProjectSiteStatusFragment.class.getSimpleName();
    private FocusProduceBean produceBean;
    private View footerView;

    public static ProjectSiteStatusFragment newInstance() {
        Bundle bundle = new Bundle();
        ProjectSiteStatusFragment fragment = new ProjectSiteStatusFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        for (int i = 0; i < 5; i++) {
            SimpleTabBean simpleTabBean = new SimpleTabBean();
            listData.add(simpleTabBean);
        }

        ProjectSiteStatusAdapter listAdapter = new ProjectSiteStatusAdapter(getContext(), listData,
                R.layout.item_project_site_status);

        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));

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
