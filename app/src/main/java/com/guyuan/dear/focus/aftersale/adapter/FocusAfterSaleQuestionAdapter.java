package com.guyuan.dear.focus.aftersale.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemFocusAfterSaleQuestionBinding;

import java.util.List;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 16:17
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleQuestionAdapter extends BaseDBRecycleAdapter<Object, ItemFocusAfterSaleQuestionBinding> {
    public FocusAfterSaleQuestionAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}