package com.guyuan.dear.focus.contract.bindingadpaters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.contract.adapter.ContractExcptListAdapter;
import com.guyuan.dear.focus.contract.bean.ExcptContractBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptListBindingAdapter {
    @BindingAdapter(value = {"setExcptContractList", "setOnItemClick"}, requireAll = true)
    public static void setExcptContractList(RecyclerView view, List<ExcptContractBean> data,
                                            ContractExcptListAdapter.ItemClickListener itemClickListener) {
        if (data == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        ContractExcptListAdapter adapter = new ContractExcptListAdapter(data, view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
        adapter.setItemClickListener(itemClickListener);
    }
}
