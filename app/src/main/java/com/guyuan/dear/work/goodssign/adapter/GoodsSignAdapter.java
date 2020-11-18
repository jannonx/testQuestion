package com.guyuan.dear.work.goodssign.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemWorkGoodsSignBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 20:10
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignAdapter extends BaseDBRecycleAdapter<Object, ItemWorkGoodsSignBinding> {
    public GoodsSignAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}