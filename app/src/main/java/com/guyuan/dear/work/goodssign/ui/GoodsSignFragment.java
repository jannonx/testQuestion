package com.guyuan.dear.work.goodssign.ui;

import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignFragment extends BaseListSearchFragment<Object, FragmentListSearchBinding, GoodsSignViewModel> {

    public static final String TAG = "GoodsSignFragment";

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

    @Override
    protected int getVariableId() {
        return 0;
    }
}