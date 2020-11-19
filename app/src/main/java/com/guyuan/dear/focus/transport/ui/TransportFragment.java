package com.guyuan.dear.focus.transport.ui;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.transport.adapter.TransportAdapter;
import com.guyuan.dear.focus.transport.data.TransportViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:40
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportFragment extends BaseListSearchFragment<Object, FragmentListSearchBinding, TransportViewModel> {

    public static final int TOTAL = 1;    //总的
    public static final int ARRIVED = 2;  //已到达
    private String content;

    public static TransportFragment newInstance(int type, int id, String content) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_STATUS_TYPE, type);
        args.putInt(ConstantValue.KEY_ID, id);
        args.putString(ConstantValue.KEY_CONTENT, content);
        TransportFragment fragment = new TransportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            int type = getArguments().getInt(ConstantValue.KEY_STATUS_TYPE);
            int id = getArguments().getInt(ConstantValue.KEY_ID);
            String content = getArguments().getString(ConstantValue.KEY_CONTENT);

            TransportAdapter transportAdapter = new TransportAdapter(listData, R.layout.item_focus_transport);
            setDefaultAdapter(transportAdapter);
        }
    }


    @Override
    protected void onSearch(String text) {
        super.onSearch(text);
    }

    @Override
    protected void editEmptyChange() {
        super.editEmptyChange();
        content = "";
    }

    @Override
    protected void editTextChanged(String text) {
        super.editTextChanged(text);
        content = text;
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