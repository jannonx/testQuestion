package com.guyuan.dear.focus.assess.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 18:02
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSellAssessFragment extends BaseListFragment<Object, FragmentListBinding> {

    public static final String TAG = "FocusSellAssessFragment";

    public static FocusSellAssessFragment newInstance() {
        Bundle args = new Bundle();
        FocusSellAssessFragment fragment = new FocusSellAssessFragment();
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
