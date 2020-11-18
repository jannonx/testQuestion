package com.guyuan.dear.work.goodssign.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemWorkGoodsSignDetailBinding;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 20:15
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignDetailAdapter extends BaseDBRecycleAdapter<GoodsSignBean, ItemWorkGoodsSignDetailBinding> {

    public GoodsSignDetailAdapter(List<GoodsSignBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, GoodsSignBean item, int position) {

    }
}