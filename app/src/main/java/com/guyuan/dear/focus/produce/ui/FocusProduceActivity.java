package com.guyuan.dear.focus.produce.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusProduceExceptionFragment;
import com.guyuan.dear.focus.produce.fragment.FocusProduceOverviewFragment;
import com.guyuan.dear.focus.produce.fragment.FocusProduceTotalFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--生产
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusProduceActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusProduceViewModel> {

    private FocusProduceOverviewFragment overviewFragment;
    private FocusProduceExceptionFragment exceptionFragment;
    private FocusProduceTotalFragment totalFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusProduceActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_produce_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        overviewFragment = FocusProduceOverviewFragment.newInstance();
        exceptionFragment = FocusProduceExceptionFragment.newInstance();
        totalFragment = FocusProduceTotalFragment.newInstance();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(overviewFragment);
        fragmentList.add(exceptionFragment);
        fragmentList.add(totalFragment);
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
        tabDrawableList.add(R.drawable.tab_focus_produce_overview_selector);
        tabDrawableList.add(R.drawable.tab_focus_produce_exception_selector);
        tabDrawableList.add(R.drawable.tab_focus_produce_detail_selector);
        return tabDrawableList;
    }

}
