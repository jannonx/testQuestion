package com.guyuan.dear.work.contractPause.bindingAdapters;

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
}
