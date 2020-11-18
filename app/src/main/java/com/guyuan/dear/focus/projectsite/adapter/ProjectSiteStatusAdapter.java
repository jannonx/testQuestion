package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 2020/11/17 12:26
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectSiteStatusAdapter extends BaseRecyclerAdapter<SimpleTabBean> {
    public ProjectSiteStatusAdapter(Context context, @NonNull List<SimpleTabBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, SimpleTabBean item,
                                  int position) {
//        holder.setText(R.id.tv_produce_type_name, item.getProductName());


    }
}
