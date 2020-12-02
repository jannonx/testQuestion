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

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.http.Query;

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

    private ApprovalViewModelListener listener;

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

    //获取审批列表
    public void getApprovalList(int pageIndex, String searchContent, int listType) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(pageIndex);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setName(searchContent);
        filtersBean.setApprovalType(listType);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, apiService.getApprovalList(
                CommonUtils.getCommonRequestBody(requestBody)))
                .getHelper().flow(getApprovalData(listType));
    }


    //生产计划审批
    public void produceApproval(int businessId, int businessType, String remarks,
                                int status, int type) {
        RxJavaHelper.build(this, apiService.productApproval(businessId, businessType,
                remarks, status, type))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (listener != null) {
                            listener.handled();
                        }
                    }
                })
                .getHelper().flow();
    }

    //合同审批
    public void contractApproval(int arType, int businessId, String remarks, int status) {
        RxJavaHelper.build(this, apiService.contractApproval(arType, businessId,
                remarks, status))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (listener != null) {
                            listener.handled();
                        }
                    }
                })
                .getHelper().flow();
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

    public void setListener(ApprovalViewModelListener listener) {
        this.listener = listener;
    }

    public interface ApprovalViewModelListener {
        void handled();
    }
}