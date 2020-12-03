package com.guyuan.dear.focus.assess.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.focus.assess.api.FocusAssessApiService;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBody;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.focus.assess.data.bean.AssessOverviewBean;
import com.guyuan.dear.focus.assess.ui.FocusAssessListFragment;
import com.guyuan.dear.utils.CommonUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @author : tl
 * @description :我的关注-评审viewModel
 * @since: 2020/9/16 17:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusAssessViewModel extends BaseViewModel {
    private FocusAssessApiService apiService;
    public MutableLiveData<AssessOverviewBean> assessOverviewBean = new MutableLiveData<>();
    public MutableLiveData<AssessListBean> assessNotPassListBean = new MutableLiveData<>();
    public MutableLiveData<AssessListBean> assessPassListBean = new MutableLiveData<>();
    public MutableLiveData<AssessListBean> assessTotalListBean = new MutableLiveData<>();
    public MutableLiveData<AssessListBean> assessOverviewSearchListBean = new MutableLiveData<>();
    public MutableLiveData<List<AssessDetailBean>> assessDetailList = new MutableLiveData<>();

    @ViewModelInject
    public FocusAssessViewModel(FocusAssessRepository focusAssessRepository) {
        this.apiService = focusAssessRepository.getFocusAssessApiService();
    }

    public void getAssessOverview(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getAssessOverview(body)).getHelper().flow(assessOverviewBean);
        addSubscription(disposable);
    }

    /**
     * 评审列表查询
     *
     * @param pageIndex   页码
     * @param pageSize    每页数量
     * @param queryParams 查询内容
     * @param status      查询类别
     */
    public void getAssessList(int pageIndex, int pageSize, String queryParams, int status) {
        ListRequestBody body = new ListRequestBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setQueryParams(queryParams);
        filtersBean.setStatus(status);
        body.setFilters(filtersBean);
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getAssessList(CommonUtils.getCommonRequestBody(body)))
                .showLoading(false)
                .getHelper().flow(getListBeanByStatus(status));
        addSubscription(disposable);
    }

    /**
     * 概览评审列表查询
     *
     * @param pageIndex   页码
     * @param pageSize    每页数量
     * @param queryParams 查询内容
     * @param status      查询类别
     */
    public void getAssessSearchList(int pageIndex, int pageSize, String queryParams, int status) {
        ListRequestBody body = new ListRequestBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setQueryParams(queryParams);
        filtersBean.setStatus(status);
        body.setFilters(filtersBean);
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getAssessList(CommonUtils.getCommonRequestBody(body)))
                .showLoading(false)
                .getHelper().flow(assessOverviewSearchListBean);
        addSubscription(disposable);
    }

    //根据类型设置返回的数据到liveData中
    public MutableLiveData<AssessListBean> getListBeanByStatus(int status) {
        if (status == FocusAssessListFragment.NOT_PASS) {
            return assessNotPassListBean;
        } else if (status == FocusAssessListFragment.PASS) {
            return assessPassListBean;
        } else {
            return assessTotalListBean;
        }
    }

    /**
     * 评审详情
     *
     * @param id             id
     * @param contractNumber 合同编号
     */
    public void getAssessDetail(int id, String contractNumber) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getAssessDetail(id, contractNumber))
                .getHelper().flow(assessDetailList);
        addSubscription(disposable);
    }
}
