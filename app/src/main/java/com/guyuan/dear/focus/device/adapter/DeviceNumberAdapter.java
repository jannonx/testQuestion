package com.guyuan.dear.focus.device.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;


import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.device.data.beans.DeviceNumberBean;
import com.guyuan.dear.focus.device.ui.overview.FocusDeviceTypeFragment;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class DeviceNumberAdapter extends BaseRecyclerAdapter<DeviceNumberBean.EquipmentsBean> {

    public DeviceNumberAdapter(Context context, @NonNull List<DeviceNumberBean.EquipmentsBean> listData,
                               int layoutID) {
        super(context, listData, layoutID);
    }

    private OnTypeClickListener typeClickListener;

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder,
                                  DeviceNumberBean.EquipmentsBean item, int position) {

        LinearLayout open_ll = holder.getView(R.id.open_ll);
        LinearLayout close_ll = holder.getView(R.id.close_ll);
        LinearLayout repair_ll = holder.getView(R.id.repair_ll);
        LinearLayout exception_ll = holder.getView(R.id.exception_ll);
        LinearLayout maintain_ll = holder.getView(R.id.maintain_ll);

        holder.setText(R.id.item_device_total_tv, item.getSum());
        holder.setText(R.id.item_device_name_tv, item.getType());
        holder.setText(R.id.item_device_open_tv, item.getOpenSum());
        holder.setText(R.id.item_device_close_tv, item.getCloseSum());
        holder.setText(R.id.item_device_repair_tv, item.getRepairSum());
        holder.setText(R.id.item_device_fault_tv, item.getFaultSum());
        holder.setText(R.id.item_device_maintain_tv, item.getMaintanceSum());

        open_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.OPEN);
                }
            }
        });

        close_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.STOP);
                }
            }
        });

        repair_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.REPAIR);
                }
            }
        });

        exception_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.EXCEPTION);
                }
            }
        });

        maintain_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.MAINTAIN);
                }
            }
        });
    }

    public void setTypeClickListener(OnTypeClickListener typeClickListener) {
        this.typeClickListener = typeClickListener;
    }

    public interface OnTypeClickListener {
        void onTypeClick(DeviceNumberBean.EquipmentsBean bean, int type);
    }

}
