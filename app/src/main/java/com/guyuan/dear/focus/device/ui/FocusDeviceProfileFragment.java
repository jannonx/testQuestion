package com.guyuan.dear.focus.device.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentFocusDeviceProfileBinding;
import com.guyuan.dear.focus.device.adapter.DeviceProfileAdapter;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;
import com.guyuan.dear.focus.device.ui.detail.FocusDeviceDetailActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 18:19
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceProfileFragment extends BaseListFragment<FactoryRealTimeBean.WorkshopsBean, FragmentFocusDeviceProfileBinding> {

    public static final String TAG = "FocusDeviceProfileFragment";
    private long currentFactoryID;
    private FocusDeviceViewModel viewModel;

    public static FocusDeviceProfileFragment newInstance() {

        Bundle args = new Bundle();

        FocusDeviceProfileFragment fragment = new FocusDeviceProfileFragment();
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
        return R.layout.fragment_focus_device_profile;
    }

    @Override
    protected void initView() {
        if (viewModel != null) {
            viewModel.getFactoryList(ConstantValue.FIRST_PAGE);
        }
        FactoryBean factoryBean = CommonUtils.getFactoryListFromCache();
        if (factoryBean != null) {
            setUI(factoryBean);
        }
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

        //    devicePresenter.getFactoryRealTimeList(currentFactoryID);
    }

    public void setUI(FactoryBean factoryBean) {
//        List<FactoryBean.ContentBean> dataList = factoryBean.getContent();
//        if (dataList != null && dataList.size() > 0) {
//            dataList.get(0).setSelected(true);
//            devicePresenter.getFactoryRealTimeList(dataList.get(0).getId());
//            farm_rv.init(dataList, new GuYuanCommonTapAdapter.OnItemTappedListener() {
//                @Override
//                public void onItemTapped(AbstractTabContent item, int pos) {
//                    FactoryBean.ContentBean contentBean = dataList.get(pos);
//                    long factoryID = contentBean.getId();
//                    if (currentFactoryID != factoryID) {
//                        currentFactoryID = factoryID;
//                        devicePresenter.getFactoryRealTimeList(currentFactoryID);
//                    }
//                }
//            });
//        }

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
