package com.guyuan.dear.focus.hr.adapter;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.guyuan.dear.focus.hr.view.hrStaffAttendDetail.AttendListFragment;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/27 16:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendDetailPagerAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"正常", "迟到", "早退", "缺席"};

    public StaffAttendDetailPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    private SparseArray<BaseFragment> cache=new SparseArray<>();

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                if(cache.get(1)==null){
                    AttendListFragment fragment = AttendListFragment.getLateListInstance();
                    cache.put(1,fragment);
                }
                return cache.get(1);
            case 2:
                if(cache.get(2)==null){
                    AttendListFragment fragment = AttendListFragment.getLeaveEarlyInstance();
                    cache.put(2,fragment);
                }
                return cache.get(2);
            case 3:
                if(cache.get(3)==null){
                    AttendListFragment fragment = AttendListFragment.getAbsentInstance();
                    cache.put(3,fragment);
                }
                return cache.get(3);
            case 0:
            default:
                if(cache.get(0)==null){
                    AttendListFragment fragment = AttendListFragment.getNormalListInstance();
                    cache.put(0,fragment);
                }
                return cache.get(0);
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public String getTitle(int pos) {
        return titles[pos];
    }

}
