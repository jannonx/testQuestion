package com.guyuan.dear.focus.client.data;


import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.approve.bean.ApprovalData;
import com.guyuan.dear.focus.client.api.FocusClientApiService;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientRepository {
    private FocusClientApiService apiService;

    public FocusClientRepository(FocusClientApiService focusAfterSaleApiService) {
        this.apiService = focusAfterSaleApiService;
    }

    public FocusClientApiService getApiService() {
        return apiService;
    }
//    Observable<ResultBean<RefreshBean<ApprovalData>>> getApplyNotApproveList(RequestBody body) {
//        return approveApiService.getApplyNotApproveList(body);
//    }

    Observable<ResultBean<ClientCompanyBean>> getClientList(RequestBody body) {
        return apiService.getClientList(body);
    }
}
