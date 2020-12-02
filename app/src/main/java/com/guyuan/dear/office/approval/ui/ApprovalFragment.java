package com.guyuan.dear.office.approval.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentPickStaffsBindingImpl;
import com.guyuan.dear.databinding.ItemApprovalBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.office.approval.adapter.ApprovalAdapter;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.office.approval.data.bean.ApprovalListBean;
import com.guyuan.dear.office.approval.ui.approvalDetail.ApprovalDetailActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.sun.jna.platform.win32.Variant;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:59
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalFragment extends BaseListSearchFragment<ApprovalListBean.ContentBean, ItemApprovalBinding, ApprovalViewModel> {

    public static final String TAG = "ApprovalFragment";
    public static final int APPROVAL_SEND = 1;//我发起的审批
    public static final int APPROVAL = 2;  //待我审批
    public static final int APPROVED = 3;  //我已审批

    private int type;

    public static ApprovalFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_TYPE, type);
        ApprovalFragment fragment = new ApprovalFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        ApprovalAdapter approvalAdapter = new ApprovalAdapter(listData, R.layout.item_approval);
        setDefaultAdapter(approvalAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                if (listData.size() > 0) {
                    ApprovalListBean.ContentBean bean = listData.get(i);
                    ApprovalDetailActivity.start(getContext(), bean.getBusinessName(), bean.getArType(), type, bean.getBusinessId());
                }
            }
        });

        if (getArguments() != null) {
            type = getArguments().getInt(ConstantValue.KEY_TYPE);
            if (viewModel != null) {
                viewModel.getApprovalList(currentPage, searchContent, type);
            }
        }
    }


    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        viewModel.getApprovalList(currentPage, searchContent, type);
    }

    @Override
    protected void loadMore() {
        viewModel.getApprovalList(++currentPage, searchContent, type);
    }

    @Override
    protected boolean isPullEnable() {
        return false;
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