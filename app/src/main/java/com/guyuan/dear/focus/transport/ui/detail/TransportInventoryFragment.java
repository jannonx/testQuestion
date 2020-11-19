package com.guyuan.dear.focus.transport.ui.detail;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.ItemTransportDetailInventoryBinding;
import com.guyuan.dear.focus.transport.adapter.InventoryAdapter;
import com.guyuan.dear.focus.transport.data.TransportViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:16
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportInventoryFragment extends BaseListFragment<Object,
        ItemTransportDetailInventoryBinding, TransportViewModel> {

    public static final String TAG = "TransportInventoryFragment";

    public static TransportInventoryFragment newInstance() {

        Bundle args = new Bundle();

        TransportInventoryFragment fragment = new TransportInventoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        InventoryAdapter inventoryAdapter = new InventoryAdapter(listData,
                R.layout.item_transport_detail_inventory);
        setDefaultAdapter(inventoryAdapter);
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