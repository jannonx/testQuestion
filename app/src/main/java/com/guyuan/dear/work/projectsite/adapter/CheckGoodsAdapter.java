package com.guyuan.dear.work.projectsite.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsAdapter extends BaseRecyclerAdapter<CheckGoodsBean> {
    public CheckGoodsAdapter(Context context, @NonNull List<CheckGoodsBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, CheckGoodsBean item,
                                  int position) {
//        holder.setText(R.id.tv_produce_type_name, item.getProductName());



    }
}
