package com.guyuan.dear.work.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallDebugStatusAdapter extends BaseRecyclerAdapter<ProjectSiteStatusBean> {
    public InstallDebugStatusAdapter(Context context, @NonNull List<ProjectSiteStatusBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProjectSiteStatusBean item,
                                  int position) {

        //只有一条数据
        boolean onlyOneData = listData != null && listData.size() == 1;
        //最后一条数据
        boolean lastOneItem = listData != null && (listData.size() - 1) == position;

        View vLineBelow = holder.getView(R.id.v_line_below);
        View vAboveLie = holder.getView(R.id.v_line_above);

        vAboveLie.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        vLineBelow.setVisibility(lastOneItem ? View.GONE : View.VISIBLE);

        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());

        View vBall = holder.getView(R.id.view_ball);

        vBall.setBackgroundResource(item.getBallBg());

        holder.setText(R.id.tv_product_status, position == listData.size() - 1 ? "实际开始" : item.getTitle());
        holder.setText(R.id.tv_time, item.getCreateTime());

        holder.setText(R.id.tv_name, item.getName());
        holder.setText(R.id.tv_comment, item.getIdea());
        holder.setText(R.id.tv_department, item.getDepartmentName());

        //处在我的工作，列表数大于2条，且最后一条，显示空白块
        View viewEmptyBottom = holder.getView(R.id.view_empty_bottom);
        viewEmptyBottom.setVisibility(listData.size() > 2 && listData.size() - 1 == position ? View.VISIBLE : View.GONE);
    }
}
