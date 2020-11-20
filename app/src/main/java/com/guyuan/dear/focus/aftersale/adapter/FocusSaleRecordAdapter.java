package com.guyuan.dear.focus.aftersale.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFocusSaleRecordBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 15:54
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusSaleRecordAdapter extends BaseDBRecycleAdapter<Object, ItemFocusSaleRecordBinding> {
    public FocusSaleRecordAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}