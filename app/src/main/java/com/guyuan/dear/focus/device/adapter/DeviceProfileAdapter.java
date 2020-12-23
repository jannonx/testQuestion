package com.guyuan.dear.focus.device.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * created by tl
 * created at 2020/4/21
 */
public class DeviceProfileAdapter extends BaseRecyclerAdapter<FactoryRealTimeBean.WorkshopsBean> {

  private FarmNewContentListener lineListener;

  public DeviceProfileAdapter(Context context, @NonNull
          List<FactoryRealTimeBean.WorkshopsBean> listData, int layoutID) {
    super(context, listData, layoutID);
  }

  @Override protected void bindDataToView(BaseRecyclerViewHolder holder,
                                          FactoryRealTimeBean.WorkshopsBean item, int position) {
    holder.setText(R.id.line_name_tv, item.getName());
    BaseRecyclerView line_rv = holder.getView(R.id.line_rv);
    DeviceItemAdapter deviceItemAdapter =
        new DeviceItemAdapter(context, item.getEquipments(), R.layout.item_focus_content);
    deviceItemAdapter.setListener(new DeviceItemAdapter.FarmDetailListener() {
      @Override public void onClick(EquipmentBean bean) {
        if (lineListener != null) {
          lineListener.showDeviceDetail(bean);
        }
      }
    });

    BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(deviceItemAdapter);

    line_rv.setLayoutManager(new GridLayoutManager(context, 3));
    line_rv.setAdapter(adapter);
    line_rv.setLoadMoreEnabled(false);
    line_rv.setPullRefreshEnabled(false);
  }

  public void setLineListener(FarmNewContentListener lineListener) {
    this.lineListener = lineListener;
  }

  public interface FarmNewContentListener {
    void showDeviceDetail(EquipmentBean bean);
  }
}
