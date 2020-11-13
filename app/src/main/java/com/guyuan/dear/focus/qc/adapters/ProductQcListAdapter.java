package com.guyuan.dear.focus.qc.adapters;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemProductQcListBinding;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 11:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcListAdapter extends BaseDBRecycleAdapter<BaseProductQcReport, ItemProductQcListBinding> {
    public ProductQcListAdapter(List<BaseProductQcReport> listData) {
        super(listData, R.layout.item_product_qc_list);
    }

    @Override
    protected void bindDataToView(Holder holder, BaseProductQcReport item, int position) {
        holder.binding.setData(item);
    }
}
