package com.guyuan.dear.focus.sales.bindingadpaters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.sales.adapter.contractPrgLog.SalesCommentAdapter;
import com.guyuan.dear.focus.sales.adapter.contractPrgLog.SalesVoteAdapter;
import com.guyuan.dear.focus.sales.bean.contractPrgLog.MeetingComment;
import com.guyuan.dear.focus.sales.bean.contractPrgLog.Vote;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/15 15:35
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ItemSalesReviewBindingAdapter {
    @BindingAdapter("setMeetingVotes")
    public static void setMeetingVotes(RecyclerView view, List<Vote> votes){
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),4, LinearLayoutManager.VERTICAL,false);
        SalesVoteAdapter adapter = new SalesVoteAdapter(votes,view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }

    @BindingAdapter("setReviewComments")
    public static void setReviewComments(RecyclerView view, List<MeetingComment> comments){
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        view.setLayoutManager(layoutManager);
        SalesCommentAdapter adapter = new SalesCommentAdapter(view.getContext(),comments);
        view.setAdapter(adapter);
    }
}
