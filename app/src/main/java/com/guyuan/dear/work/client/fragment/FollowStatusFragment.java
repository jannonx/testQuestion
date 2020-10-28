package com.guyuan.dear.work.client.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.databinding.FragmentFollowStatusBinding;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.work.client.adapter.ClientAllAdapter;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusFragment extends BaseDataBindingFragment<FragmentFollowStatusBinding> {

    public static final String TAG = "FollowStatusFragment";
    private FocusClientViewModel viewModel;

    public static FollowStatusFragment newInstance() {
        Bundle args = new Bundle();
        FollowStatusFragment fragment = new FollowStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_follow_status;
    }

    @Override
    protected void initialization() {
        List<SimpleTabBean> listData=new ArrayList<>();
        ClientAllAdapter listAdapter = new ClientAllAdapter(getContext(), listData,
                R.layout.item_work_all_customer);
//        adapter = new BaseRecyclerViewAdapter(listAdapter);
//        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycleView.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//            }
//        });
    }
}
