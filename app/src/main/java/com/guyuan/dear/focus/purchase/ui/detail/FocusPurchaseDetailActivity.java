package com.guyuan.dear.focus.purchase.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithoutToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 15:33
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusPurchaseDetailActivity extends BaseToolbarActivity<ActivityWithoutToolbarBinding, FocusPurchaseViewModel> {
    private FocusPurchaseDetailFragment fragment;

    public static void start(Context context, String title, int id, int status) {
        Intent starter = new Intent(context, FocusPurchaseDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, id);
        starter.putExtra(ConstantValue.KEY_STATUS_TYPE, status);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int status = getIntent().getIntExtra(ConstantValue.KEY_STATUS_TYPE, 0);
        setTitleCenter(title);
        fragment = FocusPurchaseDetailFragment.newInstance(id, status);
        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.fragment_container,
                FocusPurchaseDetailFragment.TAG);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}