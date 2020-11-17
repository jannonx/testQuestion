package com.guyuan.dear.focus.client.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.client.bean.ClientContactBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 我的关注-客户详情--头部：手机列表适配器
 * @author: 许建宁
 * @since: 2020/10/27 10:58
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientPhoneHeaderAdapter extends BaseRecyclerAdapter<ClientContactBean> {
    public ClientPhoneHeaderAdapter(Context context, @NonNull List<ClientContactBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ClientContactBean item,
                                  int position) {

        holder.setText(R.id.tv_client_contact, item.getName());
        holder.setText(R.id.tv_client_phone, item.getPhone());



    }
}
