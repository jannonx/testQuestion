package com.guyuan.dear.focus.aftersale.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentExploreContentBinding;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.aftersale.adapter.AfterSaleStatusAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description: 我的关注--售后服务--排查记录
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class AfterSaleStatusFragment extends BaseDataBindingFragment<FragmentExploreContentBinding, FocusAfterSaleViewModel> {

    public static final String TAG = AfterSaleStatusFragment.class.getSimpleName();
    private AfterSaleBean afterSaleBean;
    private View footerView;
    private LinearLayoutCompat llTempEmptyView;
    private BaseRecyclerViewAdapter adapter;
    private List<AfterSaleStatusBean> listData = new ArrayList<>();

    public static AfterSaleStatusFragment newInstance(AfterSaleBean data) {
        Bundle bundle = new Bundle();
        AfterSaleStatusFragment fragment = new AfterSaleStatusFragment();
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
        afterSaleBean = (AfterSaleBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        LogUtils.showLog("getContext=" + (getContext() == null));
        if (afterSaleBean == null || getContext() == null) {
            return;
        }

        viewModel.getAfterSaleStatusList(afterSaleBean.getId(), SaleSectionType.TYPE_SECTION_CHECK.getCode());
        viewModel.getAfterSaleStatusEvent().observe(getActivity(), new Observer<List<AfterSaleStatusBean>>() {
            @Override
            public void onChanged(List<AfterSaleStatusBean> data) {
                setListData(data);
                llTempEmptyView.setVisibility((data == null || data.size() == 0) ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setListData(List<AfterSaleStatusBean> dataList) {
        listData.clear();
        listData.addAll(dataList);
        AfterSaleStatusAdapter listAdapter = new AfterSaleStatusAdapter(getContext(), listData,
                R.layout.item_focus_after_sale_status);

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
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.coordinator_empty_view, binding.baseRecycleView, false);
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
        viewModel.getAfterSaleStatusList(afterSaleBean.getId(), afterSaleBean.getSectionType().getCode());
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
