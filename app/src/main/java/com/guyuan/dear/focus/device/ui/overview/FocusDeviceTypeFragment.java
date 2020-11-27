package com.guyuan.dear.focus.device.ui.overview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentDeviceTypeBinding;
import com.guyuan.dear.focus.device.adapter.DeviceTypeAdapter;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 14:52
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceTypeFragment extends BaseListFragment<List<EquipmentBean>, FragmentDeviceTypeBinding,FocusDeviceViewModel> {

    public static final String TAG = "FocusDeviceOverviewFragment";
    public static final String ID = "id";
    public static final String TYPE = "type";

    public static final int TOTAL = 0x000;
    public static final int OPEN = 0x001;
    public static final int STOP = 0x002;
    public static final int MAINTAIN = 0x003;
    public static final int REPAIR = 0x004;
    public static final int EXCEPTION = 0x005;
    private int type;

    public static FocusDeviceTypeFragment newInstance(String title, long id, int type) {

        Bundle args = new Bundle();
        args.putLong(ID, id);
        args.putString(ConstantValue.KEY_TITLE, title);
        args.putInt(TYPE, type);
        FocusDeviceTypeFragment fragment = new FocusDeviceTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_device_type;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            String title = getArguments().getString(ConstantValue.KEY_TITLE);
            binding.deviceNameTv.setText(title);
            long id = getArguments().getLong(ID);
            if (id != 0) {
                viewModel.getOverviewTypeDevice(id);
            } else {
                viewModel.getOverviewTotalDevice();
            }
            type = getArguments().getInt(TYPE);
        }
        DeviceTypeAdapter deviceTypeAdapter = new DeviceTypeAdapter(getContext(), listData,
                R.layout.item_device_type);
        adapter = new BaseRecyclerViewAdapter(deviceTypeAdapter);
        //addFooter(adapter,LayoutInflater.from(getContext()).inflate(R.layout.footer_empty, null));
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
    }


    public void getTypeDeviceSuccess(List<EquipmentBean> deviceList) {
        binding.deviceTotalNumber.setText(deviceList.size() + " 台");
        List<List<EquipmentBean>> equipmentLists = getDeviceStatusList(deviceList);
        setListData(equipmentLists);
        int position = getPositionByType(type, equipmentLists);
        recycleView.scrollToPosition(position);
    }


    private List<List<EquipmentBean>> getDeviceStatusList(List<EquipmentBean> deviceList) {
        List<List<EquipmentBean>> typeList = new ArrayList<>();

        List<EquipmentBean> openList = new ArrayList<>();
        List<EquipmentBean> stopList = new ArrayList<>();
        List<EquipmentBean> maintainList = new ArrayList<>();
        List<EquipmentBean> repairList = new ArrayList<>();
        List<EquipmentBean> exceptionList = new ArrayList<>();

        for (EquipmentBean equipmentBean : deviceList) {
            if (equipmentBean.getTEquipmentInfo() != null) {
                EquipmentBean.TEquipmentInfoBean infoBean = equipmentBean.getTEquipmentInfo();
                int status = infoBean.getStatus();
                switch (status) {
                    case 1:
                        openList.add(equipmentBean);
                        break;

                    case 2:
                        stopList.add(equipmentBean);
                        break;

                    case 3:
                        maintainList.add(equipmentBean);
                        break;

                    case 4:
                        repairList.add(equipmentBean);
                        break;

                    case 5:
                        exceptionList.add(equipmentBean);
                        break;
                }

            }
        }

        if (openList.size() > 0) {
            typeList.add(openList);
        }

        if (stopList.size() > 0) {
            typeList.add(stopList);
        }

        if (maintainList.size() > 0) {
            typeList.add(maintainList);
        }

        if (repairList.size() > 0) {
            typeList.add(repairList);
        }

        if (exceptionList.size() > 0) {
            typeList.add(exceptionList);
        }
        return typeList;
    }


    private int getPositionByType(int type, List<List<EquipmentBean>> equipmentLists) {
        int position = TOTAL;
        for (int i = 0; i < equipmentLists.size(); i++) {
            EquipmentBean equipmentBean = equipmentLists.get(i).get(0);
            EquipmentBean.TEquipmentInfoBean infoBean = equipmentBean.getTEquipmentInfo();
            if (infoBean != null && type == infoBean.getStatus()) {
                position = i;
                break;
            }
        }

        return position + 1;
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
