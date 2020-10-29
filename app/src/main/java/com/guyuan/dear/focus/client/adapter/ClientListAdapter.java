package com.guyuan.dear.focus.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ClientContactBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 10:58
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientListAdapter extends BaseRecyclerAdapter<ClientCompanyBean> {
    public ClientListAdapter(Context context, @NonNull List<ClientCompanyBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ClientCompanyBean item,
                                  int position) {
        if (TextUtils.isEmpty(item.getCusAddress())) return;
        holder.setText(R.id.tv_client_name, item.getCusName());
        List<ClientContactBean> list = item.getList();
        if (!list.isEmpty()) {
            holder.setText(R.id.tv_client_contact, "联系人：" + list.get(0).getName());
            holder.setText(R.id.tv_client_phone, list.get(0).getPhone());
        }

        holder.setText(R.id.tv_salesman, item.getSalesman());
        holder.setText(R.id.tv_latest_time, item.getFollowUpTime());


        holder.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty()) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(0).getPhone()));
                    context.startActivity(dialIntent);
                }

            }
        });

    }
}
