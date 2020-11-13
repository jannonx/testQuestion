package com.guyuan.dear.focus.qc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemMaterialQcListBinding;
import com.guyuan.dear.databinding.ItemProductQcListBinding;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 15:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AllQcListAdapter extends RecyclerView.Adapter<AllQcListAdapter.ViewHolder> {

    private List<GenericQcReport> list;
    private Context context;

    public AllQcListAdapter(List<GenericQcReport> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        int layoutId = -1;
        switch (viewType) {
            case GenericQcReport.REPORT_TYPE_MATERIAL:
                layoutId = R.layout.item_material_qc_list;
                break;
            default:
                layoutId = R.layout.item_product_qc_list;
                break;
        }
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                layoutId, parent, false
        );
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
        Gson gson = new Gson();
        GenericQcReport genericQcReport = list.get(position);
        switch (viewType) {
            case GenericQcReport.REPORT_TYPE_MATERIAL:
                ItemMaterialQcListBinding material = (ItemMaterialQcListBinding) dataBinding;
                BaseMaterialQcReport materialQcReport = gson.fromJson(genericQcReport.getJson(), BaseMaterialQcReport.class);
                material.setData(materialQcReport);
                break;
            default:
                ItemProductQcListBinding product = (ItemProductQcListBinding) dataBinding;
                BaseProductQcReport productQcReport = gson.fromJson(genericQcReport.getJson(), BaseProductQcReport.class);
                product.setData(productQcReport);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
