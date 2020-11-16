package com.guyuan.dear.focus.purchase.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFocusPurchaseBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 17:51
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseAdapter extends BaseDBRecycleAdapter<Object, ItemFocusPurchaseBinding> {

    public FocusPurchaseAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}