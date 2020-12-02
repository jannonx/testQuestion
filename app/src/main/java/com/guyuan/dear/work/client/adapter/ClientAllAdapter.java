package com.guyuan.dear.work.client.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.R;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 我的关注--全部客户--列表适配器
 * @author: 许建宁
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientAllAdapter extends BaseRecyclerAdapter<ClientCompanyBean> {
    public ClientAllAdapter(Context context, @NonNull List<ClientCompanyBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ClientCompanyBean item,
                                  int position) {
        holder.setText(R.id.tv_client_name, item.getCusName());
        holder.setText(R.id.label_salesman, "跟进人员：");
        holder.setText(R.id.tv_salesman, item.getFollowUp());
        holder.setText(R.id.tv_time, item.getFollowUpTime());


    }
}
