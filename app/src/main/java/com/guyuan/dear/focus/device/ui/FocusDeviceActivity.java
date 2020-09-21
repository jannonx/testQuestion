package com.guyuan.dear.focus.device.ui;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:04
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusDeviceActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusDeviceViewModel> {



    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_device_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        return null;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    @Override
    protected List<Integer> setTabSelectedIconList() {
        List<Integer> selectList = new ArrayList<>();
        selectList.add(R.mipmap.device_maintain);
        selectList.add(R.mipmap.device_fix);
        selectList.add(R.mipmap.device_maintain);
        selectList.add(R.mipmap.device_fix);
        return selectList;
    }

    @Override
    protected List<Integer> setTabUnselectedIconList() {
        List<Integer> unselected = new ArrayList<>();
        unselected.add(R.mipmap.device_maintain);
        unselected.add(R.mipmap.device_fix);
        unselected.add(R.mipmap.device_maintain);
        unselected.add(R.mipmap.device_fix);
        return unselected;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
