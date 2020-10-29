package com.guyuan.dear.focus.client.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFollowStatusBinding;
import com.guyuan.dear.focus.client.adapter.FollowStatusExAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusFragment extends BaseDataBindingFragment<FragmentFollowStatusBinding, FocusClientViewModel> {

    public static final String TAG = "FollowStatusFragment";
    private FocusClientViewModel viewModel;
    private List<CommentsBean> dataList = new ArrayList<>();
    private boolean isFocus = false;

    public static FollowStatusFragment newInstance() {
        return newInstance(false);
    }

    public static FollowStatusFragment newInstance(boolean isFocus) {
        Bundle bundle = new Bundle();
        FollowStatusFragment fragment = new FollowStatusFragment();
        bundle.putBoolean(ConstantValue.KEY_CONTENT, isFocus);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_follow_status;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        isFocus = arguments.getBoolean(ConstantValue.KEY_CONTENT);

        int parentIndex = 3;
        int childIndex = 5;
        for (int i = 0; i < parentIndex; i++) {
            CommentsBean parentBean = new CommentsBean();
            parentBean.setContent("parentBean" + i);
            List<CommentsBean> childList = new ArrayList<>();
            for (int ij = 0; ij < childIndex; ij++) {
                CommentsBean childBean = new CommentsBean();
                childBean.setContent("childBean" + ij);
                childList.add(childBean);
            }
            parentBean.setBusinessDetails(childList);
            dataList.add(parentBean);
        }

        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_all, null);
        FollowStatusExAdapter adapter = new FollowStatusExAdapter(getContext(), dataList);
        adapter.setCommentBtnVisible(isFocus);

        binding.elvComments.setAdapter(adapter);

        binding.elvComments.setGroupIndicator(null);
        binding.elvComments.setChildIndicator(null);
        binding.elvComments.addFooterView(footerView);
        binding.elvComments.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });

        for (int i = 0; i < dataList.size(); i++) {
            binding.elvComments.expandGroup(i);
        }
        adapter.notifyDataSetChanged();


        adapter.setChildItemClickListener(new FollowStatusExAdapter.ChildItemClickListener() {

            @Override
            public void onCommentClicked(CommentsBean bean) {
                viewModel.postCommentFollowUp(bean.getId(), "哈哈哈");
            }


        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
