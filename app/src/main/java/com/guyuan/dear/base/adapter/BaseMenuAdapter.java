package com.guyuan.dear.base.adapter;

import android.content.Context;

import androidx.annotation.NonNull;


import com.guyuan.dear.R;
import com.guyuan.dear.login.data.LoginBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * Created by TL
 * on 2019/12/13
 */
public class BaseMenuAdapter extends BaseRecyclerAdapter<LoginBean.AppMenusBean.ChildrenBean> {
    public BaseMenuAdapter(Context context,
                           @NonNull List<LoginBean.AppMenusBean.ChildrenBean> listData,
                           int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder,
                                  LoginBean.AppMenusBean.ChildrenBean item, int position) {

        holder.setImageUrl(R.id.item_fragment_menu_iv_icon, item.getIcon(),
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        holder.setText(R.id.item_fragment_menu_tv_tag, item.getTitle());
    }
}
