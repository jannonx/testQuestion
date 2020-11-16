package com.guyuan.dear.focus.purchase.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFocusPurchaseRecordBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 18:01
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseRecordAdapter extends BaseDBRecycleAdapter<Object, ItemFocusPurchaseRecordBinding> {
    public FocusPurchaseRecordAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}