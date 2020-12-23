package com.guyuan.dear.work.client.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @description: 我的关注--我的客户--列表适配器
 * @author: 许建宁
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientFollowAdapter extends BaseRecyclerAdapter<ClientCompanyBean> {
    public ClientFollowAdapter(Context context, @NonNull List<ClientCompanyBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ClientCompanyBean item,
                                  int position) {

        holder.setText(R.id.tv_client_name, item.getCusName());
        holder.setText(R.id.tv_latest_time, item.getFollowUpTime());
    }
}
