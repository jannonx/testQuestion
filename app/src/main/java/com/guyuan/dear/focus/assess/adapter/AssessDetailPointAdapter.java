package com.guyuan.dear.focus.assess.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.BR;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemFocusAssessDetailPointBinding;
import com.guyuan.dear.focus.assess.data.bean.AuditContentBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :评审点adapter
 * @since: 2020/10/21 14:58
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessDetailPointAdapter extends BaseDBRecycleAdapter<AuditContentBean, ItemFocusAssessDetailPointBinding> {

    public AssessDetailPointAdapter(List<AuditContentBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, AuditContentBean item, int position) {
        holder.binding.setVariable(BR.pointBean, item);
    }
}