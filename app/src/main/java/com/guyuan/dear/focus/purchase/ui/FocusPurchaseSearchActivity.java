package com.guyuan.dear.focus.purchase.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithoutToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 15:13
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusPurchaseSearchActivity extends BaseToolbarActivity<ActivityWithoutToolbarBinding, FocusPurchaseViewModel> {

    private FocusPurchaseListFragment listFragment;

    public static void start(Context context, String title, int statusType, String name) {
        Intent starter = new Intent(context, FocusPurchaseSearchActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(FocusPurchaseListFragment.STATUS_TYPE, statusType);
        starter.putExtra(FocusPurchaseListFragment.NAME, name);
        context.startActivity(starter);
    }

    public static void start(Context context, String title, String mouthDate, String name, int returnType, int productType) {
        Intent starter = new Intent(context, FocusPurchaseSearchActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(FocusPurchaseListFragment.MOUTH_DATE, mouthDate);
        starter.putExtra(FocusPurchaseListFragment.NAME, name);
        starter.putExtra(FocusPurchaseListFragment.RETURN_TYPE, returnType);
        starter.putExtra(FocusPurchaseListFragment.PRODUCT_TYPE, productType);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int statusType = getIntent().getIntExtra(FocusPurchaseListFragment.STATUS_TYPE, -1);
        String name = getIntent().getStringExtra(FocusPurchaseListFragment.NAME);
        String mouthDate = getIntent().getStringExtra(FocusPurchaseListFragment.MOUTH_DATE);
        int returnType = getIntent().getIntExtra(FocusPurchaseListFragment.RETURN_TYPE, -1);
        int productType = getIntent().getIntExtra(FocusPurchaseListFragment.PRODUCT_TYPE, -1);
        setTitleCenter(title);

        if (TextUtils.isEmpty(mouthDate)) {//正常或异常列表
            listFragment = FocusPurchaseListFragment.newInstance(statusType, name);
        } else {//退换货列表
            listFragment = FocusPurchaseListFragment.newInstance(mouthDate, name, returnType, productType);
        }

        ActivityUtils.addFragmentToActivity(fragmentManager, listFragment, R.id.fragment_container, FocusPurchaseListFragment.TAG);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}