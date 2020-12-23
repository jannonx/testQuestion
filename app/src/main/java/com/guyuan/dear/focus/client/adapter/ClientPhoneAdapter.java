package com.guyuan.dear.focus.client.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.client.bean.ClientContactBean;
import com.guyuan.dear.R;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @description: 我的关注-客户详情--基础信息:手机列表适配器
 * @author: 许建宁
 * @since: 2020/10/27 10:58
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientPhoneAdapter extends BaseRecyclerAdapter<ClientContactBean> {
    public ClientPhoneAdapter(Context context, @NonNull List<ClientContactBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ClientContactBean item,
                                  int position) {

        holder.setText(R.id.tv_contact_name, item.getName());
        holder.setText(R.id.tv_position, item.getPosition());
        holder.setText(R.id.tv_phone, item.getPhone());
        holder.setText(R.id.tv_email, item.getMailbox());


    }
}
