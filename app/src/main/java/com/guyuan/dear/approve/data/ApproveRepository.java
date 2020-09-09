package com.guyuan.dear.approve.data;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.approve.api.ApproveApiService;
import com.guyuan.dear.approve.bean.ApprovalData;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/9 15:52
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApproveRepository {
    private ApproveApiService approveApiService;

    public ApproveRepository(ApproveApiService approveApiService) {
        this.approveApiService = approveApiService;
    }

    Observable<ResultBean<RefreshBean<ApprovalData>>> getApplyNotApproveList(RequestBody body) {
        return approveApiService.getApplyNotApproveList(body);
    }
}
