package com.guyuan.dear.work.goodssign.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;
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

    @BindingAdapter(value = {"listData", "viewModel", "contractID"})
    public static void setListView(BaseRecyclerView rv, List<GoodsSignBean> listData, GoodsSignViewModel viewModel, int contractID) {
        GoodsSignDetailAdapter goodsSignAdapter = new GoodsSignDetailAdapter(listData, R.layout.item_work_goods_sign_detail);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(goodsSignAdapter);

        goodsSignAdapter.setListener(new GoodsSignDetailAdapter.GoodsDetailListener() {
            @Override
            public void sign(int id) {
                viewModel.sign(id, contractID);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                GoodsSignBean bean = listData.get(i);
                GoodsSignItemDetailActivity.start(rv.getContext(), bean.getName(), bean.getId());
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(DearApplication.getInstance()));
        rv.setAdapter(adapter);
        rv.setPullRefreshEnabled(false);
        rv.setLoadMoreEnabled(false);
    }

    @BindingAdapter("goodsStatus")
    public static void setGoodsStatus(TextView tv, int status) {
        if (status == 1) {
            tv.setText("待签收");
        } else if (status == 2) {
            tv.setText("已签收");
        }
    }

    @BindingAdapter("goodsArriveStatus")
    public static void setGoodsArriveStatus(TextView tv, int status) {
        if (status == 1) {
            tv.setText("待到货");
            tv.setTextAppearance(tv.getContext(), R.style.TextTagBlue);
        } else if (status == 2) {
            tv.setText("全部到货");
            tv.setTextAppearance(tv.getContext(), R.style.TextTagGreen);
        }
    }

    @BindingAdapter("goodsReceiveStatus")
    public static void setGoodsReceiveStatus(TextView tv, int status) {
        if (status == 1) {
            tv.setEnabled(true);
            tv.setText("确认到货");
        } else if (status == 2) {
            tv.setEnabled(false);
            tv.setText("已收货");
        }
    }
}