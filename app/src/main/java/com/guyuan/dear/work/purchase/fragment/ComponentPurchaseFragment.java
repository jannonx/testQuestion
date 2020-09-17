package com.guyuan.dear.work.purchase.fragment;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @description: 我的工作--采购计划--采购关键零部件
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ComponentPurchaseFragment extends BaseListSearchFragment<Object, FragmentListBinding> {

    public static final String TAG = ComponentPurchaseFragment.class.getSimpleName();

    public static ComponentPurchaseFragment newInstance() {

        Bundle args = new Bundle();

        ComponentPurchaseFragment fragment = new ComponentPurchaseFragment();
        fragment.setArguments(args);
        return fragment;
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
