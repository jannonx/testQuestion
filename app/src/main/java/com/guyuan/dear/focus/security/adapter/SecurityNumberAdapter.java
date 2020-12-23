package com.guyuan.dear.focus.security.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.security.data.beans.DangerNumberBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 17:58
 * @company : 固远（深圳）信息技术有限公司
 **/
public class SecurityNumberAdapter extends BaseRecyclerAdapter<DangerNumberBean.ListBean> {

    public SecurityNumberAdapter(Context context, @NonNull List<DangerNumberBean.ListBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, DangerNumberBean.ListBean item, int position) {

        Glide.with(context)
                .load(item.getImg_url())
                .error(R.mipmap.fire)
                .placeholder(R.mipmap.fire)
                .into((ImageView) holder.getView(R.id.number_iv));

        holder.setText(R.id.number_name_tv, item.getName() + ":");
        holder.setText(R.id.number_tv, item.getNum() + "");

//        holder.getView(R.id.danger_type_cv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onClick(type, bean.getName());
//                }
//            }
//        });
    }
}
