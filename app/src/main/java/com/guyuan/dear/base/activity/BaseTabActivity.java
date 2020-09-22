package com.guyuan.dear.base.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.TabLayoutHelper;

import java.util.List;

/**
 * Created by TL
 * on 2019/11/27
 * 顶部导航栏activity基类
 */
public abstract class BaseTabActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFileUploadActivity<V, VM> implements TabLayoutHelper.TabLayoutListener {

    protected TabLayout base_tl;
    protected ViewPager2 base_vp;

    private int startPosition;//起始选中位置
    private List<Integer> tabIconList;     //tab图标集合
    private List<String> titleList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_base_tab;
    }

    private void initView() {
        base_tl = findViewById(R.id.base_tl);
        base_vp = findViewById(R.id.base_vp);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        titleList = getTitles();
        List<Fragment> fragmentList = getFragments();
        tabIconList = setTabIconList();
        init();
        new TabLayoutHelper(this, base_tl, base_vp, fragmentList)
                .setTab()
                .setStartPosition(startPosition)
                .setListener(this)
                .setCustomView();
    }


    @Override
    public void setCustomContent(View customView, int currentPosition) {
        ImageView iv = customView.findViewById(R.id.tab_common_iv);
        TextView tv = customView.findViewById(R.id.tab_common_tv);
        iv.setImageResource(tabIconList.get(currentPosition));
        tv.setText(titleList.get(currentPosition));
    }


    protected abstract List<String> getTitles();

    protected abstract List<Fragment> getFragments();


    protected abstract void init();

    protected abstract List<Integer> setTabIconList();


    protected void setStartPosition(int position) {
        startPosition = position;
    }


    //设置tabLayout左对齐
//  protected void setTabLayoutToLeft() {
//    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) base_tl.getLayoutParams();
//    ll.gravity = Gravity.START;
//    base_tl.setLayoutParams(ll);
//  }

}
