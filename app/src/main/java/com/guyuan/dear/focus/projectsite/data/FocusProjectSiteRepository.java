package com.guyuan.dear.focus.projectsite.data;


import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.focus.projectsite.api.FocusProjectSiteApiService;
import com.guyuan.dear.focus.projectsite.bean.ProjectOverViewBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProjectSiteRepository {
    private FocusProjectSiteApiService apiService;

    public FocusProjectSiteRepository(FocusProjectSiteApiService focusProjectSiteApiService) {
        this.apiService = focusProjectSiteApiService;
    }

    Observable<ResultBean<ProjectOverViewBean>> getProjectSiteOverViewData() {
        return apiService.getProjectSiteOverViewData();
    }

    Observable<ResultBean<List<ProjectSiteStatusBean>>> getProjectSiteStatusList(long id, int type) {
        return apiService.getProjectSiteStatusList(id, type);
    }

//=====================================================================//
    //===============================现场勘察===============================//

    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getSiteExploreList(RequestBody body) {
        return apiService.getSiteExploreList(body);
    }

    Observable<ResultBean<SiteExploreBean>> getSiteExploreDetailData(long id) {
        return apiService.getSiteExploreDetailData(id);
    }

    Observable<ResultBean<Integer>> commitSiteExploreInfo(RequestBody body) {
        return apiService.commitSiteExploreInfo(body);
    }

    Observable<ResultBean<Integer>> postSiteExploreOpinion(RequestBody body) {
        return apiService.postSiteExploreOpinion(body);
    }

    //=====================================================================//
    //===============================安全排查===============================//

    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getCheckSafeList(RequestBody body) {
        return apiService.getCheckSafeList(body);
    }

    Observable<ResultBean<SiteExploreBean>> getCheckSafeDetailData(long id) {
        return apiService.getCheckSafeDetailData(id);
    }

    Observable<ResultBean<Integer>> postCheckSafeInfo(RequestBody body) {
        return apiService.postCheckSafeInfo(body);
    }

    Observable<ResultBean<Integer>> postCheckSafeOpinion(RequestBody body) {
        return apiService.postCheckSafeOpinion(body);
    }

    //=====================================================================//
    //===============================清点货物===============================//

    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getCheckGoodList(RequestBody body) {
        return apiService.getCheckGoodList(body);
    }

    Observable<ResultBean<SiteExploreBean>> getCheckGoodDetailData(long id) {
        return apiService.getCheckGoodDetailData(id);
    }

    Observable<ResultBean<Integer>> postCheckGoodInfo(RequestBody body) {
        return apiService.postCheckGoodInfo(body);
    }

    Observable<ResultBean<Integer>> postCheckGoodOpinion(RequestBody body) {
        return apiService.postCheckGoodOpinion(body);
    }

    //=====================================================================//
    //===============================安装调试===============================//

    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getInstallDebugList(RequestBody body) {
        return apiService.getInstallDebugList(body);
    }

    Observable<ResultBean<SiteExploreBean>> getInstallDebugDetailData(long id) {
        return apiService.getInstallDebugDetailData(id);

    }    Observable<ResultBean<SiteExploreBean>> getInstallDebugDetailDataBySingle(long id) {
        return apiService.getInstallDebugDetailDataBySingle(id);
    }

    Observable<ResultBean<Integer>> postInstallDebugInfo(RequestBody body) {
        return apiService.postInstallDebugInfo(body);
    }

    Observable<ResultBean<Integer>> postInstallDebugOpinion(RequestBody body) {
        return apiService.postInstallDebugOpinion(body);
    }

    //=====================================================================//
    //===============================客户验收===============================//

    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getCustomerAcceptanceList(RequestBody body) {
        return apiService.getCustomerAcceptanceList(body);
    }

    Observable<ResultBean<SiteExploreBean>> getCustomerAcceptanceDetailData(long id) {
        return apiService.getCustomerAcceptanceDetailData(id);
    }

    Observable<ResultBean<Integer>> postCustomerAcceptanceInfo(RequestBody body) {
        return apiService.postCustomerAcceptanceInfo(body);
    }

    Observable<ResultBean<Integer>> postCustomerAcceptanceOpinion(RequestBody body) {
        return apiService.postCustomerAcceptanceOpinion(body);
    }

}
