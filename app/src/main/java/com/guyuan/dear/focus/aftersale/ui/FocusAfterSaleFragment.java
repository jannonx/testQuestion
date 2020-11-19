package com.guyuan.dear.focus.aftersale.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.aftersale.adapter.FocusAfterSaleAdapter;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.aftersale.ui.detail.FocusAfterSaleDetailActivity;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 13:57
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleFragment extends BaseListSearchFragment<Object, FragmentListSearchBinding, FocusAfterSaleViewModel> {

    public static final String TYPE = "type";
    public static final String CONTENT = "content";
    public static int QUALIFIED = 1;    //合格
    public static int UNQUALIFIED = 2;  //不合格

    public static FocusAfterSaleFragment newInstance(int type, String content) {

        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        args.putString(CONTENT, content);
        FocusAfterSaleFragment fragment = new FocusAfterSaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            int type = getArguments().getInt(TYPE);
            String content = getArguments().getString(CONTENT);

            FocusAfterSaleAdapter saleAdapter = new FocusAfterSaleAdapter(listData, R.layout.item_foucs_after_sale);
            setDefaultAdapter(saleAdapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    FocusAfterSaleDetailActivity.start(getContext(), "", 1);
                }
            });
        }
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}