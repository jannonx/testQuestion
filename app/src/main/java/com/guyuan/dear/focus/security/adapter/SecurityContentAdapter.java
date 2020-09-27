package com.guyuan.dear.focus.security.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.security.data.beans.SecurityBaseBean;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 15:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class SecurityContentAdapter extends BaseRecyclerAdapter<SecurityBaseBean> {
    public SecurityContentAdapter(Context context, @NonNull List<SecurityBaseBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, SecurityBaseBean item, int position) {
        GlideUtils.getInstance().loadImageInRound((ImageView) holder.getView(R.id.item_content_iv),
                item.getImgUrl(), 4);
        holder.setText(R.id.item_content_tv, item.getName());
    }
}
