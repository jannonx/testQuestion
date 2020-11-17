package com.guyuan.dear.focus.qc.adapters;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemMaterialQcListBinding;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 12:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcListAdapter extends BaseDBRecycleAdapter<BaseMaterialQcReport, ItemMaterialQcListBinding> {
    public MaterialQcListAdapter(List<BaseMaterialQcReport> listData) {
        super(listData, R.layout.item_material_qc_list);
    }

    @Override
    protected void bindDataToView(Holder holder, BaseMaterialQcReport item, int position) {
        holder.binding.setData(item);
    }
}
