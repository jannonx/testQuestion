package com.guyuan.dear.focus.contract.view.exceptionList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator2P0;
import com.guyuan.dear.databinding.FragmentExceptionContractListBinding;
import com.guyuan.dear.databinding.FragmentExecutingContractListBinding;
import com.guyuan.dear.focus.contract.adapter.ExceptionContractListAdapter;
import com.guyuan.dear.focus.contract.adapter.LastYearDeliverListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;
import com.guyuan.dear.focus.contract.view.executingList.ExecutingListFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/4 15:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ExceptionContractListFragment extends BaseMvvmFragment<FragmentExceptionContractListBinding,ExceptionListViewModel> {
    private long date;
    private BaseRecyclerView recyclerView;
    private BaseRecyclerViewAdapter wrapper;
    private List<BaseContractBean> list = new ArrayList<>();

    public static ExceptionContractListFragment getInstance(long date){
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantValue.KEY_DATE,date);
        ExceptionContractListFragment fragment = new ExceptionContractListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_exception_contract_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        date = bundle.getLong(ConstantValue.KEY_DATE);
        getViewModel().loadContractListFromNet(date);

    }

    @Override
    protected void initViews() {
        recyclerView = getViewDataBinding().fragmentExceptionContractListRecyclerView;
        ExceptionContractListAdapter adapter = new ExceptionContractListAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator2P0());
        recyclerView.setAdapter(wrapper);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);

    }

    @Override
    protected void initListeners() {
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().loadContractListFromNet(date);
            }
        });
        getViewModel().contractList.observe(getViewLifecycleOwner(), new Observer<List<BaseContractBean>>() {
            @Override
            public void onChanged(List<BaseContractBean> baseContractBeans) {
                list.clear();
                list.addAll(baseContractBeans);
                wrapper.notifyDataSetChanged();
            }
        });
        getViewModel().isAllLoaded.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                recyclerView.setLoadMoreEnabled(!aBoolean);
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BaseContractBean item = list.get(position);
                ContractDetailActivity.start(getContext(), "合同详情", (int) item.getContractId());
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_exception_contract_list;
    }
}
