package com.guyuan.dear.focus.transport.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.focus.transport.api.TransportApiService;
import com.guyuan.dear.focus.transport.data.bean.TransportDetailBean;
import com.guyuan.dear.focus.transport.data.bean.TransportListBean;
import com.guyuan.dear.focus.transport.ui.TransportFragment;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.sun.jna.platform.win32.WinDef;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:32
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportViewModel extends BaseViewModel {
    private TransportApiService apiService;

    private MutableLiveData<TransportListBean> transportFollowListMLD = new MutableLiveData<>();
    private MutableLiveData<TransportListBean> transportArrivedListMLD = new MutableLiveData<>();
    private MutableLiveData<TransportDetailBean> transportDetailMLD=new MutableLiveData<>();

    @ViewModelInject
    public TransportViewModel(TransportRepository repository) {
        apiService = repository.getApiService();
    }

    public MutableLiveData<TransportListBean> getTransportFollowListMLD() {
        return transportFollowListMLD;
    }

    public void setTransportFollowListMLD(MutableLiveData<TransportListBean> transportFollowListMLD) {
        this.transportFollowListMLD = transportFollowListMLD;
    }

    public MutableLiveData<TransportListBean> getTransportArrivedListMLD() {
        return transportArrivedListMLD;
    }

    public void setTransportArrivedListMLD(MutableLiveData<TransportListBean> transportArrivedListMLD) {
        this.transportArrivedListMLD = transportArrivedListMLD;
    }

    public MutableLiveData<TransportDetailBean> getTransportDetailMLD() {
        return transportDetailMLD;
    }

    public void setTransportDetailMLD(MutableLiveData<TransportDetailBean> transportDetailMLD) {
        this.transportDetailMLD = transportDetailMLD;
    }

    //获取运输列表
    public void getTransportList(int type, int pageIndex, String content) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(pageIndex);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setQueryParams(content);
        filtersBean.setTransportStatus(type);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, apiService.getTransportList(
                CommonUtils.getCommonRequestBody(requestBody))).getHelper().flow(getListMLD(type));
    }


    private MutableLiveData<TransportListBean> getListMLD(int type) {
        switch (type) {
            case TransportFragment.FOLLOW:

                return transportFollowListMLD;

            case TransportFragment.ARRIVED:

                return transportArrivedListMLD;

            default:
                return transportFollowListMLD;
        }
    }

    //运输详情
    public void getTransportDetail(int id) {
        RxJavaHelper.build(this, apiService.getTransportDetail(id))
                .getHelper().flow(transportDetailMLD);
    }
}