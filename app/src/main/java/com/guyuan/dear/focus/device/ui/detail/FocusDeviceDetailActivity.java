package com.guyuan.dear.focus.device.ui.detail;

import android.os.Bundle;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityFocusDeviceDetailBinding;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 18:00
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceDetailActivity extends BaseToolbarActivity<ActivityFocusDeviceDetailBinding, BaseViewModel> {

    public static final String CONTENT = "content";
    public static final String TYPE = "type";
    public static final int NORMAL = 0x0001;
    public static final int EXCEPTION = 0x0002;
    private EquipmentBean equipmentsBean;
    private DeviceExceptionBean.ContentBean contentBean;
    private String currentDvrSeriesNum;
    private String currentCameraChannel;
    private String url;

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void viewModuleCallBack(Object o) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_focus_device_detail;
    }
}
