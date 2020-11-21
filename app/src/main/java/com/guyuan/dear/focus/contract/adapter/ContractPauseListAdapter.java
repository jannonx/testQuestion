package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemExcptContractListBinding;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseListAdapter extends BaseDBRecycleAdapter<BaseContractExcptBean,ItemExcptContractListBinding> {


    public ContractPauseListAdapter(List<BaseContractExcptBean> listData) {
        super(listData, R.layout.item_excpt_contract_list);
    }

    @Override
    protected void bindDataToView(Holder holder, BaseContractExcptBean item, int position) {
        holder.binding.setData(item);
    }

}