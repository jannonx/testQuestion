package com.guyuan.dear.focus.aftersale.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteRepository;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusAfterSaleViewModel extends BaseViewModel {
    private FocusAfterSaleRepository repository;

    @ViewModelInject
    public FocusAfterSaleViewModel(FocusAfterSaleRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }

    private MutableLiveData<RefreshBean<AfterSaleBean>> afterSaleListEvent = new MutableLiveData<>();
    private MutableLiveData<AfterSaleBean> afterSaleDetailEvent = new MutableLiveData<>();

    /**
     * 获取列表
     *
     * @param body 参数体
     * @return
     */
    public void getAfterSaleList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getAfterSaleList(body))
                .getHelper().flow(afterSaleListEvent);
        addSubscription(disposable);
    }

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return
     */
    public void getAfterSaleDetail(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getAfterSaleDetail(id))
                .getHelper().flow(afterSaleDetailEvent);
        addSubscription(disposable);
    }

    public MutableLiveData<RefreshBean<AfterSaleBean>> getAfterSaleListEvent() {
        return afterSaleListEvent;
    }

    public void setAfterSaleListEvent(MutableLiveData<RefreshBean<AfterSaleBean>> afterSaleListEvent) {
        this.afterSaleListEvent = afterSaleListEvent;
    }

    public MutableLiveData<AfterSaleBean> getAfterSaleDetailEvent() {
        return afterSaleDetailEvent;
    }

    public void setAfterSaleDetailEvent(MutableLiveData<AfterSaleBean> afterSaleDetailEvent) {
        this.afterSaleDetailEvent = afterSaleDetailEvent;
    }
}
