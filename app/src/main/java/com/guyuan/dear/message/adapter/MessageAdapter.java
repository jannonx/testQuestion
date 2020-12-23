package com.guyuan.dear.message.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.BR;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.databinding.ItemMessageBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 14:28
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageAdapter extends BaseDBRecycleAdapter<MessageBean, ItemMessageBinding> {
    public MessageAdapter(List<MessageBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, MessageBean item, int position) {
        holder.binding.setVariable(BR.messageBean, item);
    }
}