package com.guyuan.dear.work.contractPause.views.applyDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.gson.Gson;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogApproved;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogFirstCreateDate;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogProcessing;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogReject;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogToBePrc;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.GenericApproveLog;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.MyApplyDetailBean;
import com.guyuan.dear.work.contractPause.repos.MyApplyDetailRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyDetailViewModel extends BaseViewModel {
    private MyApplyDetailRepo repo = new MyApplyDetailRepo();
    private MutableLiveData<MyApplyDetailBean> applyBean = new MutableLiveData<>();

    public MutableLiveData<MyApplyDetailBean> getApplyBean() {
        return applyBean;
    }


    public Disposable getMyApplyDetailFromNet(int examineId){
        return repo.getMyApplyDetail(examineId, getApplyDetailCallback);
    }
    private DearNetHelper.NetCallback<MyApplyDetailBean> getApplyDetailCallback = new DearNetHelper.NetCallback<MyApplyDetailBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(MyApplyDetailBean result) {
            applyBean.postValue(result);
            isShowLoading.postValue(false);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(),error.getMessage());
        }
    };

    /**
     * 生成模拟数据，显示在主界面
     * @param bean
     */
//    public void loadApplyDetailDataFromNet(MyApplyBean bean){
//        MyApplyDetailBean detailBean = new MyApplyDetailBean();
//        detailBean.setBuyer(bean.getBuyer());
//        detailBean.setJudgement(bean.getJudgement());
//        detailBean.setContractNum(bean.getContractNum());
//        detailBean.setDate(bean.getDate());
//        detailBean.setExceptionTag(bean.getExceptionTag());
//        detailBean.setState(bean.getState());
//        detailBean.setApplier("谷村新司");
//        detailBean.setDetailCause(
//                "　　“什么！什么！日本问题座谈会？怎么我不知道，怎么不告诉我？”\n" +
//                        "　　“我们那天部务会议决议了的。我来找过华先生，华先生又是不在家——”\n" +
//                        "　　“好啊，你们秘密行动！”他瞪着眼。“你老实告诉我——这个座谈会到底是\n" +
//                        "　　什么背景，你老实告诉我！”\n" +
//                        "　　对方似乎也动了火：\n" +
//                        "　　“什么背景呢，都是中华民族！部务会议议决的，怎么是秘密行动呢。……华\n" +
//                        "　　先生又不到会，开会也不终席，来找又找不到……我们总不能把部里的工作停顿起来");
//        List<GenericApproveLog> logList = new ArrayList<>();
//        //first create data
//        ApproveLogFirstCreateDate logFirstCreateDate = new ApproveLogFirstCreateDate();
//        logFirstCreateDate.setApplier(detailBean.getApplier());
//        logFirstCreateDate.setCreateDate(System.currentTimeMillis());
//        GenericApproveLog l1 = new GenericApproveLog();
//        l1.setType(GenericApproveLog.LOG_TYPE_FIRST_CREATE_DATE);
//        l1.setJson(new Gson().toJson(logFirstCreateDate));
//        logList.add(l1);
//        //to be process
//        ApproveLogToBePrc toBePrc = new ApproveLogToBePrc();
//        List<StaffBean> sendList = new ArrayList<>();
//        StaffBean bean1 = new StaffBean();
//        bean1.setName("高大强");
//        StaffBean bean2 = new StaffBean();
//        bean2.setName("铁心兰");
//        sendList.add(bean1);
//        sendList.add(bean2);
//        toBePrc.setSendList(sendList);
//
//        GenericApproveLog l2 = new GenericApproveLog();
//        l2.setType(GenericApproveLog.LOG_TYPE_TO_BE_PROCESS);
//        l2.setJson(new Gson().toJson(toBePrc));
//        logList.add(l2);
//        //processing
//        ApproveLogProcessing processing = new ApproveLogProcessing();
//        processing.setCurrentTurn(toBePrc.getSendList().get(0));
//        GenericApproveLog l3 =new GenericApproveLog();
//        l3.setType(GenericApproveLog.LOG_TYPE_PROCESSING);
//        l3.setJson(new Gson().toJson(processing));
//        logList.add(l3);
//        //processing
//        ApproveLogProcessing processing1 = new ApproveLogProcessing();
//        processing1.setCurrentTurn(toBePrc.getSendList().get(1));
//        GenericApproveLog l4 = new GenericApproveLog();
//        l4.setType(GenericApproveLog.LOG_TYPE_PROCESSING);
//        l4.setJson(new Gson().toJson(processing1));
//        logList.add(l4);
//        //reject
//        if(bean.getState()== MyApplyBean.APPLY_REJECTED){
//            ApproveLogReject reject = new ApproveLogReject();
//            reject.setDate(System.currentTimeMillis());
//            List<Vote> voteList = new ArrayList<>();
//            Vote vote1 = new Vote();
//            vote1.setName(toBePrc.getSendList().get(0).getName());
//            vote1.setResult(Vote.VOTE_RESULT_PASS);
//            Vote vote2 = new Vote();
//            vote2.setName(toBePrc.getSendList().get(1).getName());
//            vote2.setResult(Vote.VOTE_RESULT_REJECT);
//            voteList.add(vote1);
//            voteList.add(vote2);
//            reject.setVoteList(voteList);
//            List<MeetingComment> meetingComments=new ArrayList<>();
//            MeetingComment comment1=new MeetingComment();
//            comment1.setName(toBePrc.getSendList().get(0).getName());
//            comment1.setDept("生产部");
//            comment1.setContent("同意。");
//            meetingComments.add(comment1);
//            MeetingComment comment2=new MeetingComment();
//            comment2.setName(toBePrc.getSendList().get(1).getName());
//            comment2.setDept("销售部");
//            comment2.setContent("利润不足以盈利，建议重新商议价格。");
//            meetingComments.add(comment2);
//            reject.setComments(meetingComments);
//            GenericApproveLog l5= new GenericApproveLog();
//            l5.setType(GenericApproveLog.LOG_TYPE_REJECT);
//            l5.setJson(new Gson().toJson(reject));
//            logList.add(l5);
//        }else {
//            //pass
//            ApproveLogApproved approved = new ApproveLogApproved();
//            approved.setDate(System.currentTimeMillis());
//            List<Vote> voteList = new ArrayList<>();
//            Vote vote1 = new Vote();
//            vote1.setName(toBePrc.getSendList().get(0).getName());
//            vote1.setResult(Vote.VOTE_RESULT_PASS);
//            Vote vote2 = new Vote();
//            vote2.setName(toBePrc.getSendList().get(1).getName());
//            vote2.setResult(Vote.VOTE_RESULT_PASS);
//            voteList.add(vote1);
//            voteList.add(vote2);
//            approved.setVoteList(voteList);
//            List<MeetingComment> meetingComments=new ArrayList<>();
//            MeetingComment comment1=new MeetingComment();
//            comment1.setName(toBePrc.getSendList().get(0).getName());
//            comment1.setDept("生产部");
//            comment1.setContent("同意。");
//            meetingComments.add(comment1);
//            MeetingComment comment2=new MeetingComment();
//            comment2.setName(toBePrc.getSendList().get(1).getName());
//            comment2.setDept("销售部");
//            comment2.setContent("同意。");
//            meetingComments.add(comment2);
//            approved.setComments(meetingComments);
//            GenericApproveLog l5= new GenericApproveLog();
//            l5.setType(GenericApproveLog.LOG_TYPE_APPROVED);
//            l5.setJson(new Gson().toJson(approved));
//            logList.add(l5);
//        }
//        detailBean.setLogs(logList);
//        applyBean.postValue(detailBean);
//    }
}
