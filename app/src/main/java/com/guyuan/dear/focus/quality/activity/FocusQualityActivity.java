package com.guyuan.dear.focus.quality.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;

import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.focus.quality.fragment.KeyComponentFragment;
import com.guyuan.dear.focus.quality.fragment.MaterialListFragment;
import com.guyuan.dear.focus.quality.fragment.OutSourceFragment;
import com.guyuan.dear.focus.quality.fragment.QualityExceptFragment;
import com.guyuan.dear.focus.quality.fragment.QualityOverViewFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--质检
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusQualityActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusPurchaseViewModel> {

    private QualityOverViewFragment qualityOverViewFragment;
    private KeyComponentFragment keyComponentFragment;
    private OutSourceFragment outSourceFragment;
    private MaterialListFragment materialListFragment;
    private QualityExceptFragment qualityExceptFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusQualityActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_quality_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        qualityOverViewFragment = QualityOverViewFragment.newInstance();
        keyComponentFragment = KeyComponentFragment.newInstance();
        outSourceFragment = OutSourceFragment.newInstance();
        materialListFragment = MaterialListFragment.newInstance();
        qualityExceptFragment = QualityExceptFragment.newInstance();
        fragmentList.add(qualityOverViewFragment);
        fragmentList.add(keyComponentFragment);
        fragmentList.add(outSourceFragment);
        fragmentList.add(materialListFragment);
        fragmentList.add(qualityExceptFragment);
        return fragmentList;
    }

    @Override
    protected int getCustomViewId() {
        return R.layout.tab_common;
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
        unselected.add(R.mipmap.device_fix);
        return unselected;
    }

    @Override
    public FocusPurchaseViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
