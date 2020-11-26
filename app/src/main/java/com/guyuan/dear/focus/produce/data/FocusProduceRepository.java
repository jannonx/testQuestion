package com.guyuan.dear.focus.produce.data;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.produce.api.FocusProduceApiService;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceOverViewBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceRepository {
    private FocusProduceApiService apiService;

    public FocusProduceRepository(FocusProduceApiService apiService) {
        this.apiService = apiService;
    }

    public FocusProduceApiService getApiService() {
        return apiService;
    }


    Observable<ResultBean<ProduceOverViewBean>> getProduceOverView(RequestBody body) {
        return apiService.getProduceOverView(body);
    }

    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceListByStatus(RequestBody body) {
        return apiService.getProduceListByStatus(body);
    }

    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceExceptionList(RequestBody body) {
        return apiService.getProduceExceptionList(body);
    }

    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceList(RequestBody body) {
        return apiService.getProduceList(body);
    }

    Observable<ResultBean<FocusProduceBean>> getBasicInfoById(long playId) {
        return apiService.getBasicInfoById(playId);
    }

    Observable<ResultBean<List<ProduceStateBean>>> getProduceStateList(long playId) {
        return apiService.getProduceStateList(playId);
    }

    Observable<ResultBean<Integer>> postExecuteProduceInfo(RequestBody body) {
        return apiService.postExecuteProduceInfo(body);
    }

    Observable<ResultBean<Integer>> approval(int businessId, int businessType,
                                             String remarks, int status, int type) {
        return apiService.approval(businessId, businessType, remarks, status, type);
    }
}
