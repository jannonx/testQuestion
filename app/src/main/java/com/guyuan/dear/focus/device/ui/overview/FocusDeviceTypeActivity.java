package com.guyuan.dear.focus.device.ui.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.R;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 14:46
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusDeviceTypeActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusDeviceViewModel> {

    private FocusDeviceTypeFragment overviewTypeListFragment;

    public static void start(Context context, String title, long id, int type) {
        Intent starter = new Intent(context, FocusDeviceTypeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(FocusDeviceTypeFragment.ID, id);
        starter.putExtra(FocusDeviceTypeFragment.TYPE, type);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        long id = getIntent().getLongExtra(FocusDeviceTypeFragment.ID, 0);
        int type = getIntent().getIntExtra(FocusDeviceTypeFragment.TYPE, FocusDeviceTypeFragment.TOTAL);
        setTitleCenter("设备状态");
        overviewTypeListFragment = FocusDeviceTypeFragment.newInstance(title, id, type);
        ActivityUtils.addFragmentToActivity(fragmentManager, overviewTypeListFragment, R.id.fragment_container,
                FocusDeviceTypeFragment.TAG);
        setObserver();
    }

    private void setObserver() {
        if (viewModel != null) {
            viewModel.getEquipmentListMLD().observe(this, new Observer<List<EquipmentBean>>() {
                @Override
                public void onChanged(List<EquipmentBean> equipmentBeans) {
                    overviewTypeListFragment.getTypeDeviceSuccess(equipmentBeans);
                }
            });
        }
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
