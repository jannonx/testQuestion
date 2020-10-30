package com.guyuan.dear.focus.security.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 16:50
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusSecurityActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusSecurityViewModel> implements TabLayoutHelper.TabLayoutListener {

    private FocusSecurityNumberFragment numberFragment;
    private FocusSecurityExceptionFragment exceptionFragment;
    private FocusSecurityProfileFragment profileFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusSecurityActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_security);
        return new ArrayList<>(Arrays.asList(titles));
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        numberFragment = FocusSecurityNumberFragment.newInstance();
        exceptionFragment = FocusSecurityExceptionFragment.newInstance();
        profileFragment = FocusSecurityProfileFragment.newInstance();

        fragmentList.add(numberFragment);
        fragmentList.add(exceptionFragment);
        fragmentList.add(profileFragment);
        return fragmentList;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int type = getIntent().getIntExtra(ConstantValue.KEY_EXCEPTION, 0);
        if (type == ConstantValue.STATE_EXCEPTION) {
            setStartPosition(1);
        }
        binding.baseTl.setTabMode(TabLayout.MODE_FIXED);
        setTitleCenter(title);
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
