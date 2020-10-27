package com.guyuan.dear.focus.sales.bean.contractPrgLog;

import java.util.List;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 17:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SalesReviewMeeting {
    private long meetingDate;
    private int meetingIndex;
    private int meetingResult;
    private List<Vote> votes;
    private String dept;
    private List<MeetingComment> comments;
    @Transient
    public static final int MEETING_RESULT_APPROVED=1;
    @Transient
    public static final int MEETING_RESULT_REJECT=0;

    public long getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(long meetingDate) {
        this.meetingDate = meetingDate;
    }

    public int getMeetingIndex() {
        return meetingIndex;
    }

    public void setMeetingIndex(int meetingIndex) {
        this.meetingIndex = meetingIndex;
    }

    public int getMeetingResult() {
        return meetingResult;
    }

    public void setMeetingResult(int meetingResult) {
        this.meetingResult = meetingResult;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<MeetingComment> getComments() {
        return comments;
    }

    public void setComments(List<MeetingComment> comments) {
        this.comments = comments;
    }
}
