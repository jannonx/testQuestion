package com.guyuan.dear.focus.transport.ui.detail;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.ItemTransportDocumentsBinding;
import com.guyuan.dear.focus.transport.adapter.TransportDocumentAdapter;
import com.guyuan.dear.focus.transport.data.TransportViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:26
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportDocumentFragment extends BaseListFragment<String,
        ItemTransportDocumentsBinding, TransportViewModel> {

    public static final String TAG = "TransportDocumentFragment";

    public static TransportDocumentFragment newInstance() {

        Bundle args = new Bundle();

        TransportDocumentFragment fragment = new TransportDocumentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        list_container.setBackgroundColor(getResources().getColor(R.color.bg_window));
        TransportDocumentAdapter documentAdapter = new TransportDocumentAdapter(listData,
                R.layout.item_transport_documents);
        adapter = new BaseRecyclerViewAdapter(documentAdapter);
        recycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recycleView.setAdapter(adapter);
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