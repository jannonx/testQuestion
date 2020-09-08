package com.guyuan.dear.customizeview.fullScreenShowFile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * created by tl
 * created at 2020/6/17
 */
public class FullScreenFileAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FullScreenFileAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, int behavior) {
        super(fm,behavior);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
