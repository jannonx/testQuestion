package com.guyuan.dear.focus.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 10:58
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusChildAdapter extends BaseRecyclerAdapter<CommentsBean> {
    public FollowStatusChildAdapter(Context context, @NonNull List<CommentsBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, CommentsBean item,
                                  int position) {
        holder.setText(R.id.tv_name, item.getCreateName());
        holder.setText(R.id.tv_time, item.getCreateTime());
        holder.setText(R.id.tv_department, item.getDepartmentName());
        holder.setText(R.id.tv_comment, item.getContent());
        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());
    }
}
