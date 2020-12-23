package com.guyuan.dear.focus.device.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.device.adapter.DeviceExceptionAdapter;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 17:54
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceExceptionFragment extends BaseListFragment<DeviceExceptionBean.ContentBean, FragmentListBinding, FocusDeviceViewModel> {

    public static final String TAG = "FocusDeviceExceptionFragment";

    public static FocusDeviceExceptionFragment newInstance() {

        Bundle args = new Bundle();

        FocusDeviceExceptionFragment fragment = new FocusDeviceExceptionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        setContainerBackground(R.color.bg_window);
        if (viewModel != null) {
            viewModel.getExceptionDevice(ConstantValue.FIRST_PAGE);
        }

        DeviceExceptionAdapter exceptionAdapter = new DeviceExceptionAdapter(getContext(), listData,
                R.layout.item_focus_device_exception);
        adapter = new BaseRecyclerViewAdapter(exceptionAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
    }

    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        viewModel.getExceptionDevice(currentPage);
    }

    @Override
    protected void loadMore() {
        viewModel.getExceptionDevice(++currentPage);
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
