package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentExploreContentBinding;
import com.guyuan.dear.focus.projectsite.adapter.ProjectSiteStatusAdapter;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @description: 我的关注--生产详情
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteStatusFragment extends BaseDataBindingFragment<FragmentExploreContentBinding, FocusProjectSiteViewModel> {

    public static final String TAG = ProjectSiteStatusFragment.class.getSimpleName();
    private SiteExploreBean detailProjectData;
    private View footerView;
    private LinearLayoutCompat llTempEmptyView;
    private BaseRecyclerViewAdapter adapter;
    private List<ProjectSiteStatusBean> listData = new ArrayList<>();

    public static ProjectSiteStatusFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        ProjectSiteStatusFragment fragment = new ProjectSiteStatusFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        EventBus.getDefault().register(this);
        detailProjectData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        if (detailProjectData == null) {
            return;
        }

        viewModel.getProjectSiteStatusList(detailProjectData.getId(), detailProjectData.getProjectReportType().getCode());
        viewModel.getProjectSiteStatusEvent().observe(getActivity(), new Observer<List<ProjectSiteStatusBean>>() {
            @Override
            public void onChanged(List<ProjectSiteStatusBean> data) {
                setListData(data);
                llTempEmptyView.setVisibility((data == null || data.size() == 0) ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setListData(List<ProjectSiteStatusBean> dataList) {
        listData.clear();
        listData.addAll(dataList);
        ProjectSiteStatusAdapter listAdapter = new ProjectSiteStatusAdapter(getContext(), listData,
                R.layout.item_project_site_status);

         adapter = new BaseRecyclerViewAdapter(listAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setLoadMoreEnabled(false);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        addContentFooterView();

    }

    /**
     * 添加列表FooterView,增加拖动范围
     */
    private void addContentFooterView() {
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.coordinator_empty_view, binding.baseRecycleView, false);

        llTempEmptyView = footerView.findViewById(R.id.ll_empty_view);
        footerView.findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshStatusList();
            }
        });

        adapter.addFooterView(footerView);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshMessage(EventAnswerListRefresh event) {
        refreshStatusList();
    }

    private void refreshStatusList() {
        viewModel.getProjectSiteStatusList(detailProjectData.getId(), detailProjectData.getProjectReportType().getCode());
    }


    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_explore_content;
    }


}
