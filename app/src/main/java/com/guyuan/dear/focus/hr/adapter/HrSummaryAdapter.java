package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 18:14
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryAdapter extends BaseRecyclerAdapter<HrStatusGroup> {
    public HrSummaryAdapter(Context context, @NonNull List<HrStatusGroup> listData) {
        super(context, listData, R.layout.item_hr_status_group);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, HrStatusGroup item, int position) {
        holder.setText(R.id.item_hr_status_group_tv_name,item.getName());
        holder.setText(R.id.item_hr_status_group_tv_count,String.valueOf(item.getStaffTotal()));
    }
}
