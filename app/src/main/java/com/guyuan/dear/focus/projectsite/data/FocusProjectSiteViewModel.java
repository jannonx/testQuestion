package com.guyuan.dear.focus.projectsite.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.focus.projectsite.bean.ProjectOverViewBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProjectSiteViewModel extends BaseViewModel {
    private FocusProjectSiteRepository repository;

    @ViewModelInject
    public FocusProjectSiteViewModel(FocusProjectSiteRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }

    /**
     * 概览
     */
    private MutableLiveData<ProjectOverViewBean> projectSiteOverViewEvent = new MutableLiveData<>();
    private MutableLiveData<List<ProjectSiteStatusBean>> projectSiteStatusEvent = new MutableLiveData<>();

    /**
     * 现场勘察
     */
    private MutableLiveData<RefreshBean<SiteExploreBean>> siteExploreListEvent = new MutableLiveData<>();
    private MutableLiveData<SiteExploreBean> siteExploreDetailEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> commitSiteExploreInfoEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> postSiteExploreOpinionEvent = new MutableLiveData<>();


    /**
     * 安全排查
     */
    private MutableLiveData<RefreshBean<SiteExploreBean>> checkSafeListEvent = new MutableLiveData<>();
    private MutableLiveData<SiteExploreBean> checkSafeDetailEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> commitCheckSafeInfoEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> postCheckSafeOpinionEvent = new MutableLiveData<>();


    /**
     * 安装调试
     */
    private MutableLiveData<RefreshBean<SiteExploreBean>> checkGoodListEvent = new MutableLiveData<>();
    private MutableLiveData<SiteExploreBean> checkGoodDetailEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> commitCheckGoodInfoEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> postCheckGoodOpinionEvent = new MutableLiveData<>();

    /**
     * 现场勘察
     */
    private MutableLiveData<RefreshBean<SiteExploreBean>> installDebugListEvent = new MutableLiveData<>();
    private MutableLiveData<SiteExploreBean> installDebugDetailEvent = new MutableLiveData<>();
    private MutableLiveData<SiteExploreBean> installDebugDetailBySingleEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> commitInstallDebugInfoEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> postInstallDebugOpinionEvent = new MutableLiveData<>();

    /**
     * 客户验收
     */
    private MutableLiveData<RefreshBean<SiteExploreBean>> customerAcceptanceListEvent = new MutableLiveData<>();
    private MutableLiveData<SiteExploreBean> customerAcceptanceDetailEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> commitCustomerAcceptanceInfoEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> postCustomerAcceptanceOpinionEvent = new MutableLiveData<>();


    /**
     * 各模块展示数量
     */
    public void getProjectSiteOverViewData() {

        Disposable disposable = RxJavaHelper.build(this, repository.getProjectSiteOverViewData())
                .getHelper().flow(projectSiteOverViewEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-意见回复-异步查询意见回复集
     */
    public void getProjectSiteStatusList(long id, int type) {

        Disposable disposable = RxJavaHelper.build(this, repository.getProjectSiteStatusList(id, type))
                .getHelper().flow(projectSiteStatusEvent);
        addSubscription(disposable);
    }

    //=====================================================================//
    //===============================现场勘察===============================//

    /**
     * 现场勘察单-app查询列表
     * <p>
     * * @param body
     */

    public void getSiteExploreList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getSiteExploreList(body))
                .getHelper().flow(siteExploreListEvent);
        addSubscription(disposable);
    }


    /**
     * 工程现场-现场勘察单-app根据评审id查看详情
     */
    public void getSiteExploreDetailData(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getSiteExploreDetailData(id))
                .getHelper().flow(siteExploreDetailEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-现场勘察单-app提交操作
     * * @param body
     */
    public void commitSiteExploreInfo(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.commitSiteExploreInfo(body))
                .getHelper().flow(commitSiteExploreInfoEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-现场勘察单-app提交操作
     * <p>
     * * @param body
     */
    public void postSiteExploreOpinion(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postSiteExploreOpinion(body))
                .getHelper().flow(postSiteExploreOpinionEvent);
        addSubscription(disposable);
    }

    //=====================================================================//
    //===============================安全排查===============================//

    /**
     * 安全排查单-app查询列表
     * <p>
     * * @param body
     */

    public void getCheckSafeList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCheckSafeList(body))
                .getHelper().flow(checkSafeListEvent);
        addSubscription(disposable);
    }


    /**
     * 工程现场-安全排查单-app根据评审id查看详情
     */
    public void getCheckSafeDetailData(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCheckSafeDetailData(id))
                .getHelper().flow(checkSafeDetailEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-安全排查单-app提交操作
     * * @param body
     */
    public void postCheckSafeInfo(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCheckSafeInfo(body))
                .getHelper().flow(commitCheckSafeInfoEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-安全排查单-app提交操作
     * <p>
     * * @param body
     */
    public void postCheckSafeOpinion(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCheckSafeOpinion(body))
                .getHelper().flow(postCheckSafeOpinionEvent);
        addSubscription(disposable);
    }


    //=====================================================================//
    //===============================清点货物===============================//

    /**
     * 清点货物单-app查询列表
     * <p>
     * * @param body
     */

    public void getCheckGoodList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCheckGoodList(body))
                .getHelper().flow(checkGoodListEvent);
        addSubscription(disposable);
    }


    /**
     * 工程现场-清点货物单-app根据评审id查看详情
     */
    public void getCheckGoodDetailData(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCheckGoodDetailData(id))
                .getHelper().flow(checkGoodDetailEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-清点货物单-app提交操作
     * * @param body
     */
    public void postCheckGoodInfo(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCheckGoodInfo(body))
                .getHelper().flow(postCheckGoodOpinionEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-清点货物单-app提交操作
     * <p>
     * * @param body
     */
    public void postCheckGoodOpinion(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCheckGoodOpinion(body))
                .getHelper().flow(postCheckGoodOpinionEvent);
        addSubscription(disposable);
    }


    //=====================================================================//
    //===============================安装调试===============================//

    /**
     * 安装调试单-app查询列表
     * <p>
     * * @param body
     */

    public void getInstallDebugList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getInstallDebugList(body))
                .getHelper().flow(installDebugListEvent);
        addSubscription(disposable);
    }


    /**
     * 工程现场-安装调试单-app根据评审id查看详情(多个)
     */
    public void getInstallDebugDetailData(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getInstallDebugDetailData(id))
                .getHelper().flow(installDebugDetailEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-安装调试单-app根据评审id查看详情(单个)
     */
    public void getInstallDebugDetailDataBySingle(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getInstallDebugDetailDataBySingle(id))
                .getHelper().flow(installDebugDetailBySingleEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-安装调试单-app提交操作
     * * @param body
     */
    public void postInstallDebugInfo(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postInstallDebugInfo(body))
                .getHelper().flow(commitInstallDebugInfoEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-安装调试单-app提交操作
     * <p>
     * * @param body
     */
    public void postInstallDebugOpinion(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postInstallDebugOpinion(body))
                .getHelper().flow(postInstallDebugOpinionEvent);
        addSubscription(disposable);
    }

    //=====================================================================//
    //===============================客户验收===============================//

    /**
     * 客户验收单-app查询列表
     * <p>
     * * @param body
     */

    public void getCustomerAcceptanceList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCustomerAcceptanceList(body))
                .getHelper().flow(customerAcceptanceListEvent);
        addSubscription(disposable);
    }


    /**
     * 工程现场-客户验收单-app根据评审id查看详情
     */
    public void getCustomerAcceptanceDetailData(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCustomerAcceptanceDetailData(id))
                .getHelper().flow(customerAcceptanceDetailEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-客户验收单-app提交操作
     * * @param body
     */
    public void postCustomerAcceptanceInfo(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCustomerAcceptanceInfo(body))
                .getHelper().flow(commitCustomerAcceptanceInfoEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-客户验收单-app提交操作
     * <p>
     * * @param body
     */
    public void postCustomerAcceptanceOpinion(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCustomerAcceptanceOpinion(body))
                .getHelper().flow(postCustomerAcceptanceOpinionEvent);
        addSubscription(disposable);
    }

    public FocusProjectSiteRepository getRepository() {
        return repository;
    }

    public void setRepository(FocusProjectSiteRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ProjectOverViewBean> getProjectSiteOverViewEvent() {
        return projectSiteOverViewEvent;
    }

    public void setProjectSiteOverViewEvent(MutableLiveData<ProjectOverViewBean> projectSiteOverViewEvent) {
        this.projectSiteOverViewEvent = projectSiteOverViewEvent;
    }

    public MutableLiveData<List<ProjectSiteStatusBean>> getProjectSiteStatusEvent() {
        return projectSiteStatusEvent;
    }

    public void setProjectSiteStatusEvent(MutableLiveData<List<ProjectSiteStatusBean>> projectSiteStatusEvent) {
        this.projectSiteStatusEvent = projectSiteStatusEvent;
    }

    public MutableLiveData<RefreshBean<SiteExploreBean>> getSiteExploreListEvent() {
        return siteExploreListEvent;
    }

    public void setSiteExploreListEvent(MutableLiveData<RefreshBean<SiteExploreBean>> siteExploreListEvent) {
        this.siteExploreListEvent = siteExploreListEvent;
    }

    public MutableLiveData<SiteExploreBean> getSiteExploreDetailEvent() {
        return siteExploreDetailEvent;
    }

    public void setSiteExploreDetailEvent(MutableLiveData<SiteExploreBean> siteExploreDetailEvent) {
        this.siteExploreDetailEvent = siteExploreDetailEvent;
    }

    public MutableLiveData<Integer> getCommitSiteExploreInfoEvent() {
        return commitSiteExploreInfoEvent;
    }

    public void setCommitSiteExploreInfoEvent(MutableLiveData<Integer> commitSiteExploreInfoEvent) {
        this.commitSiteExploreInfoEvent = commitSiteExploreInfoEvent;
    }

    public MutableLiveData<Integer> getPostSiteExploreOpinionEvent() {
        return postSiteExploreOpinionEvent;
    }

    public void setPostSiteExploreOpinionEvent(MutableLiveData<Integer> postSiteExploreOpinionEvent) {
        this.postSiteExploreOpinionEvent = postSiteExploreOpinionEvent;
    }

    public MutableLiveData<RefreshBean<SiteExploreBean>> getCheckSafeListEvent() {
        return checkSafeListEvent;
    }

    public void setCheckSafeListEvent(MutableLiveData<RefreshBean<SiteExploreBean>> checkSafeListEvent) {
        this.checkSafeListEvent = checkSafeListEvent;
    }

    public MutableLiveData<SiteExploreBean> getCheckSafeDetailEvent() {
        return checkSafeDetailEvent;
    }

    public void setCheckSafeDetailEvent(MutableLiveData<SiteExploreBean> checkSafeDetailEvent) {
        this.checkSafeDetailEvent = checkSafeDetailEvent;
    }

    public MutableLiveData<Integer> getCommitCheckSafeInfoEvent() {
        return commitCheckSafeInfoEvent;
    }

    public void setCommitCheckSafeInfoEvent(MutableLiveData<Integer> commitCheckSafeInfoEvent) {
        this.commitCheckSafeInfoEvent = commitCheckSafeInfoEvent;
    }

    public MutableLiveData<Integer> getPostCheckSafeOpinionEvent() {
        return postCheckSafeOpinionEvent;
    }

    public void setPostCheckSafeOpinionEvent(MutableLiveData<Integer> postCheckSafeOpinionEvent) {
        this.postCheckSafeOpinionEvent = postCheckSafeOpinionEvent;
    }

    public MutableLiveData<RefreshBean<SiteExploreBean>> getCheckGoodListEvent() {
        return checkGoodListEvent;
    }

    public void setCheckGoodListEvent(MutableLiveData<RefreshBean<SiteExploreBean>> checkGoodListEvent) {
        this.checkGoodListEvent = checkGoodListEvent;
    }

    public MutableLiveData<SiteExploreBean> getCheckGoodDetailEvent() {
        return checkGoodDetailEvent;
    }

    public void setCheckGoodDetailEvent(MutableLiveData<SiteExploreBean> checkGoodDetailEvent) {
        this.checkGoodDetailEvent = checkGoodDetailEvent;
    }

    public MutableLiveData<Integer> getCommitCheckGoodInfoEvent() {
        return commitCheckGoodInfoEvent;
    }

    public void setCommitCheckGoodInfoEvent(MutableLiveData<Integer> commitCheckGoodInfoEvent) {
        this.commitCheckGoodInfoEvent = commitCheckGoodInfoEvent;
    }

    public MutableLiveData<Integer> getPostCheckGoodOpinionEvent() {
        return postCheckGoodOpinionEvent;
    }

    public void setPostCheckGoodOpinionEvent(MutableLiveData<Integer> postCheckGoodOpinionEvent) {
        this.postCheckGoodOpinionEvent = postCheckGoodOpinionEvent;
    }

    public MutableLiveData<RefreshBean<SiteExploreBean>> getInstallDebugListEvent() {
        return installDebugListEvent;
    }

    public void setInstallDebugListEvent(MutableLiveData<RefreshBean<SiteExploreBean>> installDebugListEvent) {
        this.installDebugListEvent = installDebugListEvent;
    }

    public MutableLiveData<SiteExploreBean> getInstallDebugDetailEvent() {
        return installDebugDetailEvent;
    }

    public void setInstallDebugDetailEvent(MutableLiveData<SiteExploreBean> installDebugDetailEvent) {
        this.installDebugDetailEvent = installDebugDetailEvent;
    }

    public MutableLiveData<Integer> getCommitInstallDebugInfoEvent() {
        return commitInstallDebugInfoEvent;
    }

    public void setCommitInstallDebugInfoEvent(MutableLiveData<Integer> commitInstallDebugInfoEvent) {
        this.commitInstallDebugInfoEvent = commitInstallDebugInfoEvent;
    }

    public MutableLiveData<Integer> getPostInstallDebugOpinionEvent() {
        return postInstallDebugOpinionEvent;
    }

    public void setPostInstallDebugOpinionEvent(MutableLiveData<Integer> postInstallDebugOpinionEvent) {
        this.postInstallDebugOpinionEvent = postInstallDebugOpinionEvent;
    }

    public MutableLiveData<RefreshBean<SiteExploreBean>> getCustomerAcceptanceListEvent() {
        return customerAcceptanceListEvent;
    }

    public MutableLiveData<SiteExploreBean> getInstallDebugDetailBySingleEvent() {
        return installDebugDetailBySingleEvent;
    }

    public void setInstallDebugDetailBySingleEvent(MutableLiveData<SiteExploreBean> installDebugDetailBySingleEvent) {
        this.installDebugDetailBySingleEvent = installDebugDetailBySingleEvent;
    }

    public void setCustomerAcceptanceListEvent(MutableLiveData<RefreshBean<SiteExploreBean>> customerAcceptanceListEvent) {
        this.customerAcceptanceListEvent = customerAcceptanceListEvent;
    }

    public MutableLiveData<SiteExploreBean> getCustomerAcceptanceDetailEvent() {
        return customerAcceptanceDetailEvent;
    }

    public void setCustomerAcceptanceDetailEvent(MutableLiveData<SiteExploreBean> customerAcceptanceDetailEvent) {
        this.customerAcceptanceDetailEvent = customerAcceptanceDetailEvent;
    }

    public MutableLiveData<Integer> getCommitCustomerAcceptanceInfoEvent() {
        return commitCustomerAcceptanceInfoEvent;
    }

    public void setCommitCustomerAcceptanceInfoEvent(MutableLiveData<Integer> commitCustomerAcceptanceInfoEvent) {
        this.commitCustomerAcceptanceInfoEvent = commitCustomerAcceptanceInfoEvent;
    }

    public MutableLiveData<Integer> getPostCustomerAcceptanceOpinionEvent() {
        return postCustomerAcceptanceOpinionEvent;
    }

    public void setPostCustomerAcceptanceOpinionEvent(MutableLiveData<Integer> postCustomerAcceptanceOpinionEvent) {
        this.postCustomerAcceptanceOpinionEvent = postCustomerAcceptanceOpinionEvent;
    }
}
