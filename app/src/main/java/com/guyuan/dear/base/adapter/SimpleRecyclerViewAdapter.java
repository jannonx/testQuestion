package com.guyuan.dear.base.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;


/**
 * @description: 公司bean
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class SimpleRecyclerViewAdapter extends BaseRecyclerAdapter<String> {
    public SimpleRecyclerViewAdapter(Context context, @NonNull List<String> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, String item,
                                  int position) {
        holder.setText(R.id.tv_title, item);
        View view = holder.getView(R.id.v_line);
        view.setVisibility(position == listData.size() - 1 ? View.GONE : View.VISIBLE);

    }
}
