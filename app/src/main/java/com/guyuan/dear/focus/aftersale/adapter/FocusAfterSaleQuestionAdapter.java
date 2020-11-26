package com.guyuan.dear.focus.aftersale.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleQuestionBean;
import com.guyuan.dear.R;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 16:17
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleQuestionAdapter extends BaseRecyclerAdapter<AfterSaleQuestionBean> {


    public FocusAfterSaleQuestionAdapter(Context context, @NonNull List<AfterSaleQuestionBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, AfterSaleQuestionBean item, int position) {
        holder.setText(R.id.tv_des, "描述："+item.getDescribe());

        holder.setText(R.id.tv_answer, "处理意见："+item.getAnswer());



        View lineBottom = holder.getView(R.id.line_bottom);
        lineBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);
    }
}