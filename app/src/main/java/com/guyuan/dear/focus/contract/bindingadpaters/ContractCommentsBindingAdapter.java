package com.guyuan.dear.focus.contract.bindingadpaters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.contract.adapter.ContractCommentListAdapter;
import com.guyuan.dear.focus.contract.adapter.SubCommentListAdapter;
import com.guyuan.dear.focus.contract.bean.ContractComment;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/23 11:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractCommentsBindingAdapter {

    @BindingAdapter("setContractComments")
    public static void setContractComments(BaseRecyclerView view, List<ContractComment> listData) {
        if (listData == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        ContractCommentListAdapter adapter = new ContractCommentListAdapter(listData);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);
    }

    @BindingAdapter("setSubContractComments")
    public static void setSubContractComments(BaseRecyclerView view, List<ContractComment> listData) {
        if (listData == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        SubCommentListAdapter adapter = new SubCommentListAdapter(listData);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);
    }

}
