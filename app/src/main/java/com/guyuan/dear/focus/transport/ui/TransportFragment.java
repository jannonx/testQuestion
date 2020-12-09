package com.guyuan.dear.focus.transport.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.transport.adapter.TransportAdapter;
import com.guyuan.dear.focus.transport.data.TransportViewModel;
import com.guyuan.dear.focus.transport.data.bean.TransportListBean;
import com.guyuan.dear.focus.transport.ui.detail.TransportDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:40
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportFragment extends BaseListSearchFragment<TransportListBean.ContentBean, FragmentListSearchBinding, TransportViewModel> {

    public static final int FOLLOW = 10;    //跟踪
    public static final int ARRIVED = 20;  //已到达

    private int type;

    public static TransportFragment newInstance(int type, String content) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_STATUS_TYPE, type);
        args.putString(ConstantValue.KEY_CONTENT, content);
        TransportFragment fragment = new TransportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            searchBar.setHint("输入项目名称、发货编号");
            type = getArguments().getInt(ConstantValue.KEY_STATUS_TYPE);
            searchContent = getArguments().getString(ConstantValue.KEY_CONTENT);
            TransportAdapter transportAdapter = new TransportAdapter(listData, R.layout.item_focus_transport);
            setDefaultAdapter(transportAdapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    if (listData.size() > 0) {
                        TransportListBean.ContentBean bean = listData.get(i);
                        TransportDetailActivity.start(getContext(), bean.getProjectName(), bean.getId());
                    }
                }
            });
            if (viewModel != null) {
                viewModel.getTransportList(type, currentPage, searchContent);
            }
        }
    }


    @Override
    protected void refresh() {
        currentType = REFRESH;
        currentPage = ConstantValue.FIRST_PAGE;
        viewModel.getTransportList(type, currentPage, searchContent);
    }

    @Override
    protected void loadMore() {
        viewModel.getTransportList(type, ++currentPage, searchContent);
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}