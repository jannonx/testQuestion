package com.guyuan.dear.work.matterapply.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.ItemWorkMatterApplyBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.matterapply.adapter.MatterApplyAdapter;
import com.guyuan.dear.work.matterapply.data.MatterApplyViewModel;
import com.guyuan.dear.work.matterapply.data.bean.MatterApplyBean;
import com.guyuan.dear.work.matterapply.ui.detail.MatterApplyDetailActivity;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 19:24
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterAppliedFragment extends BaseListFragment<MatterApplyBean, ItemWorkMatterApplyBinding, MatterApplyViewModel> {

    public static final String TAG = "MatterAppliedFragment";

    public static MatterAppliedFragment newInstance() {

        Bundle args = new Bundle();

        MatterAppliedFragment fragment = new MatterAppliedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        setContainerBackground(R.color.bg_window);
        MatterApplyAdapter matterApplyAdapter = new MatterApplyAdapter(listData, R.layout.item_work_matter_apply);
        setDefaultAdapter(matterApplyAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                MatterApplyBean bean = listData.get(i);
                MatterApplyDetailActivity.start(getContext(), bean.getProductName(), bean.getId());
            }
        });
        if (viewModel != null) {
            viewModel.getMatterApplyList(currentPage, "", "", "");
        }
    }

    @Override
    protected void refresh() {
        currentPage = ConstantValue.FIRST_PAGE;
        viewModel.getMatterApplyList(currentPage, "", "", "");
    }

    @Override
    protected void loadMore() {
        viewModel.getMatterApplyList(++currentPage, "", "", "");
    }

    @Override
    protected boolean isPullEnable() {
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}