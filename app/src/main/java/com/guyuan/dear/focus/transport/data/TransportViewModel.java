package com.guyuan.dear.focus.transport.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.focus.transport.api.TransportApiService;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

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

    private MutableLiveData<Object> transportListMLD = new MutableLiveData<>();

    @ViewModelInject
    public TransportViewModel(TransportRepository repository) {
        apiService = repository.getApiService();
    }

    //获取运输列表
    public void getTransportList(int pageIndex, String content) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(pageIndex);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setName(content);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, apiService.getTransportList(
                CommonUtils.getCommonRequestBody(requestBody))).getHelper().flow(transportListMLD);
    }

}