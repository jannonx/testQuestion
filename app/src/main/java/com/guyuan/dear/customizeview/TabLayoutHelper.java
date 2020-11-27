package com.guyuan.dear.customizeview;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.TabAdapter;

import java.util.List;

/**
 * @author : tl
 * @description :辅助生成自定义view的TabLayout
 * @since: 2020/9/22 16:18
 * @company : 固远（深圳）信息技术有限公司
 **/
public class TabLayoutHelper {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private int tabSize;
    List<Fragment> fragmentList;
    private int layoutID;
    private FragmentActivity fa;
    private int startPosition;
    private TabLayoutListener listener;

    public static final int UNDERLINE = 10;  //下划线样式
    public static final int COMMON = 20;     //顶部tab样式

    public TabLayoutHelper(FragmentActivity fa, TabLayout tabLayout, ViewPager2 viewPager2,
                           List<Fragment> fragmentList, int layoutID) {
        this.fa = fa;
        this.tabLayout = tabLayout;
        this.viewPager2 = viewPager2;
        this.layoutID = layoutID;
        this.fragmentList = fragmentList;
    }

    public TabLayoutHelper(FragmentActivity fa, TabLayout tabLayout, int tabSize, int layoutID) {
        this.fa = fa;
        this.tabLayout = tabLayout;
        this.tabSize = tabSize;
        this.layoutID = layoutID;
    }

    public TabLayoutHelper setTab() {
        if (viewPager2 != null) {
            TabAdapter tabAdapter = new TabAdapter(fa, fragmentList);
            viewPager2.setAdapter(tabAdapter);
            new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    //由于是自定义界面这里不做处理
                }
            }).attach();
        } else {
            for (int i = 0; i < tabSize; i++) {
                tabLayout.addTab(tabLayout.newTab());
            }
        }

        return this;
    }


    public TabLayoutHelper addOnTabSelectedListener(TabLayout.OnTabSelectedListener listener) {
        tabLayout.addOnTabSelectedListener(listener);
        return this;
    }

    public TabLayoutHelper registerOnPageChangeCallback(ViewPager2.OnPageChangeCallback callback) {
        viewPager2.registerOnPageChangeCallback(callback);
        return this;
    }


    public TabLayoutHelper setTabMode(int tabMode) {
        tabLayout.setTabMode(tabMode);
        return this;
    }

    public void setCustomView() {

        //预设样式
        switch (layoutID) {
            case UNDERLINE:
                layoutID = R.layout.tab_blue_under_line;
                break;

            case COMMON:
                layoutID = R.layout.tab_common;//默认tab布局
                break;
        }

        //设置自定义布局
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View view = LayoutInflater.from(fa).inflate(layoutID, null);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                if (listener != null) {
                    listener.setCustomContent(view, i);
                }
                tab.setCustomView(view);
                if (i == startPosition) {//设置初始选中位置
                    tab.select();
                }
            }
        }
    }

    public TabLayoutHelper setListener(TabLayoutListener listener) {
        this.listener = listener;
        return this;
    }

    public TabLayoutHelper setStartPosition(int startPosition) {
        this.startPosition = startPosition;
        return this;
    }


    public interface TabLayoutListener {
        void setCustomContent(View customView, int currentPosition);
    }
}
