package com.guyuan.dear.work.qc.adapers;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemProductionInfoBinding;
import com.guyuan.dear.work.qc.beans.ProductInfo;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 15:56
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductionInfoAdapter extends BaseDBRecycleAdapter<ProductInfo, ItemProductionInfoBinding> {

    public ProductionInfoAdapter(List<ProductInfo> listData) {
        super(listData, R.layout.item_production_info);
    }

    @Override
    protected void bindDataToView(Holder holder, ProductInfo item, int position) {
        holder.binding.setData(item);
    }
}
