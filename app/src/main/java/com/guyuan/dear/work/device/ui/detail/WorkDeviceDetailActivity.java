package com.guyuan.dear.work.device.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by tl
 * created at 2020/7/13
 */
public class WorkDeviceDetailActivity extends BaseFileUploadActivity<ActivityWithToolbarBinding, WorkDeviceViewModel> {


    public static final String DEVICE_CONTENT_STATE = "DeviceContentState";
    private WorkDeviceDetailFragment workDeviceDetailFragment;

    public static void start(Context context, long deviceID, int state) {
        Intent starter = new Intent(context, WorkDeviceDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_ID, deviceID);
        starter.putExtra(DEVICE_CONTENT_STATE, state);
        context.startActivity(starter);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("维修详情");
        long deviceId = getIntent().getLongExtra(ConstantValue.KEY_ID, 0);
        int state = getIntent().getIntExtra(DEVICE_CONTENT_STATE, 0);
        workDeviceDetailFragment = WorkDeviceDetailFragment.newInstance(deviceId, state);
        ActivityUtils.addFragmentToActivity(fragmentManager, workDeviceDetailFragment,
                R.id.container, WorkDeviceDetailFragment.TAG);
        setFirstPhotoListener(workDeviceDetailFragment);
    }


    @Override
    protected void upLoadPicAndVideo(int currentType, Map fileMap) {
        super.upLoadPicAndVideo(currentType, fileMap);
        viewModel.upLoadPic(currentType, fileMap);
    }

    public void upLoadSuccess(int type, List<UploadBean> address) {
        List<String> addressList = new ArrayList<>();
        for (UploadBean bean : address) {
            addressList.add(bean.getUrl());
        }

        switch (type) {
            case BaseFileUploadActivity.FIRST:
                workDeviceDetailFragment.finishRepair(addressList);
                break;
        }
    }


    public void finishRepairSuccess(Integer i) {
        showToastTip("维修完成");
        finish();
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
