package com.guyuan.dear.focus.transport.adapter;

import com.guyuan.dear.BR;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFocusTransportBinding;
import com.guyuan.dear.focus.transport.data.bean.TransportListBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportAdapter extends BaseDBRecycleAdapter<TransportListBean.ContentBean, ItemFocusTransportBinding> {
    public TransportAdapter(List<TransportListBean.ContentBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, TransportListBean.ContentBean item, int position) {
        holder.binding.setVariable(BR.transportItemContent,item);
    }
}