package com.guyuan.dear.focus.security.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.FragmentFocusSecurityProfileBinding;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.focus.security.adapter.SecurityProfileAdapter;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;
import com.guyuan.dear.focus.security.data.beans.DangerProfileBean;
import com.guyuan.dear.focus.security.data.beans.SecurityBaseBean;
import com.guyuan.dear.focus.security.data.beans.SecurityContentBean;
import com.guyuan.dear.focus.security.ui.detail.FocusSecurityDetailActivity;
import com.guyuan.dear.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 11:06
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityProfileFragment extends BaseListFragment<DangerProfileBean.WorkshopSecurityVoBean,
        FragmentFocusSecurityProfileBinding> implements TabLayoutHelper.TabLayoutListener {

    public static final String TAG = "FocusSecurityProfileFragment";
    private FocusSecurityViewModel viewModel;
    private FactoryBean factoryBean;
    private long currentFactoryID;

    public static FocusSecurityProfileFragment newInstance() {

        Bundle args = new Bundle();

        FocusSecurityProfileFragment fragment = new FocusSecurityProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_focus_security_profile;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FocusSecurityActivity activity = (FocusSecurityActivity) context;
        viewModel = activity.getViewModel();
    }

    @Override
    protected void initView() {
        setTab();
        setAdapter();
    }


    private void setTab() {
        //    factoryBean = CommonUtils.getFactoryListFromCache();
        factoryBean = new FactoryBean();
        List<FactoryBean.ContentBean> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FactoryBean.ContentBean contentBean = new FactoryBean.ContentBean();
            contentBean.setName("factory" + i);
            contentBean.setCode("312312");
            contentBean.setId(213L);
            datas.add(contentBean);
        }
        factoryBean.setContent(datas);
        if (factoryBean != null && factoryBean.getContent() != null && factoryBean.getContent().size() > 0) {
            List<FactoryBean.ContentBean> dataList = factoryBean.getContent();

            if (viewModel != null) {
                viewModel.getDangerPointProfile(dataList.get(0).getId());
            }

            new TabLayoutHelper(getActivity(), binding.securityProfileTl, dataList.size(), R.layout.item_tab_white_to_blue_round_corner)
                    .setTab()
                    .setListener(this)
                    .addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            FactoryBean.ContentBean contentBean = dataList.get(tab.getPosition());
                            long factoryID = contentBean.getId();
                            if (currentFactoryID != factoryID) {
                                currentFactoryID = factoryID;
                                viewModel.getDangerPointProfile(currentFactoryID);
                            }
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    }).setCustomView();
        }
    }


    private void setAdapter() {
        SecurityProfileAdapter contentAdapter =
                new SecurityProfileAdapter(getContext(), listData, R.layout.item_focus_content);

        contentAdapter.setListener(new SecurityProfileAdapter.ContentListener() {
            @Override
            public void showDetail(SecurityBaseBean detailBean) {
                SecurityContentBean contentBean = new SecurityContentBean();
                contentBean.setTSecurityBaseVo(detailBean);
                FocusSecurityDetailActivity.starter(getContext(), contentBean,
                        FocusSecurityDetailActivity.SECURITY_SEARCH);
            }
        });

        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(contentAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);
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
    public void setCustomContent(View customView, int currentPosition) {
        AppCompatTextView factoryTV = customView.findViewById(R.id.item_tab_white_to_blue_round_corner_tv_name);
        factoryTV.setText(factoryBean.getContent().get(currentPosition).getName());
    }
}
