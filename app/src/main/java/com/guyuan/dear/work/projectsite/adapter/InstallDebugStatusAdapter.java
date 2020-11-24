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

//        LogUtils.showLog("size=" + listData.size() + "...position=" + position);
//        LogUtils.showLog("onlyOneData=" + onlyOneData + "...lastOneItem=" + lastOneItem);
        View specialView = holder.getView(R.id.rl_first_view);
        View normalView = holder.getView(R.id.cl_order_detail);
        View firstAboveLie = holder.getView(R.id.v_first_above);
        View vAboveLie = holder.getView(R.id.v_line_above);

//        specialView.setVisibility(lastOneItem ? View.VISIBLE : View.GONE);
        firstAboveLie.setVisibility(onlyOneData ? View.GONE : View.VISIBLE);

        normalView.setVisibility(View.VISIBLE);
        vAboveLie.setVisibility(position == 0 ? View.GONE : View.VISIBLE);

        //specialData
//        holder.setText(R.id.tv_activate_name, item.getCreateName() + "：生产开始");
//        holder.setText(R.id.tv_activate_time, item.getCreateTime());
        //normalData

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
