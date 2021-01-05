package com.guyuan.dear.focus.contract.view.contractProgress;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentClientReceiptBinding;
import com.guyuan.dear.focus.contract.adapter.contractPrgLog.ClientReceiptAdapter;
import com.guyuan.dear.focus.contract.bean.ContractStatusFlow;
import com.guyuan.dear.focus.contract.bean.ImageSource;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.ClientReceipt;
import com.guyuan.dear.focus.contract.view.zoomView.ZoomViewActivity;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同详情-客户签收照片
 * @since: 2020/10/19 10:41
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClientReceiptFragment extends BaseMvvmFragment<FragmentClientReceiptBinding, ContractPrgDetailViewModel> {
    private List<ClientReceipt> list = new ArrayList<>();
    private BaseRecyclerViewAdapter wrapperAdapter;

    public static ClientReceiptFragment getInstance() {
        ClientReceiptFragment fragment = new ClientReceiptFragment();
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_prg_client_receipt_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        ClientReceiptAdapter adapter = new ClientReceiptAdapter(getContext(), list);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false);
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentContractPrgClientReceiptRecyclerView;
        recyclerView.setLayoutManager(layoutManager);
        wrapperAdapter = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(wrapperAdapter);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(false);


    }

    @Override
    protected void initListeners() {
        wrapperAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ArrayList<ImageSource> sources = new ArrayList<ImageSource>() {
                    {
                        addAll(list);
                    }
                };
                ZoomViewActivity.start(getContext(), "客户签收单据", sources, i);
            }
        });


        ContractStatusFlow value = getViewModel().getDetailBean().getValue();
        if (value != null) {
//            updateViewByData(value.getClientReceipts());
        }

        getViewModel().getDetailBean().observe(getViewLifecycleOwner(), new Observer<ContractStatusFlow>() {
            @Override
            public void onChanged(ContractStatusFlow contractStatusFlow) {
//                updateViewByData(contractStatusFlowBean.getClientReceipts());
            }
        });
    }

    private void updateViewByData(List<ClientReceipt> data) {
        list.clear();
        list.addAll(data);
        wrapperAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_client_receipt;
    }
}
