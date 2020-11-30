package com.guyuan.dear.analyse.operate.data;


import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.analyse.operate.api.OperateApiService;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.bean.OperateStatisticsBean;

import java.util.List;

import io.reactivex.Observable;

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

    Observable<ResultBean<OperateOverViewBean>> getOperateOverViewData(String time) {
        return apiService.getOperateOverViewData(time);
    }
    Observable<ResultBean<OperateStatisticsBean>> getOperateStatistics(String time) {
        return apiService.getOperateStatistics(time);
    }


    Observable<ResultBean<List<OperateAnalyseBean>>> getPaymentList(String time) {
        return apiService.getPaymentList(time);
    }

    Observable<ResultBean<List<OperateAnalyseBean>>> getCostList(String time) {
        return apiService.getCostList(time);
    }

    Observable<ResultBean<List<OperateAnalyseBean>>> getOperateDetailData(long project) {
        return apiService.getOperateDetailData(project);
    }
}
