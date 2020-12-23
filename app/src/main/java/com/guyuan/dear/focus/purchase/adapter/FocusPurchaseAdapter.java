package com.guyuan.dear.focus.purchase.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.BR;
import com.guyuan.dear.databinding.ItemFocusPurchaseBinding;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseListBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 17:51
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseAdapter extends BaseDBRecycleAdapter<PurchaseListBean.ContentBean, ItemFocusPurchaseBinding> {

    public FocusPurchaseAdapter(List<PurchaseListBean.ContentBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, PurchaseListBean.ContentBean item, int position) {
        holder.binding.setVariable(BR.purchaseListContentBean, item);
    }
}