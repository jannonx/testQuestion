package com.guyuan.dear.work.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.client.activity.WorkClientActivity;
import com.guyuan.dear.work.client.activity.WorkClientDetailActivity;
import com.guyuan.dear.work.client.adapter.ClientFollowAdapter;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientFollowFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding,WorkClientViewModel> {

    public static final String TAG = ClientFollowFragment.class.getSimpleName();
    private WorkClientViewModel viewModel;

    public static ClientFollowFragment newInstance() {
        Bundle args = new Bundle();
        ClientFollowFragment fragment = new ClientFollowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        ClientFollowAdapter listAdapter = new ClientFollowAdapter(getContext(), listData,
                R.layout.item_work_follow);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ApplyBean bean = listData.get(position);
//                ApplyDetailPageActivity.start(getContext(), bean);
            }
        });


    }

    @Override
    protected void onSearch(String text) {
        WorkClientDetailActivity.start(getContext(), "详情");
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            WorkClientActivity activity = (WorkClientActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
