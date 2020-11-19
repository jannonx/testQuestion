package com.guyuan.dear.focus.transport.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFocusTransportBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportAdapter extends BaseDBRecycleAdapter<Object, ItemFocusTransportBinding> {
    public TransportAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}