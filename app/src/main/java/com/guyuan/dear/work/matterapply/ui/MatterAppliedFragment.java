package com.guyuan.dear.work.matterapply.ui;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.ItemWorkMatterApplyBinding;
import com.guyuan.dear.work.matterapply.adapter.MatterApplyAdapter;
import com.guyuan.dear.work.matterapply.data.MatterApplyViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 19:24
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterAppliedFragment extends BaseListSearchFragment<Object, ItemWorkMatterApplyBinding, MatterApplyViewModel> {

    public static final String TAG = "MatterAppliedFragment";

    public static MatterAppliedFragment newInstance() {

        Bundle args = new Bundle();

        MatterAppliedFragment fragment = new MatterAppliedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        MatterApplyAdapter matterApplyAdapter = new MatterApplyAdapter(listData, R.layout.item_work_matter_apply);
        setDefaultAdapter(matterApplyAdapter);
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