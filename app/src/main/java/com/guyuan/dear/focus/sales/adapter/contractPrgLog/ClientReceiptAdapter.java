package com.guyuan.dear.focus.sales.adapter.contractPrgLog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.sales.bean.contractPrgLog.ClientReceipt;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/19 11:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClientReceiptAdapter extends BaseRecyclerAdapter<ClientReceipt> {

    public ClientReceiptAdapter(Context context, @NonNull List<ClientReceipt> listData) {
        super(context, listData, R.layout.item_contract_prg_client_receipt);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ClientReceipt item, int position) {
        holder.setImageResource(R.id.item_contract_prg_client_receipt_iv_pic, item.getSrc());
    }
}
