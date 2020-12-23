package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.adapter.FocusProduceAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.utils.LogUtils;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceTotalFragment extends BaseProduceFragment {

    public static final String TAG = FocusProduceTotalFragment.class.getSimpleName();

    public static FocusProduceTotalFragment newInstance() {
        Bundle args = new Bundle();
        FocusProduceTotalFragment fragment = new FocusProduceTotalFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        searchBar.setHint("输入产品名称、产品代号");
        FocusProduceAdapter listAdapter = new FocusProduceAdapter(getContext(), listData,
                R.layout.item_focus_produce, FocusProduceAdapter.FROM_TOTAL);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FocusProduceDetailActivity.start(getContext(), listData.get(position), false);
            }
        });
        viewModel.getProduceList(getListRequestBody(true));

        viewModel.getProduceListEvent().observe(getActivity(), new Observer<RefreshBean<FocusProduceBean>>() {
            @Override
            public void onChanged(RefreshBean<FocusProduceBean> dataRefreshBean) {
                LogUtils.showLog("size=" + dataRefreshBean.getContent().size());
                setListData(dataRefreshBean.getContent());
            }
        });
    }


    @Override
    protected void loadMore() {
        viewModel.getProduceList(getListRequestBody(false));
    }

    @Override
    protected void refresh() {
        viewModel.getProduceList(getListRequestBody(true));
    }


}
