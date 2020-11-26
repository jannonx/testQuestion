package com.guyuan.dear.focus.aftersale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.customizeview.flowlayout.TagFlowLayout;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 2020/11/17 12:26
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class AfterSaleStatusAdapter extends BaseRecyclerAdapter<AfterSaleStatusBean> {
    public AfterSaleStatusAdapter(Context context, @NonNull List<AfterSaleStatusBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, AfterSaleStatusBean item,
                                  int position) {

        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());

        holder.setText(R.id.tv_name, item.getName());
        holder.setText(R.id.tv_time, item.getCreateTime());

        holder.setText(R.id.tv_comment, item.getIdea());
        holder.setText(R.id.tv_department, item.getDepartmentName());

        View lineBottom = holder.getView(R.id.line_bottom);
        lineBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);

        TagFlowLayout view = holder.getView(R.id.tfl_picture);
        view.setAdapter(new TagAdapter(item.getImgUrlList()) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                View rootView = LayoutInflater.from(context).inflate(R.layout.item_after_sale_image, parent, false);
                ImageView imageView = rootView.findViewById(R.id.image_view);
                GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrlList().get(position));
                return rootView;
            }
        });
    }
}
