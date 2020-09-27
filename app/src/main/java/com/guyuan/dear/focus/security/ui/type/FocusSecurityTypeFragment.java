package com.guyuan.dear.focus.security.ui.type;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.security.adapter.SecurityContentAdapter;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;
import com.guyuan.dear.focus.security.data.beans.SecurityBaseBean;
import com.guyuan.dear.focus.security.data.beans.SecurityContentBean;
import com.guyuan.dear.focus.security.ui.FocusSecurityActivity;
import com.guyuan.dear.focus.security.ui.detail.FocusSecurityDetailActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 14:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityTypeFragment extends BaseListFragment<SecurityBaseBean, FragmentListBinding> {

    public static final String TAG = "FocusSecurityTypeFragment";
    private FocusSecurityViewModel viewModel;

    public static FocusSecurityTypeFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_ID, id);
        FocusSecurityTypeFragment fragment = new FocusSecurityTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FocusSecurityActivity activity = (FocusSecurityActivity) context;
        viewModel = activity.getViewModel();
    }

    @Override
    protected void initView() {
        int id = 0;
        if (getArguments() != null) {
            id = getArguments().getInt(ConstantValue.KEY_ID);
        }

        SecurityContentAdapter contentAdapter =
                new SecurityContentAdapter(getContext(), listData, R.layout.item_focus_content);

        adapter = new BaseRecyclerViewAdapter(contentAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                SecurityContentBean contentBean = new SecurityContentBean();
                contentBean.setTSecurityBaseVo(listData.get(i));
                FocusSecurityDetailActivity.starter(getContext(), contentBean,
                        FocusSecurityDetailActivity.SECURITY_SEARCH);
            }
        });
        recycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recycleView.setAdapter(adapter);
        recycleView.setPullRefreshEnabled(false);
        recycleView.setLoadMoreEnabled(false);

        ListRequestBody requestBody = new ListRequestBody();
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setSecurityType(id);
        requestBody.setFilters(filtersBean);
        requestBody.setPageNum(1);
        requestBody.setPageSize(100);

        viewModel.getDangerPointTypeList(CommonUtils.getCommonRequestBody(requestBody));
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
