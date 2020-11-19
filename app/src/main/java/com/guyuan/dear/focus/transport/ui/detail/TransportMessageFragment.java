package com.guyuan.dear.focus.transport.ui.detail;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.ItemTransportMessageBinding;
import com.guyuan.dear.focus.transport.adapter.TransportMessageAdapter;
import com.guyuan.dear.focus.transport.data.TransportViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:21
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportMessageFragment extends BaseListFragment<Object, ItemTransportMessageBinding, TransportViewModel> {

    public static final String TAG = "TransportMessageFragment";

    public static TransportMessageFragment newInstance() {

        Bundle args = new Bundle();

        TransportMessageFragment fragment = new TransportMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        TransportMessageAdapter messageAdapter = new TransportMessageAdapter(listData,
                R.layout.item_transport_message);
        setDefaultAdapter(messageAdapter);
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