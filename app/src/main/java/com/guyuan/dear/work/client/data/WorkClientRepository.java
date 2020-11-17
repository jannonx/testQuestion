package com.guyuan.dear.work.client.data;


import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.work.client.api.WorkClientApiService;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkClientRepository {
    private WorkClientApiService apiService;

    public WorkClientRepository(WorkClientApiService focusAfterSaleApiService) {
        this.apiService = focusAfterSaleApiService;
    }

    Observable<ResultBean<RefreshBean<ClientCompanyBean>>> getClientList(RequestBody body) {
        return apiService.getClientList(body);
    }

    Observable<ResultBean<RefreshBean<ClientCompanyBean>>> getMyClientList(RequestBody body) {
        return apiService.getMyClientList(body);
    }

    Observable<ResultBean<ClientCompanyBean>> getClientBasicInfo(long id) {
        return apiService.getClientBasicInfo(id);
    }

    Observable<ResultBean<RefreshBean<CommentsBean>>> getFollowCommentList(RequestBody body) {
        return apiService.getFollowCommentList(body);
    }

    Observable<ResultBean<Integer>> postClientFollowUp(long customerId, String content) {
        return apiService.postClientFollowUp(customerId, content);
    }

    Observable<ResultBean<Integer>> postUserFollowUp(long followId, String content) {
        return apiService.postUserFollowUp(followId, content);
    }

}
