package com.guyuan.dear.focus.transport.adapter;

import com.guyuan.dear.BR;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemTransportDetailInventoryBinding;
import com.guyuan.dear.focus.transport.data.bean.TransportDetailBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:18
 * @company : 固远（深圳）信息技术有限公司
 **/

public class InventoryAdapter extends BaseDBRecycleAdapter<TransportDetailBean.TransportDetailVOListBean, ItemTransportDetailInventoryBinding> {
    public InventoryAdapter(List<TransportDetailBean.TransportDetailVOListBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, TransportDetailBean.TransportDetailVOListBean item, int position) {
        holder.binding.setVariable(BR.transportDetailBean, item);
    }
}