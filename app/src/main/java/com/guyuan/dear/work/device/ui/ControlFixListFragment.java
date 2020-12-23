package com.guyuan.dear.work.device.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.device.adapter.Fixadapter;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;
import com.guyuan.dear.work.device.data.bean.ControlRepairBean;
import com.guyuan.dear.work.device.ui.detail.WorkDeviceDetailActivity;
import com.guyuan.dear.work.device.ui.detail.WorkDeviceDetailFragment;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

public class ControlFixListFragment extends BaseListFragment<ControlRepairBean.RepairEntity, FragmentListBinding, WorkDeviceViewModel> {

    public static final String TAG = "ControlFixListFragment";
    private WorkDeviceActivity controlDeviceActivity;
    private Long currentID;

    public static ControlFixListFragment newInstance() {

        Bundle args = new Bundle();

        ControlFixListFragment fragment = new ControlFixListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controlDeviceActivity = (WorkDeviceActivity) context;
    }


    @Override
    public int getLayoutID() {
        return R.layout.fragment_control_fix;
    }

    @Override
    protected void initView() {
        Fixadapter fixAdapter = new Fixadapter(getContext(), listData, R.layout.item_control_fix);
        fixAdapter.setListener(new Fixadapter.FixadapterListener() {
            @Override
            public void onClick(int type, Long msgID) {
                currentID = msgID;
                if (type == 0) {
                    viewModel.startRepair(msgID);
                } else if (type == 1) {
                    WorkDeviceDetailActivity.start(getContext(), currentID, WorkDeviceDetailFragment.REPAIR);
                }
            }

            @Override
            public void onRepaired(long deviceID) {
                WorkDeviceDetailActivity.start(getContext(), deviceID, WorkDeviceDetailFragment.OTHER);
            }
        });
        adapter = new BaseRecyclerViewAdapter(fixAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

    }

    public void updateData() {
        viewModel.getRepairList(currentPage);
    }

    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
    }

    @Override
    protected void loadMore() {
        viewModel.getRepairList(++currentPage);
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
