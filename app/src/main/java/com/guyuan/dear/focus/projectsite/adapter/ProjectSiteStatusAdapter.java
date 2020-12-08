package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 2020/11/17 12:26
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteStatusAdapter extends BaseRecyclerAdapter<ProjectSiteStatusBean> {
    public ProjectSiteStatusAdapter(Context context, @NonNull List<ProjectSiteStatusBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProjectSiteStatusBean item,
                                  int position) {

        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());

        holder.setText(R.id.tv_name, item.getName());
        holder.setText(R.id.tv_time, item.getCreateTime());

        holder.setText(R.id.tv_comment, item.getIdea());
        holder.setText(R.id.tv_department, item.getDepartmentName());

        View lineBottom = holder.getView(R.id.line_bottom);
        View viewBottom = holder.getView(R.id.view_bottom);
        lineBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);
        viewBottom.setVisibility(listData.size() - 1 == position ? View.VISIBLE : View.GONE);
    }
}
