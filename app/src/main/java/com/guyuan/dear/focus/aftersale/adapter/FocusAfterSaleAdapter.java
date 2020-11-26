package com.guyuan.dear.focus.aftersale.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemFoucsAfterSaleBinding;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 14:11
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleAdapter extends BaseRecyclerAdapter<AfterSaleBean> {


    public FocusAfterSaleAdapter(Context context, @NonNull List<AfterSaleBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, AfterSaleBean item, int position) {
        holder.setText(R.id.tv_title, item.getTitle());
        //状态属性设置
        holder.setText(R.id.tv_engineering_status, item.getStatusText());
        TextView tvStatus = holder.getView(R.id.tv_engineering_status);
        tvStatus.setBackgroundResource(item.getStatusTextBg());
        int statusTextColor = item.getStatusTextColor();

        tvStatus.setTextColor(context.getResources().getColor(statusTextColor,null));

        holder.setText(R.id.tv_engineering_name, item.getProjectName());
        holder.setText(R.id.tv_customer_name, item.getConsumerName());
        holder.setText(R.id.tv_engineering_location, item.getConstructionLocaltion());
//        holder.setText(R.id.tv_operator, item.getCheckMan());
        holder.setText(R.id.tv_time, item.getUpdateTime());



    }
}