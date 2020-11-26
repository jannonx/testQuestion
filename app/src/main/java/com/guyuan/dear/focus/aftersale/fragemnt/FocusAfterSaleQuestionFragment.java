package com.guyuan.dear.focus.aftersale.fragemnt;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.ItemFocusAfterSaleQuestionBinding;
import com.guyuan.dear.focus.aftersale.adapter.FocusAfterSaleQuestionAdapter;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 16:14
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleQuestionFragment extends BaseListFragment<Object, ItemFocusAfterSaleQuestionBinding, FocusAfterSaleViewModel> {

    public static FocusAfterSaleQuestionFragment newInstance() {

        Bundle args = new Bundle();

        FocusAfterSaleQuestionFragment fragment = new FocusAfterSaleQuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        FocusAfterSaleQuestionAdapter questionAdapter = new FocusAfterSaleQuestionAdapter(listData,
                R.layout.item_focus_after_sale_question);
        setDefaultAdapter(questionAdapter);
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