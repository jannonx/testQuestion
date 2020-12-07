package com.guyuan.dear.focus.contract.view.contractDetail;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractCommentsBinding;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-销售概况-销售详情-跟进动态记录
 * @since: 2020/10/10 15:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractCommentsFragment extends BaseMvvmFragment<FragmentContractCommentsBinding, ContractCommentsViewModel> {

    public static ContractCommentsFragment getInstance() {
        return new ContractCommentsFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_comments_vm;
    }

    @Override
    protected void initData() {
        ContractDetailViewModel viewModel = new ViewModelProvider(getActivity()).get(ContractDetailViewModel.class);
        viewModel.getContractBean().observe(getViewLifecycleOwner(), new Observer<DetailContractBean>() {
            @Override
            public void onChanged(DetailContractBean detailContractBean) {
                getViewModel().applier.postValue(detailContractBean.getSalesPerson());
                getViewModel().contractDate.postValue(detailContractBean.getDate());
                List<ContractComment> commentList = detailContractBean.getCommentList();
                getViewModel().comments.postValue(commentList);
                getViewDataBinding().fragmentContractCommentsNoData.setIsShow(commentList.isEmpty());
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_comments;
    }


}
