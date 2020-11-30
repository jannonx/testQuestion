package com.guyuan.dear.work.aftersale.activity;

import android.content.Context;
import android.content.Intent;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.aftersale.fragment.WorkAfterSaleFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--售后
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkAfterSaleActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusAfterSaleViewModel> {

    private WorkAfterSaleFragment checkFragment;
    private WorkAfterSaleFragment acceptFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkAfterSaleActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.work_after_sale_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        checkFragment = WorkAfterSaleFragment.newInstance(SaleSectionType.TYPE_SECTION_CHECK);
        acceptFragment = WorkAfterSaleFragment.newInstance(SaleSectionType.TYPE_SECTION_ACCEPT);
        fragmentList.add(checkFragment);
        fragmentList.add(acceptFragment);
        return fragmentList;
    }


    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        viewModel.getAfterSaleListEvent().observe(this, new Observer<RefreshBean<AfterSaleBean>>() {
            @Override
            public void onChanged(RefreshBean<AfterSaleBean> data) {
                if (checkFragment.isVisible()){
                    checkFragment.dealDataByAddType(data.getContent(),SaleSectionType.TYPE_SECTION_CHECK);
                }else {
                    acceptFragment.dealDataByAddType(data.getContent(),SaleSectionType.TYPE_SECTION_ACCEPT);
                }

            }
        });


    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_focus_after_sale_right_selector);
        tabDrawableList.add(R.drawable.tab_focus_after_sale_wrong_selector);
        return tabDrawableList;
    }
}
