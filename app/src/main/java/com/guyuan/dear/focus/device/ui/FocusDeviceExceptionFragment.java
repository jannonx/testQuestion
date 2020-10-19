package com.guyuan.dear.focus.device.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.device.adapter.DeviceExceptionAdapter;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 17:54
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceExceptionFragment extends BaseListFragment<DeviceExceptionBean.ContentBean, FragmentListBinding> {


    public static final String TAG = "FocusDeviceExceptionFragment";
    private FocusDeviceViewModel viewModel;

    public static FocusDeviceExceptionFragment newInstance() {

        Bundle args = new Bundle();

        FocusDeviceExceptionFragment fragment = new FocusDeviceExceptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FocusDeviceActivity activity = (FocusDeviceActivity) context;
        viewModel = activity.getViewModel();
    }


    @Override
    protected void initView() {
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
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }
}