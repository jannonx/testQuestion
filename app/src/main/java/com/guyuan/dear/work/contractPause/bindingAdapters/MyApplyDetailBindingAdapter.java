package com.guyuan.dear.work.contractPause.bindingAdapters;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.work.contractPause.adapters.MyApplyHistoryLogsAdapter;
import com.guyuan.dear.work.contractPause.adapters.PauseApplyCommentAdapter;
import com.guyuan.dear.work.contractPause.adapters.VoteAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.GenericApproveLog;

import java.util.Iterator;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 17:20
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyDetailBindingAdapter {

    @BindingAdapter("setPauseApplyToBeProcessedSendList")
    public static void setPauseApplyToBeProcessedSendList(AppCompatTextView view, List<StaffBean> sendList) {
        if (sendList == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<StaffBean> iterator = sendList.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getName());
            if (iterator.hasNext()) {
                sb.append("、");
            }
        }
        view.setText(sb.toString() + "待审批");
    }

    @BindingAdapter("setPauseApplyVotingList")
    public static void setPauseApplyVotingList(RecyclerView view, List<Vote> data) {
        if (data == null) {
            return;
        }
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 4, RecyclerView.VERTICAL, false);
        VoteAdapter adapter = new VoteAdapter(data, view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }

    @BindingAdapter("setPauseApplyCommentList")
    public static void setPauseApplyCommentList(RecyclerView view, List<MeetingComment> data) {
        if (data == null) {
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        PauseApplyCommentAdapter adapter = new PauseApplyCommentAdapter(data, view.getContext());
        view.setLayoutManager(manager);
        view.setAdapter(adapter);
    }

    @BindingAdapter("setVoteResultIcon")
    public static void setVoteResultIcon(AppCompatImageView view, int result) {
        switch (result) {
            case Vote.VOTE_RESULT_PASS:
                view.setImageResource(R.drawable.ic_svg_check_circle_green_15);
                break;
            case Vote.VOTE_RESULT_DEFAULT:
                break;
            case Vote.VOTE_RESULT_FORFEIT:
                break;
            case Vote.VOTE_RESULT_REJECT:
                view.setImageResource(R.drawable.ic_svg_delete_red_circle);
                break;
            default:
                break;
        }

    }

    @BindingAdapter("setMyPauseApplyLogs")
    public static void setMyPauseApplyLogs(RecyclerView view, List<GenericApproveLog> data) {
        if (data == null) {
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, true);
        MyApplyHistoryLogsAdapter adapter = new MyApplyHistoryLogsAdapter(data, view.getContext());
        view.setLayoutManager(manager);
        view.setAdapter(adapter);
    }

    @BindingAdapter("showContractPauseReason")
    public static void showContractPauseReason(AppCompatTextView view, String reason) {
        StringBuilder sb = new StringBuilder();
        sb.append("暂停原因：");
        if (!TextUtils.isEmpty(reason)) {
            sb.append(reason);
        }
        view.setText(sb.toString());
    }

    @BindingAdapter("displayRestartApplyHeader")
    public static void displayRestartApplyHeader(View headerContainer, int approveResult) {
        //状态 0.审批中 1.已同意 2.已拒绝
        if (approveResult == 1) {
            headerContainer.setVisibility(View.VISIBLE);
        } else {
            headerContainer.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("displayPauseApplyHeader")
    public static void displayPauseApplyHeader(View headerContainer, int approveResult) {
        //状态 0.审批中 1.已同意 2.已拒绝
        if (approveResult == 1) {
            headerContainer.setVisibility(View.VISIBLE);
        } else {
            headerContainer.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("displayRestartApplySubmitButton")
    public static void displayRestartApplySubmitButton(View view, int approveResult) {
        //状态 0.审批中 1.已同意 2.已拒绝
        if (approveResult == 2) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("setMyApplyDetailTagTextProperty")
    public static void setMyApplyDetailTagTextProperty(AppCompatTextView view, int state) {
        //状态 0.审批中 1.已同意 2.已拒绝
        switch (state) {
            case 1:
                view.setText("已通过");
                view.setTextColor(Color.parseColor("#00B578"));
                view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
                break;
            case 0:
                view.setText("待审批");
                view.setTextColor(Color.parseColor("#1677FF"));
                view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
                break;
            case 2:
                view.setText("驳回");
                view.setTextColor(Color.parseColor("#FF6010"));
                view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);
                break;
            default:
                break;
        }
    }
}
