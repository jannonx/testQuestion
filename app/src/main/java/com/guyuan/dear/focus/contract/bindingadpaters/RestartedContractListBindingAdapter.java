package com.guyuan.dear.focus.contract.bindingadpaters;

import android.widget.StackView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.contract.adapter.RestartedContractListAdapter;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 17:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractListBindingAdapter {
    @BindingAdapter(value = {"setRestartedContractData","setRestartedContractClickListener"},requireAll = true)
    public static void setRestartedContractData(RecyclerView view, List<RestartedContractBean> list,
                                                RestartedContractListAdapter.OnItemClickListener listener){
        if(list==null){
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        RestartedContractListAdapter adapter = new RestartedContractListAdapter(list,view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
        adapter.setOnItemClickListener(listener);
    }

}