package com.guyuan.dear.focus.purchase.fragment;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @description: 我的关注--工程现场--原材料列表
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class MaterialListFragment extends BaseListFragment<Object, FragmentListBinding> {

    public static final String TAG = MaterialListFragment.class.getSimpleName();

    public static MaterialListFragment newInstance() {

        Bundle args = new Bundle();

        MaterialListFragment fragment = new MaterialListFragment();
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
