package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;
import java.util.Locale;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 17:32
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SubCommentListAdapter extends BaseRecyclerAdapter<ContractComment> {
    public SubCommentListAdapter(Context context, @NonNull List<ContractComment> listData) {
        super(context, listData, R.layout.item_contract_sub_comment);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ContractComment item, int position) {
        holder.setText(R.id.item_contract_sub_comment_tv_name,item.getCommenter());
        holder.setText(R.id.item_contract_sub_comment_tv_date,
                CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(item.getDate()));
        holder.setText(R.id.item_contract_sub_comment_tv_comment,item.getContent());
        String dept = String.format(Locale.CHINA,"来自部门：%s",item.getCommenterDept());
        holder.setText(R.id.item_contract_sub_comment_tv_dept,dept);
    }
}
