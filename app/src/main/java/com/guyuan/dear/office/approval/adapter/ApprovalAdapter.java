package com.guyuan.dear.office.approval.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemApprovalBinding;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 12:08
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalAdapter extends BaseDBRecycleAdapter<Object, ItemApprovalBinding> {

    public ApprovalAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}