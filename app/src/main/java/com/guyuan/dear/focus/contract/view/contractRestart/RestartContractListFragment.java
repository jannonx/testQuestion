package com.guyuan.dear.focus.contract.view.contractRestart;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentRestartContractListBinding;
import com.guyuan.dear.focus.contract.adapter.RestartedContractListAdapter;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 15:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartContractListFragment extends BaseMvvmFragment<FragmentRestartContractListBinding,RestartContractListViewModel> {

    public static RestartContractListFragment getInstance(){
        return new RestartContractListFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_restart_contract_list_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        RestartContractListViewModel viewModel = getViewModel();
        viewModel.setOnItemClickListener(new RestartedContractListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RestartedContractBean item, int pos) {
                //todo
                showToastTip("待开发");
            }
        });
        viewModel.getRestartedContractListFromNet();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_restart_contract_list;
    }
}
