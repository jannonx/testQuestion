package com.guyuan.dear.focus.contract.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemContractBaseInfoBinding;
import com.guyuan.dear.focus.contract.bean.ProductComponent;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComponentInfoAdapter extends BaseDBRecycleAdapter<ProductComponent, ItemContractBaseInfoBinding> {
    public ComponentInfoAdapter(List<ProductComponent> listData) {
        super(listData, R.layout.item_contract_base_info);
    }

    @Override
    protected void bindDataToView(Holder holder, ProductComponent item, int position) {
        holder.binding.setData(item);

    }
}
