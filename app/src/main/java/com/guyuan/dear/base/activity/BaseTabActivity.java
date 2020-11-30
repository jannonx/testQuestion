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
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

/**
 * Created by TL
 * on 2019/11/27
 * 顶部导航栏activity基类
 */
public abstract class BaseTabActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFileUploadActivity<V, VM> implements TabLayoutHelper.TabLayoutListener {

    protected TabLayout tlBase;
    protected ViewPager2 vpBase;

    /**
     * 起始选中位置
     */
    private int startPosition;

    /**
     * tab图标集合
     */
    private List<Integer> tabIconList;
    /**
     * tab文本集合
     */
    private List<String> titleList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_base_tab;
    }

    private void initView() {
        tlBase = findViewById(R.id.base_tl);
        vpBase = findViewById(R.id.base_vp);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        titleList = getTitles();
        List<Fragment> fragmentList = getFragments();
        tabIconList = setTabIconList();
        init();
        new TabLayoutHelper(this, tlBase, vpBase, fragmentList, TabLayoutHelper.COMMON)
                .setTab()
                .setStartPosition(startPosition)
                .setListener(this)
                .setCustomView();
    }


    @Override
    public void setCustomContent(View customView, int currentPosition) {
        ImageView iv = customView.findViewById(R.id.tab_common_iv);
        TextView tv = customView.findViewById(R.id.tab_common_tv);
        LogUtils.showLog("setCustomContent=" + currentPosition);
        iv.setImageResource(tabIconList.get(currentPosition));
        tv.setText(titleList.get(currentPosition));
    }

    /**
     * 设置头部Tab文本集合
     *
     * @return 头部Tab文本集合
     */
    protected abstract List<String> getTitles();

    /**
     * 设置内容fragment集合
     *
     * @return 内容fragment集合
     */
    protected abstract List<Fragment> getFragments();

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * 设置头部Tab图标集合
     *
     * @return 头部Tab图标集合
     */
    protected abstract List<Integer> setTabIconList();


    /**
     * 设置起始位置
     *
     * @param position 位置下标
     */
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
