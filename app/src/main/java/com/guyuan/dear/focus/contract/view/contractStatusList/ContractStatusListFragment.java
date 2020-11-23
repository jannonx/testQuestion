package com.guyuan.dear.focus.contract.view.contractStatusList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractStatusListBinding;
import com.guyuan.dear.focus.contract.adapter.ContractPauseListAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractRestartedListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;
import com.guyuan.dear.focus.contract.view.contractApplyDetail.ContractApplyDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractStatusListFragment extends BaseMvvmFragment<FragmentContractStatusListBinding, ContractStatusListViewModel> {

    private int statusType;

    /**
     * 暂停类型
     */
    public static final int STATUS_TYPE_ON_PAUSE = 1;
    /**
     * 重启类型
     */
    public static final int STATUS_TYPE_RESTART = 2;


    /**
     * @param statusType {@link ContractStatusListFragment#STATUS_TYPE_ON_PAUSE} {@link ContractStatusListFragment#STATUS_TYPE_RESTART}
     * @return
     */
    public static ContractStatusListFragment getInstance(int statusType) {
        ContractStatusListFragment fragment = new ContractStatusListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_STATUS_TYPE, statusType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_status_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        statusType = bundle.getInt(ConstantValue.KEY_STATUS_TYPE);

    }

    @Override
    protected void initViews() {
        switch (statusType) {
            case STATUS_TYPE_ON_PAUSE:
                initPauseList();
                break;
            case STATUS_TYPE_RESTART:
                initRestartList();
                break;
            default:
                break;
        }
    }

    private void initRestartList() {
        BaseRecyclerView view = getViewDataBinding().fragmentContractStatusListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ContractRestartedListAdapter adapter = new ContractRestartedListAdapter(getViewModel().getRestartContractList().getValue());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(true);
        view.setPullRefreshEnabled(false);
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                List<RestartedContractBean> value = getViewModel().getRestartContractList().getValue();
                RestartedContractBean bean = value.get(i);
                ContractApplyDetailActivity.start(getActivity(), "合同重启详情", bean.getExamineId());
            }
        });
        view.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().getRestartedContractFromNet();
            }
        });
        getViewModel().getIsAllRestartListLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                view.setLoadMoreEnabled(!aBoolean);
            }
        });
        getViewModel().getRestartContractList().observe(getViewLifecycleOwner(), new Observer<List<RestartedContractBean>>() {
            @Override
            public void onChanged(List<RestartedContractBean> beans) {
                wrapper.notifyDataSetChanged();
            }
        });

        getViewModel().getRestartedContractFromNet();
    }

    private void initPauseList() {
        BaseRecyclerView view = getViewDataBinding().fragmentContractStatusListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ContractPauseListAdapter adapter = new ContractPauseListAdapter(getViewModel().getPauseContractList().getValue());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(true);
        view.setPullRefreshEnabled(false);
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                BaseContractExcptBean bean = getViewModel().getPauseContractList().getValue().get(i);
                ContractApplyDetailActivity.start(getActivity(), "合同异常详情", bean.getExamineId());
            }
        });
        view.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().getPauseContractsFromNet();
            }
        });
        getViewModel().getIsAllPauseListLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                view.setLoadMoreEnabled(!aBoolean);
            }
        });
        getViewModel().getPauseContractList().observe(getViewLifecycleOwner(), new Observer<List<BaseContractExcptBean>>() {
            @Override
            public void onChanged(List<BaseContractExcptBean> baseContractExcptBeans) {
                wrapper.notifyDataSetChanged();
            }
        });
        getViewModel().getPauseContractsFromNet();
    }

    @Override
    protected void initListeners() {


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_status_list;
    }
}
