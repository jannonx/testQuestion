package com.guyuan.dear.focus.aftersale.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.aftersale.bean.SaleQualifiedType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.aftersale.fragemnt.FocusAfterSaleFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--售后
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusAfterSaleActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusAfterSaleViewModel> {

    private FocusAfterSaleFragment qualifiedFragment;
    private FocusAfterSaleFragment unqualifiedFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusAfterSaleActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_after_sale_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        qualifiedFragment = FocusAfterSaleFragment.newInstance(SaleQualifiedType.TYPE_QUALIFIED);
        unqualifiedFragment = FocusAfterSaleFragment.newInstance(SaleQualifiedType.TYPE_UNQUALIFIED);
        fragmentList.add(qualifiedFragment);
        fragmentList.add(unqualifiedFragment);
        return fragmentList;
    }


    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_work_after_sale_check_selector);
        tabDrawableList.add(R.drawable.tab_work_after_sale_customer_selector);
        return tabDrawableList;
    }
}
