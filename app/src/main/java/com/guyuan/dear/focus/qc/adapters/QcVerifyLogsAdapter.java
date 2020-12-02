package com.guyuan.dear.focus.qc.adapters;

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
import com.guyuan.dear.databinding.ItemQcReportLogFirstCreateDateBinding;
import com.guyuan.dear.databinding.ItemQcReportLogPendingForApprovalBinding;
import com.guyuan.dear.databinding.ItemQcReportLogSubmitDetailBinding;
import com.guyuan.dear.databinding.ItemQcReportLogVerifyResultBinding;
import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeFirstCreateDate;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypePendingForVerify;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeSubmitDetail;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeVerifyResult;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 11:43
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcVerifyLogsAdapter extends RecyclerView.Adapter<QcVerifyLogsAdapter.ViewHolder> {

    private List<GenericQcLogBean> list;
    private Context context;

    public QcVerifyLogsAdapter(List<GenericQcLogBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getLogType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bindingView = null;
        int layoutId = -1;
        switch (viewType) {
            case GenericQcLogBean.LOG_TYPE_PENDING_FOR_VERIFY:
                layoutId = R.layout.item_qc_report_log_pending_for_approval;
                break;
            case GenericQcLogBean.LOG_TYPE_SUBMIT_DETAIL:
                layoutId = R.layout.item_qc_report_log_submit_detail;
                break;
            case GenericQcLogBean.LOG_TYPE_VERIFY_RESULT:
                layoutId = R.layout.item_qc_report_log_verify_result;
                break;
            case GenericQcLogBean.LOG_TYPE_FIRST_CREATE_DATE:
            default:
                layoutId = R.layout.item_qc_report_log_first_create_date;
                break;
        }
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
        bindingView = binding.getRoot();
        return new ViewHolder(bindingView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        GenericQcLogBean genericQcLogBean = list.get(position);
        Gson gson = new Gson();
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        switch (viewType) {
            case GenericQcLogBean.LOG_TYPE_PENDING_FOR_VERIFY:
                ItemQcReportLogPendingForApprovalBinding pendingBinding = (ItemQcReportLogPendingForApprovalBinding) binding;
                pendingBinding.setData(gson.fromJson(genericQcLogBean.getJsonString(), LogTypePendingForVerify.class));
                break;
            case GenericQcLogBean.LOG_TYPE_SUBMIT_DETAIL:
                ItemQcReportLogSubmitDetailBinding detailBinding = (ItemQcReportLogSubmitDetailBinding) binding;
                detailBinding.setData(gson.fromJson(genericQcLogBean.getJsonString(), LogTypeSubmitDetail.class));
                break;
            case GenericQcLogBean.LOG_TYPE_VERIFY_RESULT:
                ItemQcReportLogVerifyResultBinding resultBinding = (ItemQcReportLogVerifyResultBinding) binding;
                resultBinding.setData(gson.fromJson(genericQcLogBean.getJsonString(), LogTypeVerifyResult.class));
                break;
            case GenericQcLogBean.LOG_TYPE_FIRST_CREATE_DATE:
            default:
                ItemQcReportLogFirstCreateDateBinding createDateBinding = (ItemQcReportLogFirstCreateDateBinding) binding;
                createDateBinding.setData(gson.fromJson(genericQcLogBean.getJsonString(), LogTypeFirstCreateDate.class));
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
