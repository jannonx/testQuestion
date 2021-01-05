package com.guyuan.dear.focus.contract.adapter;

import android.view.View;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.databinding.ItemClientAcceptanceBillsBinding;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 16:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClientAcceptanceBillAdapter extends BaseDBRecycleAdapter<String, ItemClientAcceptanceBillsBinding> {
    public ClientAcceptanceBillAdapter(List<String> listData) {
        super(listData, R.layout.item_client_acceptance_bills);
    }

    @Override
    protected void bindDataToView(Holder holder, String item, int position) {
        holder.binding.setImgUrl(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenShowActivity.start(getContext(), getListData(), position);
            }
        });
    }
}
