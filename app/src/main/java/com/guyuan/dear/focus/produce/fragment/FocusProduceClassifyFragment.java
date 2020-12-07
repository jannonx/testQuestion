package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.adapter.FocusProduceAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--生产计划--分类查询
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceClassifyFragment extends BaseProduceFragment {

    public static final String TAG = FocusProduceClassifyFragment.class.getSimpleName();


    public static FocusProduceClassifyFragment newInstance(ProductStatusType data) {
        Bundle bundle = new Bundle();
        FocusProduceClassifyFragment fragment = new FocusProduceClassifyFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {
        Bundle arguments = getArguments();
        statusType = (ProductStatusType) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        FocusProduceAdapter listAdapter = new FocusProduceAdapter(getContext(), listData,
                R.layout.item_focus_produce);
        etSearch.setHint("输入产品名称、产品代号");
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FocusProduceDetailActivity.start(getContext(),listData.get(position),false);
            }
        });
        viewModel.getProduceListByStatus(getListRequestBody(true));
        viewModel.getStatusEvent().observe(getActivity(), new Observer<RefreshBean<FocusProduceBean>>() {
            @Override
            public void onChanged(RefreshBean<FocusProduceBean> dataRefreshBean) {
                setListData(dataRefreshBean.getContent());
            }
        });
    }


    @Override
    protected void refresh() {
        viewModel.getProduceList(getListRequestBody(true));
    }


    @Override
    protected void loadMore() {
        viewModel.getProduceListByStatus(getListRequestBody(false));
    }





}
