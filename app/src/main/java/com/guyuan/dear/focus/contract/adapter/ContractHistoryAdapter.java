package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemContractHistoryClientPaymentBinding;
import com.guyuan.dear.databinding.ItemContractHistoryClientTakeDeliveryBinding;
import com.guyuan.dear.databinding.ItemContractHistoryContractPauseBinding;
import com.guyuan.dear.databinding.ItemContractHistoryContractRestartedBinding;
import com.guyuan.dear.databinding.ItemContractHistoryDepositReceivedBinding;
import com.guyuan.dear.databinding.ItemContractHistoryDepositeNotReceivedBinding;
import com.guyuan.dear.focus.contract.bean.ClientAcceptanceHistory;
import com.guyuan.dear.focus.contract.bean.ClientPaymentHistory;
import com.guyuan.dear.focus.contract.bean.ContractPauseHistory;
import com.guyuan.dear.focus.contract.bean.ContractRestartHistory;
import com.guyuan.dear.focus.contract.bean.DepositSubmitHistory;
import com.guyuan.dear.focus.contract.bean.NoDepositPrgLog;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 17:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractHistoryAdapter extends RecyclerView.Adapter<ContractHistoryAdapter.ViewHolder> {

    private List<ContractHistoryTypeWrapper> list;
    private Context context;

    public ContractHistoryAdapter(List<ContractHistoryTypeWrapper> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = 0;
        switch (viewType) {
            case ContractHistoryTypeWrapper.TYPE_CLIENT_PAYMENT_RECEIVED:
                layout = R.layout.item_contract_history_client_payment;
                break;
            case ContractHistoryTypeWrapper.TYPE_CLIENT_TAKE_DELIVERY:
                layout = R.layout.item_contract_history_client_take_delivery;
                break;
            case ContractHistoryTypeWrapper.TYPE_CONTRACT_PAUSE:
                layout = R.layout.item_contract_history_contract_pause;
                break;
            case ContractHistoryTypeWrapper.TYPE_CONTRACT_RESTART:
                layout = R.layout.item_contract_history_contract_restarted;
                break;
            case ContractHistoryTypeWrapper.TYPE_DEPOSIT_NOT_RECEIVED:
                layout = R.layout.item_contract_history_deposite_not_received;
                break;
            case ContractHistoryTypeWrapper.TYPE_DEPOSIT_RECEIVED:
                layout = R.layout.item_contract_history_deposit_received;
                break;
            default:
                break;
        }
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layout, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
        ContractHistoryTypeWrapper bean = list.get(position);
        switch (viewType) {
            case ContractHistoryTypeWrapper.TYPE_CLIENT_PAYMENT_RECEIVED:
                ItemContractHistoryClientPaymentBinding clientPaymentBinding = (ItemContractHistoryClientPaymentBinding) dataBinding;
                ClientPaymentHistory clientPaymentHistory = new Gson().fromJson(bean.getJsonString(), ClientPaymentHistory.class);
                clientPaymentBinding.setData(clientPaymentHistory);
                break;
            case ContractHistoryTypeWrapper.TYPE_CLIENT_TAKE_DELIVERY:
                ItemContractHistoryClientTakeDeliveryBinding takeDeliveryBinding = (ItemContractHistoryClientTakeDeliveryBinding) dataBinding;
                ClientAcceptanceHistory acceptanceHistory = new Gson().fromJson(bean.getJsonString(),ClientAcceptanceHistory.class);
                takeDeliveryBinding.setData(acceptanceHistory);
                break;
            case ContractHistoryTypeWrapper.TYPE_CONTRACT_PAUSE:
                ItemContractHistoryContractPauseBinding pauseBinding = (ItemContractHistoryContractPauseBinding) dataBinding;
                ContractPauseHistory pauseHistory = new Gson().fromJson(bean.getJsonString(),ContractPauseHistory.class);
                pauseBinding.setData(pauseHistory);
                break;
            case ContractHistoryTypeWrapper.TYPE_CONTRACT_RESTART:
                ItemContractHistoryContractRestartedBinding restartedBinding = (ItemContractHistoryContractRestartedBinding) dataBinding;
                ContractRestartHistory restartHistory = new Gson().fromJson(bean.getJsonString(),ContractRestartHistory.class);
                restartedBinding.setData(restartHistory);
                break;
            case ContractHistoryTypeWrapper.TYPE_DEPOSIT_NOT_RECEIVED:
                ItemContractHistoryDepositeNotReceivedBinding depositeNotReceivedBinding = (ItemContractHistoryDepositeNotReceivedBinding) dataBinding;
                NoDepositPrgLog noDepositPrgLog = new Gson().fromJson(bean.getJsonString(),NoDepositPrgLog.class);
                depositeNotReceivedBinding.setData(noDepositPrgLog);
                break;
            case ContractHistoryTypeWrapper.TYPE_DEPOSIT_RECEIVED:
                ItemContractHistoryDepositReceivedBinding depositReceivedBinding = (ItemContractHistoryDepositReceivedBinding) dataBinding;
                DepositSubmitHistory depositSubmitHistory = new Gson().fromJson(bean.getJsonString(),DepositSubmitHistory.class);
                depositReceivedBinding.setData(depositSubmitHistory);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
