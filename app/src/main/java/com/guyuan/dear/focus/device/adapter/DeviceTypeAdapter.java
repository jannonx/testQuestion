package com.guyuan.dear.focus.device.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.ui.detail.FocusDeviceDetailActivity;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * created by tl
 * created at 2020/6/4
 */
public class DeviceTypeAdapter extends BaseRecyclerAdapter<List<EquipmentBean>> {

  public DeviceTypeAdapter(Context context, @NonNull List<List<EquipmentBean>> listData, int layoutID) {
    super(context, listData, layoutID);
  }

  @Override
  protected void bindDataToView(BaseRecyclerViewHolder holder, List<EquipmentBean> item, int position) {
    int status = item.get(0).getTEquipmentInfo().getStatus();
    holder.setText(R.id.device_type_number_tv, item.size() + " 台");
    BaseRecyclerView recyclerView = holder.getView(R.id.device_type_rv);
    switch (status) {
      case 1://开机
        holder.setText(R.id.device_title_tv, "开机设备");
        holder.setImageResource(R.id.device_type_iv, R.mipmap.device_open);
        break;

      case 2://停机
        holder.setText(R.id.device_title_tv, "停机设备");
        holder.setImageResource(R.id.device_type_iv, R.mipmap.device_close);
        break;

      case 3://保养
        holder.setText(R.id.device_title_tv, "保养设备");
        holder.setImageResource(R.id.device_type_iv, R.mipmap.device_maintain1);
        break;

      case 4://维修
        holder.setText(R.id.device_title_tv, "维修设备");
        holder.setImageResource(R.id.device_type_iv, R.mipmap.device_repair);
        break;

      case 5://故障
        holder.setText(R.id.device_title_tv, "故障设备");
        holder.setImageResource(R.id.device_type_iv, R.mipmap.device_exception);
        break;
    }

    DeviceItemAdapter farmDetailAdapter =
        new DeviceItemAdapter(context, item, R.layout.item_focus_content);
    farmDetailAdapter.setListener(new DeviceItemAdapter.FarmDetailListener() {
      @Override
      public void onClick(EquipmentBean bean) {
        Intent intent = new Intent(context, FocusDeviceDetailActivity.class);
        intent.putExtra(FocusDeviceDetailActivity.TYPE, FocusDeviceDetailActivity.NORMAL);
        intent.putExtra(FocusDeviceDetailActivity.CONTENT, bean);
        context.startActivity(intent);
      }
    });

    BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(farmDetailAdapter);
    recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
    recyclerView.setAdapter(adapter);
    recyclerView.setPullRefreshEnabled(false);
    recyclerView.setLoadMoreEnabled(false);
  }
}
