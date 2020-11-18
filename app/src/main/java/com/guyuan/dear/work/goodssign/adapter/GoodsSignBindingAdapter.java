package com.guyuan.dear.work.goodssign.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignBean;
import com.guyuan.dear.work.goodssign.ui.GoodsSignItemDetailActivity;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/18 17:03
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignBindingAdapter {

    @BindingAdapter("signGoods")
    public void setSignProductList(BaseRecyclerView rv, List<GoodsSignBean> goodsSignBeanList) {
        GoodsSignDetailAdapter goodsSignAdapter = new GoodsSignDetailAdapter(goodsSignBeanList, R.layout.item_work_goods_sign_detail);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(goodsSignAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                GoodsSignItemDetailActivity.start(DearApplication.getInstance(), goodsSignBeanList.get(i).getId());
            }
        });
        rv.setAdapter(adapter);
        rv.setPullRefreshEnabled(false);
        rv.setLoadMoreEnabled(false);
    }
}