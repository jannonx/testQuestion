package com.guyuan.dear.focus.security.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentFocusSecurityNumberBinding;
import com.guyuan.dear.focus.security.adapter.SecurityNumberAdapter;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;
import com.guyuan.dear.focus.security.data.beans.DangerNumberBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 17:52
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityNumberFragment extends BaseListFragment<DangerNumberBean.ListBean, FragmentFocusSecurityNumberBinding,FocusSecurityViewModel> {

    public static final String TAG = "FocusSecurityNumberFragment";
    private FocusSecurityViewModel viewModel;

    public static FocusSecurityNumberFragment newInstance() {

        Bundle args = new Bundle();

        FocusSecurityNumberFragment fragment = new FocusSecurityNumberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_focus_security_number;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FocusSecurityActivity activity = (FocusSecurityActivity) context;
        viewModel = activity.getViewModel();
    }

    @Override
    protected void initView() {
        SecurityNumberAdapter securityNumberAdapter = new SecurityNumberAdapter(getContext(),
                listData, R.layout.item_focus_security_number);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(securityNumberAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                DangerNumberBean.ListBean bean = listData.get(i);

            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        if (viewModel != null) {
            viewModel.getDangerPointNumber();
        }
    }

    public void setUI(DangerNumberBean bean) {
        List<DangerNumberBean.SumListBean> numberList = bean.getSumList();
        if (numberList != null && numberList.size() > 0) {
            for (DangerNumberBean.SumListBean sumListBean : numberList) {
                if (sumListBean.getType_id() == -2) {//危险点类型数量
                    binding.securityDangerTotalTypeNameTv.setText(sumListBean.getName());
                    binding.securityDangerTotalTypeNumber.setText(sumListBean.getNum() + "");
                } else if (sumListBean.getType_id() == 0) {//危险点总数
                    binding.securityDangerTotalNameTv.setText(sumListBean.getName());
                    binding.securityDangerTotalNumber.setText(sumListBean.getNum() + "");
                }
            }
        }

        if (bean.getList() != null) {
            setListData(bean.getList());
        }
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
