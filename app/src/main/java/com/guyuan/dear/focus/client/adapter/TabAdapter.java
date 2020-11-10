package com.guyuan.dear.focus.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @description: 我的关注-客户详情--Tab列表适配器
 * @author: Jannonx
 * @since: 2020/10/27 10:58
 * @company: 固远（深圳）信息技术有限公司
 */
public class TabAdapter extends FragmentPagerAdapter {
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private Context context;
    private int layoutID;

    public TabAdapter(@NonNull FragmentManager fm, List<String> titleList,
                      List<Fragment> fragmentList, Context context, int layoutID) {
        super(fm);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
        this.context = context;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    public View getView() {
        return LayoutInflater.from(context).inflate(layoutID, null);
    }
}
