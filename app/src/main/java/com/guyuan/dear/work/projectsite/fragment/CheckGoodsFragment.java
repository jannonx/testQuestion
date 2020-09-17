package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @description: 我的关注--采购--采购合同进度
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsFragment extends BaseListSearchFragment<Object, FragmentListBinding> {

    public static final String TAG = CheckGoodsFragment.class.getSimpleName();

    public static CheckGoodsFragment newInstance() {

        Bundle args = new Bundle();

        CheckGoodsFragment fragment = new CheckGoodsFragment();
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
