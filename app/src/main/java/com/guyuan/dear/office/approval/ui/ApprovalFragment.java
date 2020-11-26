package com.guyuan.dear.office.approval.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.ItemApprovalBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.office.approval.adapter.ApprovalAdapter;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.office.approval.data.bean.ApprovalListBean;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:59
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalFragment extends BaseListFragment<ApprovalListBean.ContentBean, ItemApprovalBinding, ApprovalViewModel> {

    public static final String TAG = "ApprovalFragment";
    public static final int APPROVAL = 1;  //待审批
    public static final int APPROVED = 2;  //已审批

    private int type;

    public static ApprovalFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_TYPE, type);
        ApprovalFragment fragment = new ApprovalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        setContainerBackground(R.color.bg_window);
        ApprovalAdapter approvalAdapter = new ApprovalAdapter(listData, R.layout.item_approval);
        setDefaultAdapter(approvalAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                if (listData.size() > 0) {
                    ApprovalListBean.ContentBean bean = listData.get(i);
                    FocusProduceBean produceBean=new FocusProduceBean();
                    produceBean.setEquipmentId(bean.getEquipmentId());
                    produceBean.setStatusType(bean.getStatus());
                    FocusProduceDetailActivity.start(getContext(),false,produceBean,
                            bean.getBusinessId(),bean.getBusinessType(),type);
                }
            }
        });
        if (getArguments() != null) {
            type = getArguments().getInt(ConstantValue.KEY_TYPE);
            if (viewModel != null) {
                viewModel.getApprovalList(currentPage, type);
            }
        }
    }

    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        viewModel.getApprovalList(currentPage, type);
    }

    @Override
    protected void loadMore() {
        viewModel.getApprovalList(++currentPage, type);
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