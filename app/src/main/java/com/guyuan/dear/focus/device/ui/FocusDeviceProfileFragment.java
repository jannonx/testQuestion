package com.guyuan.dear.focus.device.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.FragmentFocusDeviceProfileBinding;
import com.guyuan.dear.focus.device.adapter.DeviceProfileAdapter;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;
import com.guyuan.dear.focus.device.ui.detail.FocusDeviceDetailActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 18:19
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceProfileFragment extends BaseListFragment<FactoryRealTimeBean.WorkshopsBean, FragmentFocusDeviceProfileBinding, FocusDeviceViewModel> implements TabLayoutHelper.TabLayoutListener {

    public static final String TAG = "FocusDeviceProfileFragment";
    private long currentFactoryID;
    private FactoryBean factoryBean;

    public static FocusDeviceProfileFragment newInstance() {

        Bundle args = new Bundle();

        FocusDeviceProfileFragment fragment = new FocusDeviceProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_focus_device_profile;
    }

    @Override
    protected void initView() {
        DeviceProfileAdapter farmNewContentAdapter =
                new DeviceProfileAdapter(getContext(), listData, R.layout.item_line);

        farmNewContentAdapter.setLineListener(new DeviceProfileAdapter.FarmNewContentListener() {
            @Override
            public void showDeviceDetail(EquipmentBean bean) {
                Intent intent = new Intent(getContext(), FocusDeviceDetailActivity.class);
                intent.putExtra(FocusDeviceDetailActivity.TYPE, FocusDeviceDetailActivity.NORMAL);
                intent.putExtra(FocusDeviceDetailActivity.CONTENT, bean);
                startActivity(intent);
            }
        });

        adapter = new BaseRecyclerViewAdapter(farmNewContentAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        viewModel.getFactoryList();
    }

    public void setTab(FactoryBean factoryBean) {
        this.factoryBean = factoryBean;
        List<FactoryBean.ContentBean> dataList = factoryBean.getContent();
        if (dataList != null && dataList.size() > 0) {
            viewModel.getFactoryDevice(dataList.get(0).getId());

            new TabLayoutHelper(getActivity(), binding.farmTl, dataList.size(), R.layout.item_tab_white_to_blue_round_corner)
                    .setTab()
                    .setListener(this)
                    .addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            FactoryBean.ContentBean contentBean = dataList.get(tab.getPosition());
                            long factoryID = contentBean.getId();
                            if (currentFactoryID != factoryID) {
                                currentFactoryID = factoryID;
                                viewModel.getFactoryDevice(currentFactoryID);
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
        if (factoryBean != null) {
            List<FactoryBean.ContentBean> factoryList = factoryBean.getContent();
            if (factoryList != null && factoryList.size() > 0) {
                factoryTV.setText(factoryList.get(currentPosition).getName());
            }
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
