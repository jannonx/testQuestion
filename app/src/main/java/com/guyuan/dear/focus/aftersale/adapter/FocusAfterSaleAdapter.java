package com.guyuan.dear.focus.aftersale.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFoucsAfterSaleBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 14:11
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleAdapter extends BaseDBRecycleAdapter<Object, ItemFoucsAfterSaleBinding> {
    public FocusAfterSaleAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}