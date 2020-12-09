package com.guyuan.dear.focus.contract.view.contractStatusList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.contractSearchBar.ContractSearchBar;
import com.guyuan.dear.databinding.FragmentContractStatusListBinding;
import com.guyuan.dear.databinding.LayoutNoDataBinding;
import com.guyuan.dear.focus.contract.adapter.ContractExceptionOrTotalAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractPauseListAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractRestartedListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.focus.contract.bean.ContractBean;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;
import com.guyuan.dear.focus.contract.view.contractApplyDetail.ContractApplyDetailActivity;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;
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
     * 异常类型
     */

    public static final int STATUS_TYPE_EXCEPTION = 6;

    /**
     * 全部类型
     */

    public static final int STATUS_TYPE_TOTAL = 0;

    private int currentExceptionPageIndex = 1;
    private int currentTotalPageIndex = 1;
    private MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(true);
    private LayoutNoDataBinding mNoDataSign;

    /**
     * @param statusType 只有{@link ContractStatusListFragment#STATUS_TYPE_EXCEPTION},{@link ContractStatusListFragment#STATUS_TYPE_TOTAL}
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
        mNoDataSign = getViewDataBinding().fragmentContractStatusListLayoutNoData;
        ContractSearchBar searchBar = getViewDataBinding().fragmentContractStatusListContractSearchBar;
        switch (statusType) {
            case STATUS_TYPE_ON_PAUSE:
                initPauseList();
                break;
            case STATUS_TYPE_RESTART:
                initRestartList();
                break;
            case STATUS_TYPE_TOTAL:
                initTotal();
                searchBar.setSearchType(ContractStatusListFragment.STATUS_TYPE_TOTAL);
                break;
            case STATUS_TYPE_EXCEPTION:
                initException();
                searchBar.setSearchType(ContractStatusListFragment.STATUS_TYPE_EXCEPTION);
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
                if (!beans.isEmpty()) {
                    shouldShowNoData.postValue(false);
                }
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
                if (!baseContractExcptBeans.isEmpty()) {
                    shouldShowNoData.postValue(false);
                }
                wrapper.notifyDataSetChanged();
            }
        });
        getViewModel().getPauseContractsFromNet();
    }

    private void initException() {
        BaseRecyclerView view = getViewDataBinding().fragmentContractStatusListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ContractExceptionOrTotalAdapter adapter = new ContractExceptionOrTotalAdapter(getViewModel().
                getExceptionContractList().getValue(), R.layout.item_contract_exception_or_total);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(true);
        view.setPullRefreshEnabled(false);
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ContractBean.ContentBean bean = getViewModel().getExceptionContractList().getValue().get(i);
                ContractDetailActivity.start(getActivity(), "合同异常详情", bean.getId());
            }
        });
        view.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().getExceptionContractList(STATUS_TYPE_EXCEPTION, ++currentExceptionPageIndex, "");
            }
        });
        getViewModel().getIsAllExceptionListLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                view.setLoadMoreEnabled(!aBoolean);
            }
        });
        getViewModel().getExceptionContractList().observe(getViewLifecycleOwner(), new Observer<List<ContractBean.ContentBean>>() {
            @Override
            public void onChanged(List<ContractBean.ContentBean> baseContractExcptBeans) {
                if (!baseContractExcptBeans.isEmpty()) {
                    shouldShowNoData.postValue(false);
                }
                wrapper.notifyDataSetChanged();
            }
        });
        getViewModel().getExceptionContractList(STATUS_TYPE_EXCEPTION, currentExceptionPageIndex, "");
    }

    private void initTotal() {
        BaseRecyclerView view = getViewDataBinding().fragmentContractStatusListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ContractExceptionOrTotalAdapter adapter = new ContractExceptionOrTotalAdapter(getViewModel().getTotalContractList()
                .getValue(), R.layout.item_contract_exception_or_total);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(true);
        view.setPullRefreshEnabled(false);
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ContractBean.ContentBean bean = getViewModel().getTotalContractList().getValue().get(i);
                ContractDetailActivity.start(getActivity(), "合同详情", bean.getId());
            }
        });
        view.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().getTotalContractList(STATUS_TYPE_TOTAL, ++currentTotalPageIndex, "");
            }
        });
        getViewModel().getIsAllTotalListLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                view.setLoadMoreEnabled(!aBoolean);
            }
        });
        getViewModel().getTotalContractList().observe(getViewLifecycleOwner(), new Observer<List<ContractBean.ContentBean>>() {
            @Override
            public void onChanged(List<ContractBean.ContentBean> baseContractExcptBeans) {
                if (!baseContractExcptBeans.isEmpty()) {
                    shouldShowNoData.postValue(false);
                }
                adapter.notifyDataSetChanged();
            }
        });
        getViewModel().getTotalContractList(STATUS_TYPE_TOTAL, currentTotalPageIndex, "");
    }


    @Override
    protected void initListeners() {
        shouldShowNoData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mNoDataSign.setIsShow(aBoolean);
            }
        });


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_status_list;
    }
}
