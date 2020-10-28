package com.guyuan.dear.focus.quality.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.quality.adapter.KeyComponentAdapter;
import com.guyuan.dear.focus.quality.data.FocusQualityViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--质检--关键零部件质检
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class KeyComponentFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, FocusQualityViewModel> {

    public static final String TAG = KeyComponentFragment.class.getSimpleName();

    public static KeyComponentFragment newInstance() {

        Bundle args = new Bundle();

        KeyComponentFragment fragment = new KeyComponentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        KeyComponentAdapter keyComponentAdapter = new KeyComponentAdapter(getContext(),
                listData, R.layout.item_focus_key_component);
        adapter = new BaseRecyclerViewAdapter(keyComponentAdapter);
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
