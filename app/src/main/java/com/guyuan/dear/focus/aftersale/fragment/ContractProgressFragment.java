package com.guyuan.dear.focus.aftersale.fragment;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ContractProgressFragment extends BaseListSearchFragment<Object, FragmentListBinding> {

    public static final String TAG = ContractProgressFragment.class.getSimpleName();

    public static ContractProgressFragment newInstance() {

        Bundle args = new Bundle();

        ContractProgressFragment fragment = new ContractProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {

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
