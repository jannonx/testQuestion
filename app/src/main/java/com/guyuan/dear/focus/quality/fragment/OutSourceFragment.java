package com.guyuan.dear.focus.quality.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.quality.adapter.OutSourceAdapter;
import com.guyuan.dear.focus.quality.data.FocusQualityViewModel;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--质检--委外关键零部件
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class OutSourceFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, FocusQualityViewModel> {

    public static final String TAG = OutSourceFragment.class.getSimpleName();

    public static OutSourceFragment newInstance() {

        Bundle args = new Bundle();

        OutSourceFragment fragment = new OutSourceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        OutSourceAdapter outSourceAdapter = new OutSourceAdapter(getContext(),
                listData, R.layout.item_focus_out_source);
        adapter = new BaseRecyclerViewAdapter(outSourceAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setPullRefreshEnabled(isPullEnable());
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());

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
