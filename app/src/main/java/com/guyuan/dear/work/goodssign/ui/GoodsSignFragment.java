package com.guyuan.dear.work.goodssign.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.goodssign.adapter.GoodsSignAdapter;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignListBean;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignFragment extends BaseListSearchFragment<GoodsSignListBean.ContentBean, FragmentListSearchBinding, GoodsSignViewModel> {

    public static final String TAG = "GoodsSignFragment";

    public static GoodsSignFragment newInstance() {

        Bundle args = new Bundle();

        GoodsSignFragment fragment = new GoodsSignFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        searchBar.setHint("输入客户名称、合同编号");
        GoodsSignAdapter goodsSignAdapter = new GoodsSignAdapter(listData, R.layout.item_work_goods_sign);
        setDefaultAdapter(goodsSignAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                GoodsSignListBean.ContentBean contentBean = listData.get(i);
                GoodsSignDetailActivity.start(getContext(), contentBean.getSupplierName(), contentBean.getId());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void refresh() {
        currentType = REFRESH;
        currentPage = FIRST_PAGE;
        viewModel.getGoodsSignList(currentPage, searchContent);
    }

    @Override
    protected void loadMore() {
        viewModel.getGoodsSignList(++currentPage, searchContent);
    }


    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}