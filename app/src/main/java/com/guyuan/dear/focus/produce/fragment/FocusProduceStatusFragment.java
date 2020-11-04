package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceStatusAdapter;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusFragment extends BaseListFragment<SimpleTabBean, FragmentListBinding, FocusProduceViewModel> {

    public static final String TAG = "FocusProduceStatusFragment";

    public static FocusProduceStatusFragment newInstance() {
        Bundle args = new Bundle();
        FocusProduceStatusFragment fragment = new FocusProduceStatusFragment();
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
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_focus_produce_status, null);
        FocusProduceStatusAdapter listAdapter = new FocusProduceStatusAdapter(getContext(), listData,
                R.layout.item_focus_produce_status);

        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setPullRefreshEnabled(false);
        adapter.addFooterView(footerView);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });


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
