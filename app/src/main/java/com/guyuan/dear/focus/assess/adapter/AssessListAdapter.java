package com.guyuan.dear.focus.assess.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.assess.ui.FocusAssessListFragment;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 11:44
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessListAdapter extends BaseRecyclerAdapter<Object> {

    public AssessListAdapter(Context context, @NonNull List<Object> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, Object item, int position) {

    }
}