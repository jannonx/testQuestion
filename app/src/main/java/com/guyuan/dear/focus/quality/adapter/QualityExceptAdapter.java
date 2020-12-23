package com.guyuan.dear.focus.quality.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class QualityExceptAdapter extends BaseRecyclerAdapter<SimpleTabBean> {
    public QualityExceptAdapter(Context context, @NonNull List<SimpleTabBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, SimpleTabBean item,
                                  int position) {
//        holder.setText(R.id.tv_produce_type_name, item.getProductName());



    }
}
