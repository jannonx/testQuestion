package com.guyuan.dear.focus.produce.ui;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceAdapter;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--生产计划--分类查询
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceClassifyFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, FocusProduceViewModel> {

    public static final String TAG = "FocusProduceClassifyFragment";

    public static FocusProduceClassifyFragment newInstance() {
        Bundle args = new Bundle();
        FocusProduceClassifyFragment fragment = new FocusProduceClassifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < 5; i++) {
            SimpleTabBean contactBean = new SimpleTabBean();
            contactBean.setId(i);
            listData.add(contactBean);
        }
        FocusProduceAdapter listAdapter = new FocusProduceAdapter(getContext(), listData,
                R.layout.item_focus_produce);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FocusProduceDetailActivity.start(getContext());
            }
        });
    }

    @Override
    protected void init() {

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
