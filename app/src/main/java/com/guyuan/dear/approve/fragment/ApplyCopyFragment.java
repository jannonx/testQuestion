package com.guyuan.dear.approve.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.activity.ApplyCopyActivity;
import com.guyuan.dear.approve.activity.MineApplyListActivity;
import com.guyuan.dear.approve.adapter.ApprovedListAdapter;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 掌上办公--审批--抄送我的
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyCopyFragment extends BaseListFragment<ApplyBean, FragmentListBinding,ApproveViewModel> {

    public static final String TAG = ApplyCopyFragment.class.getSimpleName();

    public static ApplyCopyFragment newInstance() {
        Bundle args = new Bundle();
        ApplyCopyFragment fragment = new ApplyCopyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        ApprovedListAdapter listAdapter = new ApprovedListAdapter(getContext(), listData,
                R.layout.item_examine_and_approve);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ApplyBean bean = listData.get(position);
//                ApplyDetailPageActivity.start(getContext(), bean);
            }
        });
//        mPresenter.getApplyCopyList();

    }

    public void showApplyCopyList(RefreshBean<ApplyBean> result) {
        setListData(result.getContent());
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
