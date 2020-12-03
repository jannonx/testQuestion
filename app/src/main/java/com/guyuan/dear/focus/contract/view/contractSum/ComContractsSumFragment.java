package com.guyuan.dear.focus.contract.view.contractSum;

import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentCompanyContractsSummaryBinding;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractList.ContractListActivity;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import io.reactivex.disposables.Disposable;

/**
 * 我的关注-销售首页-合同订单概况
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 15:49
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractsSumFragment extends BaseMvvmFragment<FragmentCompanyContractsSummaryBinding, ComContractsSumViewModel> {

    public static ComContractsSumFragment getInstance() {
        return new ComContractsSumFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contact_summary_vm;
    }

    @Override
    protected void initData() {
        Disposable disposable = getViewModel().updateSummary(System.currentTimeMillis());
        addDisposable(disposable);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        final ComContractsSumViewModel viewModel = getViewModel();
        viewModel.setOnClickSelectDate(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentDate = System.currentTimeMillis();
                long minDate = CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(currentDate, 60);
                long maxDate = currentDate;
                AlertDialogUtils.pickTime(
                        getParentFragmentManager(),
                        "请选择年份",
                        minDate,
                        maxDate,
                        currentDate,
                        Type.YEAR,
                        new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                viewModel.updateSummary(millseconds);
                            }
                        }
                );
            }
        });
        viewModel.setOnClickShowPreAnnualDelivers(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractListActivity.start(getActivity(), "上年交付",
                        BaseContractBean.CONTRACT_TYPE_PRE_ANNUAL_DELIVERS,
                        getViewModel().getSelectDate().getValue());
            }
        });

        viewModel.setOnClickShowNewContracts(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractListActivity.start(getActivity(), "今年新签",
                        BaseContractBean.CONTRACT_TYPE_NEW_CONTRACTS,
                        getViewModel().getSelectDate().getValue());
            }
        });
        viewModel.setOnClickShowFinished(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractListActivity.start(getActivity(), "已经完成",
                        BaseContractBean.CONTRACT_TYPE_FINISHED_CONTRACTS,
                        getViewModel().getSelectDate().getValue());
            }
        });
        viewModel.setOnClickShowExecuting(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractListActivity.start(getActivity(), "正在执行",
                        BaseContractBean.CONTRACT_TYPE_EXECUTING_CONTRACTS,
                        getViewModel().getSelectDate().getValue());
            }
        });

        viewModel.setOnClickShowUnfinished(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractListActivity.start(getActivity(), "未完成",
                        BaseContractBean.CONTRACT_TYPE_UNFINISHED_CONTRACTS,
                        getViewModel().getSelectDate().getValue());
            }
        });

        viewModel.setOnClickShowExceptions(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractListActivity.start(getActivity(), "执行异常",
                        BaseContractBean.CONTRACT_TYPE_EXCEPTION_CONTRACTS,
                        getViewModel().getSelectDate().getValue());
            }
        });


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_company_contracts_summary;
    }
}
