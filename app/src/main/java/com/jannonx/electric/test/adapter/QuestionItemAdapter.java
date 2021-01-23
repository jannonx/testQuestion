package com.jannonx.electric.test.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.jannonx.electric.R;
import com.jannonx.electric.test.bean.ItemQuestionBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/19 14:11
 **/

public class QuestionItemAdapter extends BaseRecyclerAdapter<ItemQuestionBean> {


    public QuestionItemAdapter(Context context, @NonNull List<ItemQuestionBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ItemQuestionBean item, int position) {
        ConstraintLayout rootView = holder.getView(R.id.cl_item_root);
        holder.setText(R.id.tv_item, item.getItemType().getSign());
        holder.setText(R.id.tv_content, item.getContent());
//        LogUtils.showLog("getSelectIndex==" + item.getSelectIndex() + "position=" + position);
        rootView.setBackground(ResourcesCompat.getDrawable(context.getResources(),
                item.getSelectIndex() == position ? R.drawable.bg_gray_f8f8f8_stroke_33333_corner_6 :
                        R.drawable.bg_gray_f8f8f8_corner_6, null));

    }
}