package com.guyuan.dear.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.TabAdapter;

import java.util.List;

/**
 * Created by TL
 * on 2019/11/27
 * 顶部导航栏activity基类
 */
public abstract class BaseTabActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFileUploadActivity<V, VM> {

    protected TabLayout base_tl;
    protected ViewPager base_vp;

    private int startPosition;//起始选中位置
    private List<Integer> selectIconList;     //选中图标集合
    private List<Integer> unSelectedIconList; //未选中图标集合
    private List<String> titleList;
    private int selectedTextColor, unSelectedTextColor;

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
        selectIconList = setTabSelectedIconList();
        unSelectedIconList = setTabUnselectedIconList();
        TabAdapter tabAdapter = new TabAdapter(fragmentManager, titleList, fragmentList, this,
                getCustomViewId());
        base_vp.setAdapter(tabAdapter);
        int pageLimit = setOffScreenPageLimit();
        if (pageLimit > 0) {
            base_vp.setOffscreenPageLimit(pageLimit);
        }
        base_tl.setupWithViewPager(base_vp);
        base_tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        init();
        for (int i = 0; i < base_tl.getTabCount(); i++) {
            TabLayout.Tab tab = base_tl.getTabAt(i);
            View view = tabAdapter.getView();
            if (tab != null) {
                setContent(i, view);
                tab.setCustomView(view);
                if (i == startPosition) {
                    setTabSelect(tab);
                    tab.select();
                }
            }
        }
    }

    public ViewPager getViewPager() {
        return base_vp;
    }

    /**
     * 设置当前页面两边最大存活页面数量，默认-1，即不设置，这时ViewPager将采用其默认值1；
     *
     * @return
     */
    protected int setOffScreenPageLimit() {
        return -1;
    }

    private void setContent(int position, View customView) {
        ImageView iv = customView.findViewById(R.id.tab_common_iv);
        TextView tv = customView.findViewById(R.id.tab_common_tv);
        iv.setImageResource(unSelectedIconList.get(position));
        tv.setText(titleList.get(position));
    }


    protected abstract List<String> getTitles();

    protected abstract List<Fragment> getFragments();

    protected abstract int getCustomViewId();

    protected abstract void init();

    protected abstract List<Integer> setTabSelectedIconList();

    protected abstract List<Integer> setTabUnselectedIconList();

    protected void setSelectedtextColor(int color) {
        this.selectedTextColor = color;
    }

    protected void setUnSelectedTextColor(int color) {
        this.unSelectedTextColor = color;
    }

    protected void setStartPosition(int position) {
        startPosition = position;
    }


    protected void setTabSelect(TabLayout.Tab tab) {
        ImageView iv = tab.getCustomView().findViewById(R.id.tab_common_iv);
        TextView tv = tab.getCustomView().findViewById(R.id.tab_common_tv);
        iv.setImageResource(selectIconList.get(tab.getPosition()));
        if (selectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.white));
        } else {
            tv.setTextColor(getResources().getColor(selectedTextColor));
        }
    }


    protected void setTabUnselected(TabLayout.Tab tab) {
        ImageView iv = tab.getCustomView().findViewById(R.id.tab_common_iv);
        TextView tv = tab.getCustomView().findViewById(R.id.tab_common_tv);
        iv.setImageResource(unSelectedIconList.get(tab.getPosition()));
        if (unSelectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.white));
        } else {
            tv.setTextColor(getResources().getColor(unSelectedTextColor));
        }

    }


    //设置tabLayout左对齐
//  protected void setTabLayoutToLeft() {
//    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) base_tl.getLayoutParams();
//    ll.gravity = Gravity.START;
//    base_tl.setLayoutParams(ll);
//  }

    //当键盘弹出来的时候，点击屏幕空白处，将受收起键盘 by leo
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isToHide(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private boolean isToHide(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText || v instanceof AppCompatEditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
