package com.guyuan.dear.focus.transport.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemTransportDetailInventoryBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:18
 * @company : 固远（深圳）信息技术有限公司
 **/

public class InventoryAdapter extends BaseDBRecycleAdapter<Object, ItemTransportDetailInventoryBinding> {
    public InventoryAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}