package com.guyuan.dear.focus.assess.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.assess.api.FocusAssessApiService;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.focus.assess.data.bean.AssessOverviewBean;

import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusAssessViewModel extends BaseViewModel {
    private FocusAssessApiService apiService;
    public MutableLiveData<AssessOverviewBean> assessOverviewBean = new MutableLiveData<>();
    public MutableLiveData<AssessListBean> assessListBean = new MutableLiveData<>();
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

    public void getAssessList(int pageIndex, int pageSize, String queryParams, int status) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getAssessList(pageIndex, pageSize, queryParams, status))
                .getHelper().flow(assessListBean);
    }

    public void getAssessDetail(int id, String contractNumber) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getAssessDetail(id, contractNumber)).getHelper().flow(assessDetailList);
    }
}
