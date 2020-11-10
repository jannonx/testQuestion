package com.guyuan.dear.work.contractPause.views.applyDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.gson.Gson;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogApproved;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogFirstCreateDate;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogProcessing;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogReject;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogToBePrc;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.GenericApproveLog;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.MyPauseApplyDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyDetailViewModel extends BaseViewModel {
    private MutableLiveData<MyPauseApplyDetailBean> applyBean = new MutableLiveData<>();

    public MutableLiveData<MyPauseApplyDetailBean> getApplyBean() {
        return applyBean;
    }

    public void loadApplyDetailDataFromNet(MyPauseApplyBean bean){
        MyPauseApplyDetailBean detailBean = new MyPauseApplyDetailBean();
        detailBean.setBuyer(bean.getBuyer());
        detailBean.setJudgement(bean.getJudgement());
        detailBean.setContractId(bean.getContractId());
        detailBean.setDate(bean.getDate());
        detailBean.setExceptionTag(bean.getExceptionTag());
        detailBean.setState(bean.getState());
        detailBean.setApplier("谷村新司");
        detailBean.setDetailCause(
                "　　“什么！什么！日本问题座谈会？怎么我不知道，怎么不告诉我？”\n" +
                "　　“我们那天部务会议决议了的。我来找过华先生，华先生又是不在家——”\n" +
                "　　“好啊，你们秘密行动！”他瞪着眼。“你老实告诉我——这个座谈会到底是\n" +
                "　　什么背景，你老实告诉我！”\n" +
                "　　对方似乎也动了火：\n" +
                "　　“什么背景呢，都是中华民族！部务会议议决的，怎么是秘密行动呢。……华\n" +
                "　　先生又不到会，开会也不终席，来找又找不到……我们总不能把部里的工作停顿起\n" +
                "　　来。”\n" +
                "　　“混蛋！”他咬着牙，嘴唇在颤抖着。“你们小心！你们，哼，你们！你们！\n" +
                "　　……”他倒到了沙发上，嘴巴痛苦地抽得歪着。“妈的！这个这个——你们青年！\n" +
                "　　……”\n" +
                "　　五分钟之后他抬起头来，害怕地四面看一看。那两个客人已经走了。他叹一口\n" +
                "　　长气，对我说：\n" +
                "　　“唉，你看你看！现在的青年怎么办，现在的青年！”\n" +
                "　　这晚他没命地喝了许多酒，嘴里嘶嘶地骂着那些小伙子。他打碎了一只茶杯。\n" +
                "　　密司黄扶着他上了床，他忽然打个寒噤说：“明天十点钟有个集会……”");
        List<GenericApproveLog> logList = new ArrayList<>();
        //first create data
        ApproveLogFirstCreateDate logFirstCreateDate = new ApproveLogFirstCreateDate();
        logFirstCreateDate.setApplier(detailBean.getApplier());
        logFirstCreateDate.setCreateDate(System.currentTimeMillis());
        GenericApproveLog l1 = new GenericApproveLog();
        l1.setType(GenericApproveLog.LOG_TYPE_FIRST_CREATE_DATE);
        l1.setJson(new Gson().toJson(logFirstCreateDate));
        logList.add(l1);
        //to be process
        ApproveLogToBePrc toBePrc = new ApproveLogToBePrc();
        List<StaffBean> sendList = new ArrayList<>();
        StaffBean bean1 = new StaffBean();
        bean1.setName("高大强");
        StaffBean bean2 = new StaffBean();
        bean2.setName("铁心兰");
        sendList.add(bean1);
        sendList.add(bean2);
        toBePrc.setSendList(sendList);

        GenericApproveLog l2 = new GenericApproveLog();
        l2.setType(GenericApproveLog.LOG_TYPE_TO_BE_PROCESS);
        l2.setJson(new Gson().toJson(toBePrc));
        logList.add(l2);
        //processing
        ApproveLogProcessing processing = new ApproveLogProcessing();
        processing.setCurrentTurn(toBePrc.getSendList().get(0));
        GenericApproveLog l3 =new GenericApproveLog();
        l3.setType(GenericApproveLog.LOG_TYPE_PROCESSING);
        l3.setJson(new Gson().toJson(processing));
        logList.add(l3);
        //processing
        ApproveLogProcessing processing1 = new ApproveLogProcessing();
        processing1.setCurrentTurn(toBePrc.getSendList().get(1));
        GenericApproveLog l4 = new GenericApproveLog();
        l4.setType(GenericApproveLog.LOG_TYPE_PROCESSING);
        l4.setJson(new Gson().toJson(processing1));
        logList.add(l4);
        //reject
        if(bean.getState()==MyPauseApplyBean.APPLY_REJECTED){
            ApproveLogReject reject = new ApproveLogReject();
            reject.setDate(System.currentTimeMillis());
            List<Vote> voteList = new ArrayList<>();
            Vote vote1 = new Vote();
            vote1.setName(toBePrc.getSendList().get(0).getName());
            vote1.setResult(Vote.VOTE_RESULT_PASS);
            Vote vote2 = new Vote();
            vote2.setName(toBePrc.getSendList().get(1).getName());
            vote2.setResult(Vote.VOTE_RESULT_REJECT);
            voteList.add(vote1);
            voteList.add(vote2);
            reject.setVoteList(voteList);
            List<MeetingComment> meetingComments=new ArrayList<>();
            MeetingComment comment1=new MeetingComment();
            comment1.setName(toBePrc.getSendList().get(0).getName());
            comment1.setDept("生产部");
            comment1.setContent("同意。");
            meetingComments.add(comment1);
            MeetingComment comment2=new MeetingComment();
            comment2.setName(toBePrc.getSendList().get(1).getName());
            comment2.setDept("销售部");
            comment2.setContent("利润不足以盈利，建议重新商议价格。");
            meetingComments.add(comment2);
            reject.setComments(meetingComments);
            GenericApproveLog l5= new GenericApproveLog();
            l5.setType(GenericApproveLog.LOG_TYPE_REJECT);
            l5.setJson(new Gson().toJson(reject));
            logList.add(l5);
        }else {
            //pass
            ApproveLogApproved approved = new ApproveLogApproved();
            approved.setDate(System.currentTimeMillis());
            List<Vote> voteList = new ArrayList<>();
            Vote vote1 = new Vote();
            vote1.setName(toBePrc.getSendList().get(0).getName());
            vote1.setResult(Vote.VOTE_RESULT_PASS);
            Vote vote2 = new Vote();
            vote2.setName(toBePrc.getSendList().get(1).getName());
            vote2.setResult(Vote.VOTE_RESULT_PASS);
            voteList.add(vote1);
            voteList.add(vote2);
            approved.setVoteList(voteList);
            List<MeetingComment> meetingComments=new ArrayList<>();
            MeetingComment comment1=new MeetingComment();
            comment1.setName(toBePrc.getSendList().get(0).getName());
            comment1.setDept("生产部");
            comment1.setContent("同意。");
            meetingComments.add(comment1);
            MeetingComment comment2=new MeetingComment();
            comment2.setName(toBePrc.getSendList().get(1).getName());
            comment2.setDept("销售部");
            comment2.setContent("同意。");
            meetingComments.add(comment2);
            approved.setComments(meetingComments);
            GenericApproveLog l5= new GenericApproveLog();
            l5.setType(GenericApproveLog.LOG_TYPE_APPROVED);
            l5.setJson(new Gson().toJson(approved));
            logList.add(l5);
        }
        detailBean.setLogs(logList);
        applyBean.postValue(detailBean);
    }
}
