package com.guyuan.dear.focus.device.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;


import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemFocusDeviceNumberBinding;
import com.guyuan.dear.focus.device.data.beans.DeviceNumberBean;
import com.guyuan.dear.focus.device.ui.overview.FocusDeviceTypeFragment;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class DeviceNumberAdapter extends BaseDBRecycleAdapter<DeviceNumberBean.EquipmentsBean, ItemFocusDeviceNumberBinding> {

    private OnTypeClickListener typeClickListener;

    public DeviceNumberAdapter(List<DeviceNumberBean.EquipmentsBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    public void setTypeClickListener(OnTypeClickListener typeClickListener) {
        this.typeClickListener = typeClickListener;
    }

    @Override
    protected void bindDataToView(Holder holder, DeviceNumberBean.EquipmentsBean item, int position) {

        holder.binding.setVariable(BR.equipmentNumberBean, item);

        holder.binding.openLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.OPEN);
                }
            }
        });

        holder.binding.closeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.STOP);
                }
            }
        });

        holder.binding.repairLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.REPAIR);
                }
            }
        });

        holder.binding.exceptionLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.EXCEPTION);
                }
            }
        });

        holder.binding.maintainLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeClickListener != null) {
                    typeClickListener.onTypeClick(item, FocusDeviceTypeFragment.MAINTAIN);
                }
            }
        });
    }

    public interface OnTypeClickListener {
        void onTypeClick(DeviceNumberBean.EquipmentsBean bean, int type);
    }

}
