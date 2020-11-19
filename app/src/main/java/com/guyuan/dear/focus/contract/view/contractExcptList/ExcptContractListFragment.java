package com.guyuan.dear.focus.contract.view.contractExcptList;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractExcptListBinding;
import com.guyuan.dear.focus.contract.adapter.ContractExcptListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.focus.contract.view.contractExptDetail.ContractExcptDetailActivity;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ExcptContractListFragment extends BaseMvvmFragment<FragmentContractExcptListBinding,ContractExcptListViewModel> {

    public static ExcptContractListFragment getInstance(){
        return new ExcptContractListFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_except_list_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().setItemClickListener(new ContractExcptListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(BaseContractExcptBean item, int position) {
                ContractExcptDetailActivity.start(getActivity(),"合同异常详情",item.getContractNum());
            }
        });
        getViewModel().getExcptContractsFromNet();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_excpt_list;
    }
}
