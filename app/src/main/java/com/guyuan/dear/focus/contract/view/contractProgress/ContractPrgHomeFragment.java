package com.guyuan.dear.focus.contract.view.contractProgress;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractPrgHomeBinding;
import com.guyuan.dear.focus.contract.adapter.ContractPrgHomeListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同订单进度
 * @since: 2020/10/12 16:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgHomeFragment extends BaseMvvmFragment<FragmentContractPrgHomeBinding,ContractPrgHomeViewModel> {
    private List<BaseContractBean> mList=new ArrayList<>();
    private BaseRecyclerViewAdapter mAdapterWrapper;
    private int pageIndex=0;
    private static final int PAGE_SIZE=20;

    public static ContractPrgHomeFragment getInstance(){
        return new ContractPrgHomeFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_prg_home_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        ContractPrgHomeListAdapter adapter = new ContractPrgHomeListAdapter(getContext(),mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentContractPrgHomeRecyclerView;
        recyclerView.setLayoutManager(layoutManager);
        mAdapterWrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(mAdapterWrapper);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getViewModel().loadContractListFromNet(pageIndex++,PAGE_SIZE);
                        recyclerView.refreshComplete(PAGE_SIZE);
                    }
                },2000);

            }
        });
        mAdapterWrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                BaseContractBean bean = mList.get(i);
                ContractPrgDetailActivity.start(getActivity(),"合同订单进度详情",bean.getContractNum());
            }
        });
        getViewModel().getContractList().observe(getViewLifecycleOwner(), new Observer<List<BaseContractBean>>() {
            @Override
            public void onChanged(List<BaseContractBean> baseContractBeans) {
                mList.clear();
                mList.addAll(baseContractBeans);
                mAdapterWrapper.notifyDataSetChanged();
            }
        });
        getViewModel().loadContractListFromNet(pageIndex++, PAGE_SIZE);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_prg_home;
    }
}
