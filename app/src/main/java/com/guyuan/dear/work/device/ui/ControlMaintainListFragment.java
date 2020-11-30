package com.guyuan.dear.work.device.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.device.adapter.MaintainListAdapter;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;
import com.guyuan.dear.work.device.data.bean.MaintainListBean;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * Created by TL
 * on 2019/12/21
 */
public class ControlMaintainListFragment extends BaseListFragment<MaintainListBean,
        FragmentListBinding, WorkDeviceViewModel> {

  private WorkDeviceActivity controlDeviceActivity;


  public static ControlMaintainListFragment newInstance() {

    Bundle args = new Bundle();

    ControlMaintainListFragment fragment = new ControlMaintainListFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    controlDeviceActivity = (WorkDeviceActivity) context;
  }

  @Override
  protected void initView() {
    MaintainListAdapter maintainListAdapter = new MaintainListAdapter(getContext(), listData,
        R.layout.item_maintian);
    adapter = new BaseRecyclerViewAdapter(maintainListAdapter);
    recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    recycleView.setAdapter(adapter);
    viewModel.getDeviceMaintainList();
  }


  public void updateData(){
    viewModel.getDeviceMaintainList();
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
