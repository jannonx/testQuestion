package com.guyuan.dear.focus.produce.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusAdapter extends BaseRecyclerAdapter<ProduceStateBean> {
    public FocusProduceStatusAdapter(Context context, @NonNull List<ProduceStateBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProduceStateBean item,
                                  int position) {

        ImageView imageView = holder.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadUrlImage(imageView, item.getImgUrl());

        View vBall = holder.getView(R.id.view_ball);

//        tvStatus.setBackgroundResource(item.getStatusTextColor());

        holder.setText(R.id.tv_produce_status, item.getTitle());
        holder.setText(R.id.tv_time, item.getCreateTime());

        holder.setText(R.id.tv_name, item.getCreateName());
        holder.setText(R.id.tv_comment, item.getRemark());
        holder.setText(R.id.tv_department, item.getDepartmentName());
    }
}
