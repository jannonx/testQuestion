package com.guyuan.dear.work.contractPause.adapters;

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
import com.guyuan.dear.databinding.ItemMyPauseApplyLogApprovedBinding;
import com.guyuan.dear.databinding.ItemMyPauseApplyLogFirstCreateDateBinding;
import com.guyuan.dear.databinding.ItemMyPauseApplyLogProcessingBinding;
import com.guyuan.dear.databinding.ItemMyPauseApplyLogRejectBinding;
import com.guyuan.dear.databinding.ItemMyPauseApplyLogToBeProcBinding;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogApproved;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogFirstCreateDate;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogProcessing;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogReject;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogToBePrc;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.GenericApproveLog;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyHistoryLogsAdapter extends BaseRecyclerView.Adapter<MyApplyHistoryLogsAdapter.ViewHolder> {
    private List<GenericApproveLog> list;
    private Context context;

    public MyApplyHistoryLogsAdapter(List<GenericApproveLog> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        GenericApproveLog log = list.get(position);
        return log.getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case GenericApproveLog.LOG_TYPE_FIRST_CREATE_DATE:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_my_pause_apply_log_first_create_date, parent, false);
                break;
            case GenericApproveLog.LOG_TYPE_TO_BE_PROCESS:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_my_pause_apply_log_to_be_proc, parent, false);
                break;
            case GenericApproveLog.LOG_TYPE_PROCESSING:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_my_pause_apply_log_processing, parent, false);
                break;
            case GenericApproveLog.LOG_TYPE_REJECT:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_my_pause_apply_log_reject, parent, false);
                break;
            case GenericApproveLog.LOG_TYPE_APPROVED:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_my_pause_apply_log_approved, parent, false);
                break;
            default:
                break;
        }
        if (binding != null) {
            return new ViewHolder(binding.getRoot());
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        GenericApproveLog log = list.get(position);
        Gson gson = new Gson();
        int viewType = getItemViewType(position);
        switch (viewType) {
            case GenericApproveLog.LOG_TYPE_APPROVED:
                ApproveLogApproved approved = gson.fromJson(log.getJson(),ApproveLogApproved.class);
                ItemMyPauseApplyLogApprovedBinding approvedBinding = (ItemMyPauseApplyLogApprovedBinding) binding;
                approvedBinding.setData(approved);
                break;
            case GenericApproveLog.LOG_TYPE_FIRST_CREATE_DATE:
                ApproveLogFirstCreateDate firstCreateDate = gson.fromJson(log.getJson(),ApproveLogFirstCreateDate.class);
                ItemMyPauseApplyLogFirstCreateDateBinding firstCreateDateBinding= (ItemMyPauseApplyLogFirstCreateDateBinding) binding;
                firstCreateDateBinding.setData(firstCreateDate);
                break;
            case GenericApproveLog.LOG_TYPE_PROCESSING:
                ApproveLogProcessing processing = gson.fromJson(log.getJson(),ApproveLogProcessing.class);
                ItemMyPauseApplyLogProcessingBinding processingBinding = (ItemMyPauseApplyLogProcessingBinding) binding;
                processingBinding.setData(processing);
                break;
            case GenericApproveLog.LOG_TYPE_REJECT:
                ApproveLogReject reject = gson.fromJson(log.getJson(),ApproveLogReject.class);
                ItemMyPauseApplyLogRejectBinding rejectBinding = (ItemMyPauseApplyLogRejectBinding) binding;
                rejectBinding.setData(reject);
                break;
            case GenericApproveLog.LOG_TYPE_TO_BE_PROCESS:
                ApproveLogToBePrc toBePrc = gson.fromJson(log.getJson(),ApproveLogToBePrc.class);
                ItemMyPauseApplyLogToBeProcBinding beProcBinding = (ItemMyPauseApplyLogToBeProcBinding) binding;
                beProcBinding.setData(toBePrc);
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
