package com.guyuan.dear.focus.client.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFollowStatusBinding;
import com.guyuan.dear.focus.client.adapter.FollowStatusExAdapter;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusFragment extends BaseDataBindingFragment<FragmentFollowStatusBinding> {

    public static final String TAG = "FollowStatusFragment";
    private FocusClientViewModel viewModel;
    private List<CommentsBean> dataList = new ArrayList<>();

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
        FollowStatusExAdapter adapter = new FollowStatusExAdapter(getContext(), dataList);
        binding.elvComments.setAdapter(adapter);

        binding.elvComments.setGroupIndicator(null);
        binding.elvComments.setChildIndicator(null);

        binding.elvComments.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
        int parentIndex = 3;
        int childIndex = 5;
        for (int i = 0; i < parentIndex; i++) {
            CommentsBean parentBean = new CommentsBean();
            parentBean.setContent("parentBean" + i);
            List<CommentsBean> childList = new ArrayList<>();
            for (int ij = 0; ij < childIndex; ij++) {
                CommentsBean childBean = new CommentsBean();
                parentBean.setContent("childBean" + i);
                childList.add(childBean);
            }
        }


        for (int i = 0; i < dataList.size(); i++) {
            binding.elvComments.expandGroup(i);
        }

        adapter.notifyDataSetChanged();
    }
}
