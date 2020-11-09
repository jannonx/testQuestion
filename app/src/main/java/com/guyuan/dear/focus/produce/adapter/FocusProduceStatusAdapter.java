package com.guyuan.dear.focus.produce.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusAdapter extends BaseRecyclerAdapter<FocusProduceBean> {
    public FocusProduceStatusAdapter(Context context, @NonNull List<FocusProduceBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, FocusProduceBean item,
                                  int position) {
        View vAbove = holder.getView(R.id.v_line_above);
        vAbove.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
//        if (TextUtils.isEmpty(item.getTitle())) return;


    }
}
