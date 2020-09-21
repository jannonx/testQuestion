package com.guyuan.dear.focus.device.ui;

import android.os.Bundle;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 17:54
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceExceptionFragment extends BaseListFragment<DeviceExceptionBean.ContentBean, FragmentListBinding> {


    public static final String TAG = "FocusDeviceExceptionFragment";

    public static FocusDeviceExceptionFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FocusDeviceExceptionFragment fragment = new FocusDeviceExceptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

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
