package com.guyuan.dear.focus.contract.view.allContractList;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator2P0;
import com.guyuan.dear.databinding.FragmentAllContractListBinding;
import com.guyuan.dear.focus.contract.adapter.ContractListDataBindingAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/9 20:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AllContractListFragment extends BaseMvvmFragment<FragmentAllContractListBinding,AllContractListViewModel> {

    private BaseRecyclerViewAdapter wrapper;
    private BaseRecyclerView recyclerView;

    public static AllContractListFragment getInstance(){
        return new AllContractListFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_all_contract_list_vm;
    }

    @Override
    protected void initData() {
        getViewModel().loadNextPageFromNet();

    }

    @Override
    protected void initViews() {
        recyclerView = getViewDataBinding().fragmentAllContractListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ContractListDataBindingAdapter adapter = new ContractListDataBindingAdapter(getViewModel().contractList.getValue());
        wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator2P0(12,12,12,12,16));
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().loadNextPageFromNet();
            }
        });

    }

    @Override
    protected void initListeners() {
        getViewModel().shouldShowNoData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    getViewDataBinding().fragmentAllContractListNoData.setVisibility(View.VISIBLE);
                }
            }
        });

        getViewModel().isLoadAll.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    recyclerView.refreshComplete(0);
                    recyclerView.setLoadMoreEnabled(false);
                }
            }
        });

        getViewModel().shouldNotifyDataSetChange.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
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
                BaseContractBean bean = getViewModel().contractList.getValue().get(position);
                ContractDetailActivity.start(getActivity(), "合同详情", (int) bean.getContractId());
            }
        });


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_all_contract_list;
    }
}
