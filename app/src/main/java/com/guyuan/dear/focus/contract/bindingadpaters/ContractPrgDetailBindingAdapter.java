package com.guyuan.dear.focus.contract.bindingadpaters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.contract.adapter.ComponentStatesAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractPrgDetailKnotAdapter;
import com.guyuan.dear.customizeview.itemDecorator.ContractPrgKnotItemDecorator;
import com.guyuan.dear.focus.contract.bean.ComponentStateBean;
import com.guyuan.dear.focus.contract.bean.ContractPrgKnot;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

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
    public static void setContractPrgKnots(BaseRecyclerView view, List<ContractPrgKnot> data){
        if(data==null){
            return;
        }
        view.addItemDecoration(new ContractPrgKnotItemDecorator());
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),5,RecyclerView.VERTICAL,false);
        ContractPrgDetailKnotAdapter adapter = new ContractPrgDetailKnotAdapter(data);
        BaseRecyclerViewAdapter wrapper =new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);

    }
}
