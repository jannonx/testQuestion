package com.guyuan.dear.work.assess.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.assess.api.WorkAssessApiService;
import com.guyuan.dear.work.assess.data.bean.CustomerBean;
import com.guyuan.dear.work.assess.data.bean.MeetingRoomBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessCommitBody;
import com.guyuan.dear.work.assess.data.bean.WorkAssessDetailBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessItemBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessListBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessVoteBody;
import com.guyuan.dear.work.assess.ui.WorkAssessListFragment;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:38
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessViewModel extends BaseViewModel {
    private WorkAssessApiService apiService;
    public MutableLiveData<WorkAssessListBean> workAssessList = new MutableLiveData<>();
    public MutableLiveData<WorkAssessListBean> workAssessCreateList = new MutableLiveData<>();
    public MutableLiveData<WorkAssessDetailBean> workAssessListDetail = new MutableLiveData<>();
    public MutableLiveData<List<CustomerBean>> customerList = new MutableLiveData<>();
    public MutableLiveData<MeetingRoomBean> meetingRoomList = new MutableLiveData<>();

    @ViewModelInject
    public WorkAssessViewModel(WorkAssessRepository workAssessRepository) {
        this.apiService = workAssessRepository.getAssessApiService();
    }

    //查询评审列表
    public void getAssessList(int pageIndex, String content, int type) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        requestBody.setPageNum(pageIndex);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setQueryParams(content);
        requestBody.setFilters(filtersBean);
        switch (type) {
            case WorkAssessListFragment.TOTAL:
                RxJavaHelper.build(this,
                        apiService.getAssessList(CommonUtils.getCommonRequestBody(requestBody)))
                        .getHelper().flow(workAssessList);
                break;

            case WorkAssessListFragment.CREATE:
                RxJavaHelper.build(this,
                        apiService.getMyCreatedAssessList(CommonUtils.getCommonRequestBody(requestBody)))
                        .getHelper().flow(workAssessCreateList);
                break;
        }

    }

    //评审详情
    public void getAssessDetail(int id) {
        RxJavaHelper.build(this, apiService.getAssessDetail(id)).getHelper()
                .flow(workAssessListDetail);
    }


    //评审投票
    public void voteAssess(int id, String auditExplain, int auditStatus, String imgUrl) {
        WorkAssessVoteBody voteBody = new WorkAssessVoteBody();
        voteBody.setId(id);
        voteBody.setAuditExplain(auditExplain);
        voteBody.setAuditStatus(auditStatus);
        voteBody.setImgUrl(imgUrl);
        RxJavaHelper.build(this, apiService.voteAssess(CommonUtils.getCommonRequestBody(voteBody)))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                })
                .getHelper()
                .flow();
    }


    //评审提交或修改
    public void assessCommit(String contractNumber, int customerId, int id, int isDraft,
                             int isInformTime, int meetingId, String reserveEndTime, int reserveId,
                             String reserveStartTime,
                             List<WorkAssessCommitBody.AuditItemDetailParamsListBean> auditItemDetailParamsList,
                             List<Integer> userIdList) {

        WorkAssessCommitBody commitBody = new WorkAssessCommitBody();
        commitBody.setContractNumber(contractNumber);
        commitBody.setCustomerId(customerId);
        commitBody.setIsDraft(isDraft);
        commitBody.setIsInformTime(isInformTime);
        commitBody.setMeetingId(meetingId);
        commitBody.setReserveId(reserveId);
        commitBody.setReserveStartTime(reserveStartTime);
        commitBody.setReserveEndTime(reserveEndTime);
        commitBody.setAuditItemDetailParamsList(auditItemDetailParamsList);
        commitBody.setUserIdList(userIdList);
        RxJavaHelper.build(this, apiService.assessCommit(
                CommonUtils.getCommonRequestBody(commitBody)))
                .success(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                    }
                })
                .getHelper()
                .flow();
    }

    //获取所有客户列表
    public void getCustomerList() {
        RxJavaHelper.build(this, apiService.getAllCustomerList()).getHelper().flow(customerList);
    }

    //获取会议室列表
    public void getMeetingRoomList() {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(ConstantValue.FIRST_PAGE);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        RxJavaHelper.build(this, apiService.getMeetingRoomList(
                CommonUtils.getCommonRequestBody(requestBody))).getHelper().flow(meetingRoomList);
    }

}