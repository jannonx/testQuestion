package com.guyuan.dear.focus.contract.view.contractSearchList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator;
import com.guyuan.dear.databinding.FragmentContractSearchListBinding;
import com.guyuan.dear.focus.contract.adapter.ContractListDataBindingAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractSearchListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

import static com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment.STATUS_TYPE_TOTAL;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/3 11:03
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractSearchListFragment extends BaseMvvmFragment<FragmentContractSearchListBinding, ContractSearchListViewModel> {

    private BaseRecyclerView recyclerView;
    private BaseRecyclerViewAdapter wrapper;
    private String searchContent;
    private int searchType;

    /**
     * @param searchType 只有以下情况
     * {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_EXCEPTION},
     * {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_TOTAL},
     * {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_ON_PAUSE},
     * {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_RESTART}
     */
    public static ContractSearchListFragment getInstance(String companyNameOrContractNo,int searchType) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.KEY_KEY_WORD, companyNameOrContractNo);
        bundle.putInt(ConstantValue.KEY_SEARCH_TYPE,searchType);
        ContractSearchListFragment fragment = new ContractSearchListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_search_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        searchContent = bundle.getString(ConstantValue.KEY_KEY_WORD);
        searchType = bundle.getInt(ConstantValue.KEY_SEARCH_TYPE,STATUS_TYPE_TOTAL);
        getViewModel().getContractListByComNameOrContractNo(searchContent,searchType);

    }

    @Override
    protected void initViews() {
        recyclerView = getViewDataBinding().fragmentContractSearchListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ContractListDataBindingAdapter adapter = new ContractListDataBindingAdapter(getViewModel().contractList.getValue());
        wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator(12, 16));
        recyclerView.setAdapter(wrapper);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().getContractListByComNameOrContractNo(searchContent,searchType);
            }
        });
    }

    @Override
    protected void initListeners() {
        getViewModel().isLoadAll.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    recyclerView.setLoadMoreEnabled(false);
                }

            }
        });

        getViewModel().shouldNotifyDateSetChange.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    recyclerView.refreshComplete(0);
                    wrapper.notifyDataSetChanged();
                }
            }
        });

        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BaseContractBean contractBean = getViewModel().contractList.getValue().get(position);
                ContractDetailActivity.start(getActivity(), "合同详情", (int) contractBean.getContractId());
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_search_list;
    }
}
