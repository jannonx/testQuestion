package com.guyuan.dear.focus.contract.view.contractProgress;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractPrgDetailLogsBinding;
import com.guyuan.dear.focus.contract.adapter.contractPrgLog.LogTailAdapter;
import com.guyuan.dear.focus.contract.adapter.contractPrgLog.SalesReviewAdapter;
import com.guyuan.dear.focus.contract.bean.ContractLogBean;
import com.guyuan.dear.focus.contract.bean.ContractStatusFlowBean;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.FirstCreateDate;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.SalesReviewMeeting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同详情-历史记录
 * @since: 2020/10/14 18:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgLogsFragment extends BaseMvvmFragment<FragmentContractPrgDetailLogsBinding, ContractPrgDetailViewModel> {

    private List<SalesReviewMeeting> mSalesReviews = new ArrayList<>();
    private List<FirstCreateDate> mFirstCreateDate = new ArrayList<>();
    private ConcatAdapter concatAdapter;
    private SalesReviewAdapter salesReviewAdapter;
    private LogTailAdapter tailAdapter;

    public static ContractPrgLogsFragment getInstance() {
        ContractPrgLogsFragment fragment = new ContractPrgLogsFragment();
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_prg_detail_logs_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        RecyclerView recyclerView = getViewDataBinding().fragmentContractPrgDetailLogsRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        salesReviewAdapter = new SalesReviewAdapter(mSalesReviews, getContext());
        tailAdapter = new LogTailAdapter(getContext(), mFirstCreateDate);


        concatAdapter = new ConcatAdapter();
        concatAdapter.addAdapter(salesReviewAdapter);
        concatAdapter.addAdapter(tailAdapter);


        recyclerView.setAdapter(concatAdapter);


        ContractStatusFlowBean detailBean = getViewModel().getDetailBean().getValue();
        if (detailBean != null) {
            upDateViewByDate(detailBean);
        }
    }

    @Override
    protected void initListeners() {
        getViewModel().getDetailBean().observe(getViewLifecycleOwner(), new Observer<ContractStatusFlowBean>() {
            @Override
            public void onChanged(ContractStatusFlowBean contractStatusFlowBean) {
                mFirstCreateDate.clear();
                mSalesReviews.clear();
                upDateViewByDate(contractStatusFlowBean);
            }
        });
    }

    private void upDateViewByDate(ContractStatusFlowBean contractStatusFlowBean) {
//        List<ContractLogBean> logs = contractStatusFlowBean.getLogs();
//        for (ContractLogBean log : logs) {
//            int logType = log.getLogType();
//            String jsonString = log.getJsonString();
//            switch (logType) {
//                case ContractLogBean.LOG_TYPE_FIRST_CREATE_DATE:
//                    FirstCreateDate date = new Gson().fromJson(jsonString, FirstCreateDate.class);
//                    mFirstCreateDate.add(date);
//                    break;
//                case ContractLogBean.LOG_TYPE_SALES_REVIEW_MEETING:
//                    SalesReviewMeeting meeting = new Gson().fromJson(jsonString, SalesReviewMeeting.class);
//                    mSalesReviews.add(meeting);
//                    break;
//                default:
//                    break;
//            }
//        }
////        concatAdapter.notifyDataSetChanged();
//        salesReviewAdapter.notifyDataSetChanged();
//        tailAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_prg_detail_logs;
    }
}
