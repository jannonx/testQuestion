package com.guyuan.dear.focus.contract.view.contractList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractListBinding;
import com.guyuan.dear.focus.contract.adapter.ContractListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同列表
 * @since: 2020/10/12 10:40
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractListFragment extends BaseMvvmFragment<FragmentContractListBinding, ContractListViewModel> {

    private List<BaseContractBean> mList = new ArrayList<>();
    private BaseRecyclerViewAdapter mAdapterWrapper;
    private int contractType;
    private long date;

    /**
     *
     * @param contractType 参考
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_EXCEPTION_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_EXECUTING_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_FINISHED_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_NEW_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_PRE_ANNUAL_DELIVERS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_UNFINISHED_CONTRACTS}
     * @param date 合同截至日期
     * @return
     */
    public static ContractListFragment getInstance(int contractType,long date) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_CONTRACT_TYPE, contractType);
        bundle.putLong(ConstantValue.KEY_DATE,date);
        ContractListFragment fragment = new ContractListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        contractType = bundle.getInt(ConstantValue.KEY_CONTRACT_TYPE);
        date = bundle.getLong(ConstantValue.KEY_DATE);

    }

    @Override
    protected void initViews() {
        getViewModel().getContractList().observe(getViewLifecycleOwner(), new Observer<List<BaseContractBean>>() {
            @Override
            public void onChanged(List<BaseContractBean> baseContractBeans) {
                mList.clear();
                mList.addAll(baseContractBeans);
                mAdapterWrapper.notifyDataSetChanged();
            }
        });
        ContractListAdapter adapter = new ContractListAdapter(getContext(), mList);
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentContractListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapterWrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(mAdapterWrapper);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().loadContractListFromNet(contractType,date);
            }
        });

        mAdapterWrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                BaseContractBean bean = mList.get(i);
                ContractDetailActivity.start(getActivity(), "合同详情", bean.getContractNum());
            }
        });

        getViewModel().getIsAllLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                recyclerView.setLoadMoreEnabled(aBoolean);
            }
        });
        addDisposable(getViewModel().loadContractListFromNet(contractType,date));
    }

    @Override
    protected void initListeners() {


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_list;
    }
}
