package com.guyuan.dear.work.produce.data;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.work.produce.api.WorkProduceApiService;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/3 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProduceRepository {
    private WorkProduceApiService apiService;

    public WorkProduceRepository(WorkProduceApiService apiService) {
        this.apiService = apiService;
    }

    public WorkProduceApiService getApiService() {
        return apiService;
    }

    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceList(RequestBody body) {
        return apiService.getProduceList(body);
    }

}
