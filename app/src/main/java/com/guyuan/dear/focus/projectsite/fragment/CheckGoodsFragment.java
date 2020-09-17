package com.guyuan.dear.focus.projectsite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.projectsite.adapter.CheckGoodsAdapter;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--工程现场--清点货物
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding> {

    public static final String TAG = CheckGoodsFragment.class.getSimpleName();

    public static CheckGoodsFragment newInstance() {

        Bundle args = new Bundle();

        CheckGoodsFragment fragment = new CheckGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        CheckGoodsAdapter checkGoodsAdapter = new CheckGoodsAdapter(getContext(),
                listData, R.layout.item_focus_check_goods);
        adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
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


}
