package com.guyuan.dear.focus.transport.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemTransportMessageBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:23
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportMessageAdapter extends BaseDBRecycleAdapter<Object, ItemTransportMessageBinding> {
    public TransportMessageAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}