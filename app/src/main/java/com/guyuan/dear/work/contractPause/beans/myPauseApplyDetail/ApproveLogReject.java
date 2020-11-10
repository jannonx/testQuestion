package com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:52
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ApproveLogReject {
    private long date;
    private List<Vote> voteList;
    private List<MeetingComment> comments;

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public List<MeetingComment> getComments() {
        return comments;
    }

    public void setComments(List<MeetingComment> comments) {
        this.comments = comments;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
