package com.guyuan.dear.office.approval.ui;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.ItemApprovalBinding;
import com.guyuan.dear.office.approval.adapter.ApprovalAdapter;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:59
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalFragment extends BaseListSearchFragment<Object, ItemApprovalBinding, ApprovalViewModel> {

    public static final String TAG = "ApprovalFragment";
    public static final int APPROVAL = 1;  //待审批
    public static final int APPROVED = 2;  //已审批

    public static ApprovalFragment newInstance(int type) {

        Bundle args = new Bundle();

        ApprovalFragment fragment = new ApprovalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        ApprovalAdapter approvalAdapter = new ApprovalAdapter(listData, R.layout.item_approval);
        setDefaultAdapter(approvalAdapter);
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