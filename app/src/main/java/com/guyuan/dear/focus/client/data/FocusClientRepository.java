package com.guyuan.dear.focus.client.data;


import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.approve.bean.ApprovalData;
import com.guyuan.dear.focus.client.api.FocusClientApiService;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;

import java.util.List;

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


    Observable<ResultBean<List<ClientCompanyBean>>> getClientListByName(String name) {
        return apiService.getClientListByName(name);
    }

    Observable<ResultBean<List<ClientCompanyBean>>> getClientList(RequestBody body) {
        return apiService.getClientList(body);
    }

    Observable<ResultBean<ClientCompanyBean>> getClientBasicInfo(long id) {
        return apiService.getClientBasicInfo(id);
    }

    Observable<ResultBean<RefreshBean<ClientCompanyBean>>> getFollowCommentList(RequestBody body) {
        return apiService.getFollowCommentList(body);
    }



    Observable<ResultBean<Integer>> postCommentFollowUp(long followId, String content) {
        return apiService.postCommentFollowUp(followId, content);
    }

}
