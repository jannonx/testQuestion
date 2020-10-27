package com.guyuan.dear.focus.sales.view.contractDetail;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.sales.adapter.ContractBaseInfoAdapter;
import com.guyuan.dear.focus.sales.bean.ContractDetailBean;
import com.guyuan.dear.focus.sales.bean.ProductComponent;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同详情-销售产品
 * @since: 2020/10/10 12:16
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractBaseInfoFragment extends BaseFragment {
    private AppCompatTextView tvName;
    private AppCompatTextView tvModel;
    private BaseRecyclerView mRecyclerView;
    private List<ProductComponent> mComponentList;
    private BaseRecyclerViewAdapter adapterWrapper;


    public static ContractBaseInfoFragment getInstance(){
        return new ContractBaseInfoFragment();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_base_info;
    }

    @Override
    protected void initialization() {
        initView();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {
        tvName = rootView.findViewById(R.id.fragment_contract_base_info_tv_product_name);
        tvModel = rootView.findViewById(R.id.fragment_contract_base_info_tv_product_model);
        mRecyclerView = rootView.findViewById(R.id.fragment_contract_base_info_recycler_view);

        mComponentList = new ArrayList<>();
        ContractBaseInfoAdapter adapter = new ContractBaseInfoAdapter(getContext(),mComponentList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        adapterWrapper = new BaseRecyclerViewAdapter(adapter);
        mRecyclerView.setAdapter(adapterWrapper);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadMoreEnabled(false);

    }

    public void updateViewByData(ContractDetailBean data){
        tvName.setText(data.getProductName());
        tvModel.setText(data.getProductModel());
        mComponentList.clear();
        mComponentList.addAll(data.getProductComponents());
        adapterWrapper.notifyDataSetChanged();
    }
}
