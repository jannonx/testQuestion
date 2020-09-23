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
import com.guyuan.dear.databinding.FragmentFocusDeviceOverviewBinding;
import com.guyuan.dear.focus.device.adapter.DeviceNumberAdapter;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.DeviceNumberBean;
import com.guyuan.dear.focus.device.ui.overview.FocusDeviceTypeActivity;
import com.guyuan.dear.focus.device.ui.overview.FocusDeviceTypeFragment;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 14:31
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceOverviewFragment extends BaseListFragment<DeviceNumberBean.EquipmentsBean, FragmentFocusDeviceOverviewBinding> {


    public static final String TAG = "FocusDeviceOverviewFragment";
    private FocusDeviceViewModel viewModel;

    public static FocusDeviceOverviewFragment newInstance() {

        Bundle args = new Bundle();

        FocusDeviceOverviewFragment fragment = new FocusDeviceOverviewFragment();
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
    public int getLayoutID() {
        return R.layout.fragment_focus_device_overview;
    }

    @Override
    protected void initView() {
        if (viewModel != null) {
            viewModel.getOverviewTotalDevice();
        }
        DeviceNumberAdapter numberAdapter = new DeviceNumberAdapter(getContext(), listData,
                R.layout.item_focus_device_number);

        numberAdapter.setTypeClickListener(new DeviceNumberAdapter.OnTypeClickListener() {
            @Override
            public void onTypeClick(DeviceNumberBean.EquipmentsBean bean, int type) {
                FocusDeviceTypeActivity.start(getContext(), bean.getType(), bean.getTypeId(), type);
            }
        });

        adapter = new BaseRecyclerViewAdapter(numberAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                DeviceNumberBean.EquipmentsBean bean = listData.get(i);
                String title = bean.getType();
                long id = bean.getTypeId();
                FocusDeviceTypeActivity.start(getContext(), title, id, FocusDeviceTypeFragment.TOTAL);
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        binding.totalNumberCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusDeviceTypeActivity.start(getContext(), "设备总数", 0, FocusDeviceTypeFragment.TOTAL);
            }
        });

        binding.totalOpenLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusDeviceTypeActivity.start(getContext(), "设备总数", 0, FocusDeviceTypeFragment.OPEN);
            }
        });

        binding.totalRepairLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusDeviceTypeActivity.start(getContext(), "设备总数", 0, FocusDeviceTypeFragment.REPAIR);
            }
        });

        binding.totalStopLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusDeviceTypeActivity.start(getContext(), "设备总数", 0, FocusDeviceTypeFragment.STOP);
            }
        });

        binding.totalExceptionLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusDeviceTypeActivity.start(getContext(), "设备总数", 0, FocusDeviceTypeFragment.EXCEPTION);
            }
        });

        binding.totalMaintainLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusDeviceTypeActivity.start(getContext(), "设备总数", 0, FocusDeviceTypeFragment.MAINTAIN);
            }
        });
    }

    public void setUI(DeviceNumberBean deviceNumberBean) {
        binding.setNumber(deviceNumberBean);
        if(deviceNumberBean.getEquipments()!=null){
            setListData(deviceNumberBean.getEquipments());
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
}
