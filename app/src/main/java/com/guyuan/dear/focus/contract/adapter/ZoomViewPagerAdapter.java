package com.guyuan.dear.focus.contract.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.guyuan.dear.focus.contract.bean.ImageSource;
import com.guyuan.dear.focus.contract.view.zoomView.ZoomViewPageFragment;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/19 14:49
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ZoomViewPagerAdapter extends FragmentStateAdapter {

    private List<ImageSource> list;

    public ZoomViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,List<ImageSource> list) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ZoomViewPageFragment.getInstance(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
