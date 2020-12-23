package com.guyuan.dear.focus.contract.bindingadpaters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.contract.adapter.ComponentInfoAdapter;
import com.guyuan.dear.focus.contract.bean.ProductComponent;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/20 20:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductBaseInfoBindingAdapter {
    @BindingAdapter("setComponentListData")
    public static void setComponentListData(BaseRecyclerView view, List<ProductComponent> listData){
        if(listData!=null){
            LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,false);
            ComponentInfoAdapter adapter = new ComponentInfoAdapter(listData);
            BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
            view.setLayoutManager(manager);
            view.setAdapter(wrapper);
            view.setLoadMoreEnabled(false);
            view.setPullRefreshEnabled(false);
        }
    }
}
