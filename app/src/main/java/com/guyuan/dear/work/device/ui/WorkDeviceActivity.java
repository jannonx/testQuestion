package com.guyuan.dear.work.device.ui;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.scan.ScanActivity;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/30 10:43
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class WorkDeviceActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkDeviceViewModel> {

    public static final String TAG = "ControlDeviceActivity";
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final int ALL = 0x0001;
    public static final int PART = 0x0002;

    private ControlMaintainFragment controlMaintainFragment;
    private ControlFixFragment controlFixFragment;
    private ControlMaintainListFragment controlMaintainListFragment;
    private ControlFixListFragment controlFixListFragment;
    private String QRcode;
    private int type;
    protected String title;

    @Override
    protected List<String> getTitles() {
        type = getIntent().getIntExtra(TYPE, 0);
        if (type == ALL) {
            String[] titles = getResources().getStringArray(R.array.control_device);
            return new ArrayList<>(Arrays.asList(titles));
        } else {
            binding.baseTl.setTabMode(TabLayout.MODE_FIXED);
            String[] titles = {"保养记录", "维修执行"};
            return new ArrayList<>(Arrays.asList(titles));
        }
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        if (type == ALL) {
            controlMaintainFragment = ControlMaintainFragment.newInstance();
            controlFixFragment = ControlFixFragment.newInstance();
            controlMaintainListFragment = ControlMaintainListFragment.newInstance();
            controlFixListFragment = ControlFixListFragment.newInstance();
            fragmentList.add(controlMaintainFragment);
            fragmentList.add(controlFixFragment);
            fragmentList.add(controlMaintainListFragment);
            fragmentList.add(controlFixListFragment);
            setFirstPhotoListener(controlMaintainFragment);
            setSecondPhotoListener(controlFixFragment);
        } else if (type == PART) {
            controlMaintainListFragment = ControlMaintainListFragment.newInstance();
            controlFixListFragment = ControlFixListFragment.newInstance();
            fragmentList.add(controlMaintainListFragment);
            fragmentList.add(controlFixListFragment);
        }
        return fragmentList;
    }

    public void checkToItem(int position) {
        binding.baseVp.setCurrentItem(position);
    }


    @Override
    protected void init() {
        title = getIntent().getStringExtra(TITLE);
        setTitleCenter(title);
        binding.baseVp.setOffscreenPageLimit(3);
        String qrCode = getIntent().getStringExtra(ScanActivity.QRCODE);
        if (qrCode != null) {
            viewModel.getQRInfo(qrCode);
        }
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }
}