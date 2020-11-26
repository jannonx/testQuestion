package com.guyuan.dear.office.clockIn.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.guyuan.dear.R;
import com.guyuan.dear.office.clockIn.adapter.ClockInPagerAdapter;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 打卡界面
 *
 * @author 廖华凯
 */
public class ClockInActivity extends BaseActionBarActivity {

    private ViewPager2 vp;
    private ClockInPagerAdapter adapter;
    private TabLayout tabLayout;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ClockInActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        //先判断是否有定位权限了。
        checkPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);

    }

    @Override
    public void onAllPermissionsGranted() {
        super.onAllPermissionsGranted();
        //用户批准了定位权限才能继续。
        intViewPager();
    }

    private void intViewPager() {
        vp = findViewById(R.id.activity_clock_in_vp);
        tabLayout = findViewById(R.id.activity_clock_in_tab_layout);
        adapter = new ClockInPagerAdapter(getSupportFragmentManager(), getLifecycle());
        vp.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, vp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        setupClockInTab(tab);
                        break;
                    case 1:
                        setupMyAttendSumTab(tab);
                        break;
                    default:
                        break;
                }

            }
        });
        mediator.attach();
        vp.setCurrentItem(0);
    }

    private void setupMyAttendSumTab(TabLayout.Tab tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_clock_in_tab, null);
        AppCompatImageView icon = view.findViewById(R.id.item_clock_in_tab_iv_icon);
        icon.setImageResource(R.drawable.selector_clock_in_tab_left);
        AppCompatTextView label = view.findViewById(R.id.item_clock_in_tab_tv_label);
        label.setText("打卡");
        tab.setCustomView(view);
    }

    private void setupClockInTab(TabLayout.Tab tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_clock_in_tab, null);
        AppCompatImageView icon = view.findViewById(R.id.item_clock_in_tab_iv_icon);
        icon.setImageResource(R.drawable.selector_clock_in_tab_right);
        AppCompatTextView label = view.findViewById(R.id.item_clock_in_tab_tv_label);
        label.setText("统计");
        tab.setCustomView(view);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_clock_in;
    }

}