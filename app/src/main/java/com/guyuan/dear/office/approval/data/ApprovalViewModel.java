package com.guyuan.dear.office.approval.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.office.approval.api.ApprovalApiService;
import com.guyuan.dear.office.approval.data.bean.ApprovalListBean;
import com.guyuan.dear.office.approval.ui.ApprovalFragment;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:47
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalViewModel extends BaseViewModel {
    private ApprovalApiService apiService;
    private MutableLiveData<ApprovalListBean> approvalListMLD = new MutableLiveData<>();
    private MutableLiveData<ApprovalListBean> approvedListMLD = new MutableLiveData<>();

    @ViewModelInject
    public ApprovalViewModel(ApprovalRepository repository) {
        apiService = repository.getApiService();
    }

    public MutableLiveData<ApprovalListBean> getApprovalListMLD() {
        return approvalListMLD;
    }

    public MutableLiveData<ApprovalListBean> getApprovedListMLD() {
        return approvedListMLD;
    }

    public void getApprovalList(int pageIndex, int listType) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(pageIndex);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setListType(listType);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, apiService.getApprovalList(
                CommonUtils.getCommonRequestBody(requestBody)))
                .getHelper().flow(getApprovalData(listType));
    }

    private MutableLiveData<ApprovalListBean> getApprovalData(int type) {
        switch (type) {
            case ApprovalFragment.APPROVAL:

                return approvalListMLD;

            case ApprovalFragment.APPROVED:

                return approvedListMLD;

            default:

                return approvalListMLD;
        }
    }
}