package com.guyuan.dear.focus.aftersale.fragment;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProviderFragment extends BaseListSearchFragment<Object, FragmentListBinding> {

    public static final String TAG = ProviderFragment.class.getSimpleName();

    public static ProviderFragment newInstance() {

        Bundle args = new Bundle();

        ProviderFragment fragment = new ProviderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


}
