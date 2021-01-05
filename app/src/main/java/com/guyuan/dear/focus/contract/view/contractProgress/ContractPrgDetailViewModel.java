package com.guyuan.dear.focus.contract.view.contractProgress;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.adapter.ContractHistoryTypeWrapper;
import com.guyuan.dear.focus.contract.bean.ClientAcceptanceHistory;
import com.guyuan.dear.focus.contract.bean.ClientPaymentHistory;
import com.guyuan.dear.focus.contract.bean.ContractPauseHistory;
import com.guyuan.dear.focus.contract.bean.ContractRestartHistory;
import com.guyuan.dear.focus.contract.bean.ContractStatusFlow;
import com.guyuan.dear.focus.contract.bean.DepositSubmitHistory;
import com.guyuan.dear.focus.contract.bean.NoDepositPrgLog;
import com.guyuan.dear.focus.contract.repos.ContractPrgDetailRepo;
import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;
import com.tencent.bugly.proguard.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/12 18:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailViewModel extends BaseDearViewModel {
    private ContractPrgDetailRepo repo = new ContractPrgDetailRepo();
    private NetContractStatusFlow temp;
    private MutableLiveData<ContractStatusFlow> detailBean = new MutableLiveData<>();
    public MutableLiveData<ContractStatusFlow> getDetailBean() {
        return detailBean;
    }
    public MutableLiveData<List<ContractHistoryTypeWrapper>> contractHistories = new MutableLiveData<>();


    public void loadStatusFlowFromNet(int contractId) {
        Disposable disposable = repo.getContractStatusFlowById(contractId, new BaseNetCallback<NetContractStatusFlow>() {
            @Override
            protected void handleResult(NetContractStatusFlow result) {
                if (result != null) {
                    temp = result;
                    loadHistoryFromNet(contractId);
                } else {
                    showToast("服务器无节点数据，请联系后台人员。");
                }
            }
        });
        addSubscription(disposable);
    }

    private void loadHistoryFromNet(int contractId) {
        Disposable disposable = repo.getContractHistoryById(contractId, new BaseNetCallback<NetContractHistory>() {
            @Override
            protected void handleResult(NetContractHistory result) {
                if(result!=null){
                    ContractStatusFlow flow = new ContractStatusFlow(temp,result);
                    detailBean.postValue(flow);
                    updateContractHistories(flow);
                }else {
                    showToast("服务器无历史数据，请联系后台人员。");
                }
            }
        });
        addSubscription(disposable);
    }

    private void updateContractHistories(ContractStatusFlow flow) {
        List<ContractHistoryTypeWrapper> result = new ArrayList<>();

        NoDepositPrgLog noDepositPrgLog = flow.getNoDepositPrgLog();
        if(noDepositPrgLog!=null){
            ContractHistoryTypeWrapper wrapper = new ContractHistoryTypeWrapper();
            wrapper.setDate(noDepositPrgLog.getDate());
            wrapper.setType(ContractHistoryTypeWrapper.TYPE_DEPOSIT_NOT_RECEIVED);
            wrapper.setJsonString(new Gson().toJson(noDepositPrgLog));
            result.add(wrapper);
        }

        DepositSubmitHistory depositSubmitHistory = flow.getDepositSubmitHistory();
        if(depositSubmitHistory!=null){
            ContractHistoryTypeWrapper wrapper = new ContractHistoryTypeWrapper();
            wrapper.setDate(depositSubmitHistory.getDate());
            wrapper.setType(ContractHistoryTypeWrapper.TYPE_DEPOSIT_RECEIVED);
            wrapper.setJsonString(new Gson().toJson(depositSubmitHistory));
            result.add(wrapper);
        }

        ClientPaymentHistory clientPaymentHistory = flow.getClientPaymentHistory();
        if(clientPaymentHistory!=null){
            ContractHistoryTypeWrapper wrapper = new ContractHistoryTypeWrapper();
            wrapper.setDate(clientPaymentHistory.getDate());
            wrapper.setType(ContractHistoryTypeWrapper.TYPE_CLIENT_PAYMENT_RECEIVED);
            wrapper.setJsonString(new Gson().toJson(clientPaymentHistory));
            result.add(wrapper);
        }

        List<ContractPauseHistory> contractPauseHistories = flow.getContractPauseHistories();
        if(contractPauseHistories!=null&&!contractPauseHistories.isEmpty()){
            for (ContractPauseHistory temp : contractPauseHistories) {
                ContractHistoryTypeWrapper wrapper = new ContractHistoryTypeWrapper();
                wrapper.setDate(temp.getDate());
                wrapper.setType(ContractHistoryTypeWrapper.TYPE_CONTRACT_PAUSE);
                wrapper.setJsonString(new Gson().toJson(temp));
                result.add(wrapper);
            }
        }

        List<ContractRestartHistory> restartHistoryList = flow.getContractRestartHistories();
        if(restartHistoryList!=null&&!restartHistoryList.isEmpty()){
            for (ContractRestartHistory temp : restartHistoryList) {
                ContractHistoryTypeWrapper wrapper = new ContractHistoryTypeWrapper();
                wrapper.setDate(temp.getDate());
                wrapper.setType(ContractHistoryTypeWrapper.TYPE_CONTRACT_RESTART);
                wrapper.setJsonString(new Gson().toJson(temp));
                result.add(wrapper);
            }
        }

        List<ClientAcceptanceHistory> acceptanceHistoryList = flow.getClientAcceptanceHistories();
        if(acceptanceHistoryList!=null&&!acceptanceHistoryList.isEmpty()){
            for (ClientAcceptanceHistory temp : acceptanceHistoryList) {
                ContractHistoryTypeWrapper wrapper = new ContractHistoryTypeWrapper();
                wrapper.setDate(temp.getDate());
                wrapper.setType(ContractHistoryTypeWrapper.TYPE_CLIENT_TAKE_DELIVERY);
                wrapper.setJsonString(new Gson().toJson(temp));
                result.add(wrapper);
            }
        }

        Collections.sort(result, new Comparator<ContractHistoryTypeWrapper>() {
            @Override
            public int compare(ContractHistoryTypeWrapper o1, ContractHistoryTypeWrapper o2) {
                return (int) (o2.getDate()-o1.getDate());
            }
        });

        contractHistories.postValue(result);
    }


}
