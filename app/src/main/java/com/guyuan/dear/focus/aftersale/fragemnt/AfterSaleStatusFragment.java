package com.guyuan.dear.focus.aftersale.fragemnt;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.aftersale.adapter.AfterSaleStatusAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.projectsite.adapter.ProjectSiteStatusAdapter;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description: 我的关注--售后服务--排查记录
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class AfterSaleStatusFragment extends BaseListFragment<AfterSaleStatusBean, FragmentListBinding, FocusAfterSaleViewModel> {

    public static final String TAG = AfterSaleStatusFragment.class.getSimpleName();
    private SiteExploreBean detailProjectData;
    private View footerView;

    public static AfterSaleStatusFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        AfterSaleStatusFragment fragment = new AfterSaleStatusFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        EventBus.getDefault().register(this);
        detailProjectData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        if (detailProjectData == null) {
            return;
        }

        AfterSaleStatusAdapter listAdapter = new AfterSaleStatusAdapter(getContext(), listData,
                R.layout.item_focus_after_sale);

        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));


//        viewModel.getProjectSiteStatusList(detailProjectData.getId(), detailProjectData.getProjectReportType().getCode());
//        viewModel.getProjectSiteStatusEvent().observe(getActivity(), new Observer<List<ProjectSiteStatusBean>>() {
//            @Override
//            public void onChanged(List<ProjectSiteStatusBean> data) {
//                setListData(data);
//            }
//        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshMessage(EventAnswerListRefresh event) {
//        viewModel.getProjectSiteStatusList(detailProjectData.getId(), detailProjectData.getProjectReportType().getCode());
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

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
