package com.guyuan.dear.focus.client.adapter;

import android.content.Context;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;

import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;


/**
 * @description: 我的关注-客户详情--跟进动态：评价列表适配器
 * @author: 许建宁
 * @since: 2020/10/27 10:58
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusParentAdapter extends BaseRecyclerAdapter<CommentsBean> {
    private boolean isCommentBtnVisible = false;
    private OnFollowClickListener clickListener;


    public FollowStatusParentAdapter(Context context, @NonNull List<CommentsBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, CommentsBean item,
                                  int position) {
        holder.setText(R.id.tv_name, item.getCreateName());
        holder.setText(R.id.tv_time, item.getCreateTime());
        holder.setText(R.id.tv_department, item.getDeptName());
        holder.setText(R.id.tv_comment, item.getContent());

        TextView tvRemarkBtn = holder.getView(R.id.tv_remark_on);
        tvRemarkBtn.setVisibility(isCommentBtnVisible ? View.VISIBLE : View.GONE);
        tvRemarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(item.getId());
                }
            }
        });

        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());

        BaseRecyclerView baseRecycleView = holder.getView(R.id.base_recycleView);
        FollowStatusChildAdapter listAdapter = new FollowStatusChildAdapter(context, item.getBusinessDetails(),
                R.layout.item_follow_status_child);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(listAdapter);
        baseRecycleView.setAdapter(adapter);
        baseRecycleView.setLayoutManager(new LinearLayoutManager(context));
        baseRecycleView.setLoadMoreEnabled(false);
        baseRecycleView.setPullRefreshEnabled(false);

        //最后一项，下划线不显示
//        View lineBottom = holder.getView(R.id.line_bottom);
//        lineBottom.setVisibility(position == listData.size() - 1 ? View.GONE : View.VISIBLE);


    }

    public void setCommentBtnVisible(boolean commentBtnVisible) {
        isCommentBtnVisible = commentBtnVisible;
    }

    public void setClickListener(OnFollowClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnFollowClickListener {
        void onClick(long followId);
    }
}
