package com.guyuan.dear.office.clockIn.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail.StaffMonthlyDetailFragment;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.office.clockIn.repo.ClockInRepo;
import com.guyuan.dear.office.clockIn.view.ClockInFragment;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 17:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInPagerAdapter extends FragmentStateAdapter {

    private ClockInFragment clockInFragment;
    private StaffMonthlyDetailFragment calendarFragment;

    public ClockInPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            if (clockInFragment == null) {
                clockInFragment = ClockInFragment.getInstance();
            }
            return clockInFragment;
        } else if (position == 1) {
            if (calendarFragment == null) {
                LoginBean myInfo = new ClockInRepo().getMyInfo();
                calendarFragment = StaffMonthlyDetailFragment.getInstance((int) myInfo.getUserInfo().getId());
            }
            return calendarFragment;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public void updateCurrentAttendStatus() {
        if(calendarFragment!=null){
            calendarFragment.updateTodayAttendRecord();
        }
    }
}
