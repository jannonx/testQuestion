package com.guyuan.dear.focus.sales.bindingadpaters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.focus.sales.adapter.ComponentStatesAdapter;
import com.guyuan.dear.focus.sales.adapter.ContractPrgDetailKnotAdapter;
import com.guyuan.dear.focus.sales.adapter.ContractPrgKnotItemDecorator;
import com.guyuan.dear.focus.sales.bean.ComponentStateBean;
import com.guyuan.dear.focus.sales.bean.ContractPrgKnot;
import com.guyuan.dear.focus.sales.bean.ProductComponent;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/16 11:25
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailBindingAdapter {
    @BindingAdapter("setContractComponentStates")
    public static void setContractComponentStates(BaseRecyclerView view, List<ComponentStateBean> data){
        if(data==null){
            return;
        }
        ComponentStatesAdapter adapter = new ComponentStatesAdapter(view.getContext(),data);
        BaseRecyclerViewAdapter wrapper =new BaseRecyclerViewAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,false);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);
    }


    @BindingAdapter("setContractPrgKnots")
    public static void setContractPrgKnots(RecyclerView view, List<ContractPrgKnot> data){
        if(data==null){
            return;
        }
        view.addItemDecoration(new ContractPrgKnotItemDecorator());
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),5,RecyclerView.VERTICAL,false);
        ContractPrgDetailKnotAdapter adapter = new ContractPrgDetailKnotAdapter(data,view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }
}
