package com.guyuan.dear.focus.produce.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 生产详情--生产动态适配器
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusAdapter extends BaseRecyclerAdapter<ProduceStateBean> {
    public FocusProduceStatusAdapter(Context context, @NonNull List<ProduceStateBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProduceStateBean item,
                                  int position) {

        //只有一条数据
        boolean onlyOneData = listData != null && listData.size() == 1;
        //最后一条数据
        boolean lastOneItem = listData != null && (listData.size() - 1) == position;

        LogUtils.showLog("size=" + listData.size() + "...position=" + position);
        LogUtils.showLog("onlyOneData=" + onlyOneData + "...lastOneItem=" + lastOneItem);
        View specialView = holder.getView(R.id.rl_first_view);
        View normalView = holder.getView(R.id.cl_order_detail);
        View firstAboveLie = holder.getView(R.id.v_first_above);
        View vAboveLie = holder.getView(R.id.v_line_above);
        View viewEmptyBottom = holder.getView(R.id.view_empty_bottom);

        specialView.setVisibility(lastOneItem ? View.VISIBLE : View.GONE);
        firstAboveLie.setVisibility(onlyOneData ? View.GONE : View.VISIBLE);

        normalView.setVisibility(lastOneItem ? View.GONE : View.VISIBLE);
        vAboveLie.setVisibility(position == 0 ? View.GONE : View.VISIBLE);

        //specialData
        holder.setText(R.id.tv_activate_name, item.getCreateName() + "：生产开始");
        holder.setText(R.id.tv_activate_time, item.getCreateTime());
        //normalData

        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());

        View vBall = holder.getView(R.id.view_ball);

        vBall.setBackgroundResource(item.getBallBg());

        holder.setText(R.id.tv_product_status, item.getTitle());
        holder.setText(R.id.tv_time, TextUtils.isEmpty(item.getCreateTime())?item.getApprovalTime():item.getCreateTime());

        holder.setText(R.id.tv_name, TextUtils.isEmpty(item.getCreateName())?item.getApproveName():item.getCreateName());
        holder.setText(R.id.tv_comment, item.getRemarks());
        holder.setText(R.id.tv_department, TextUtils.isEmpty(item.getCreateDept())?item.getDepartmentName():item.getCreateDept());


        viewEmptyBottom.setVisibility(listData.size() >= 3 && (position == listData.size() - 1) ? View.VISIBLE : View.GONE);
    }
}
