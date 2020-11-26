package com.guyuan.dear.analyse.operate.data;


import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.analyse.operate.api.OperateApiService;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.PartMap;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateRepository {
    private OperateApiService apiService;

    public OperateRepository(OperateApiService focusAfterSaleApiService) {
        this.apiService = focusAfterSaleApiService;
    }

    Observable<ResultBean<List<UploadBean>>> uploadPic(@PartMap Map<String, RequestBody> map) {
        return apiService.uploadPic(map);
    }

    Observable<ResultBean<RefreshBean<AfterSaleBean>>> getAfterSaleList(RequestBody body) {
        return apiService.getAfterSaleList(body);
    }

    Observable<ResultBean<AfterSaleBean>> getAfterSaleDetail(long id) {
        return apiService.getAfterSaleDetail(id);
    }

    Observable<ResultBean<List<AfterSaleStatusBean>>> getAfterSaleStatusList(long id, int type) {
        return apiService.getAfterSaleStatusList(id, type);
    }

    Observable<ResultBean<Integer>> postAnswerInfo(RequestBody body) {
        return apiService.postAnswerInfo(body);
    }

    Observable<ResultBean<Integer>> postAcceptInfo(int status) {
        return apiService.postAcceptInfo(status);
    }
}
