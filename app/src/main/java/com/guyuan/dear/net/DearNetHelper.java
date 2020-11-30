package com.guyuan.dear.net;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.BasePageResultBean;
import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.rx.SchedulersCompat;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.db.DearDbManager;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.focus.contract.bean.ComContractsBean;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.focus.contract.bean.ContractStatusFlowBean;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.bean.HrSummaryBean;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.beans.MaterialQcReportDetail;
import com.guyuan.dear.focus.qc.beans.ProductQcReportDetail;
import com.guyuan.dear.focus.qc.beans.QcSummaryBean;
import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;
import com.guyuan.dear.net.api.DearNetApiService;
import com.guyuan.dear.net.reqBean.ClockInRqBody;
import com.guyuan.dear.net.reqBean.ContractApplyBody;
import com.guyuan.dear.net.reqBean.SearchRqBody;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetBaseProjectBean;
import com.guyuan.dear.net.resultBeans.NetBaseQcBean;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetClockInConfig;
import com.guyuan.dear.net.resultBeans.NetContractDetailInfo;
import com.guyuan.dear.net.resultBeans.NetContractInfo;
import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;
import com.guyuan.dear.net.resultBeans.NetContractSumBean;
import com.guyuan.dear.net.resultBeans.NetHrSummary;
import com.guyuan.dear.net.resultBeans.NetIdAndStatusMapping;
import com.guyuan.dear.net.resultBeans.NetMaterialBean;
import com.guyuan.dear.net.resultBeans.NetProductInfo;
import com.guyuan.dear.net.resultBeans.NetQcApproach;
import com.guyuan.dear.net.resultBeans.NetQcReportApproveFlow;
import com.guyuan.dear.net.resultBeans.NetQcReportDetailBean;
import com.guyuan.dear.net.resultBeans.NetQcSummaryBean;
import com.guyuan.dear.net.resultBeans.NetSearchContactInfo;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.net.resultBeans.NetStaffAttendStatus;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.net.resultBeans.NetVerifyFlowBean;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.MyApplyDetailBean;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.beans.MaterialInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 14:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DearNetHelper {
    private static DearNetHelper instance;
    private final DearNetApiService netApiService;

    private DearNetHelper() {
        netApiService = DearApplication.getInstance().getRetrofit().create(DearNetApiService.class);
    }

    public static DearNetHelper getInstance() {
        if (instance == null) {
            synchronized (DearNetHelper.class) {
                if (instance == null) {
                    instance = new DearNetHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 获取所有人员
     *
     * @param pageIndex      分页页码，从1开始
     * @param pageSize       分页容量
     * @param lastUpdateTime 上次更新时间，服务器返回这个时间段后改动的人员清单。如果为空，则返回所有人员。
     * @param callback       回调
     * @return
     */
    public Disposable getAllStaffs(int pageIndex, int pageSize, long lastUpdateTime, NetCallback<BasePageResultBean<NetStaffBean>> callback) {
        BasePageReqBean body = new BasePageReqBean();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        body.setUpdateTime(CalenderUtils.getInstance().toSmartFactoryDateStringFormat(lastUpdateTime));
        Observable<ResultBean<BasePageResultBean<NetStaffBean>>> observable = netApiService.getAllStaffs(body);
        return getDisposal(observable, callback, null);
    }

    /**
     * 获取所有客户清单
     *
     * @param callback
     * @return
     */
    public Disposable getClientList(NetCallback<BasePageResultBean<NetClientInfo>> callback) {
        BasePageReqBean body = new BasePageReqBean();
        body.setPageNum(0);
        body.setPageSize(-1);
        Observable<ResultBean<BasePageResultBean<NetClientInfo>>> observable = netApiService.getClientInfos(body);
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 根据客户id获取客户合同列表，列表信息为基本信息。
     *
     * @param id
     * @param callback
     * @return
     */
    public Disposable getBaseContractListByClientId(long id, NetCallback<List<NetBaseContractInfo>> callback) {
        Observable<ResultBean<List<NetBaseContractInfo>>> observable = netApiService.getContractBaseInfosByClientId(id);
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 获取判断维度列表
     *
     * @param callback
     * @return
     */
    public Disposable getJudgeConditions(NetCallback<List<NetServerParam.JudgeCondition>> callback) {
        Observable<ResultBean<NetServerParam>> observable = netApiService.getNetServerParams();
        Mapper<NetServerParam, List<NetServerParam.JudgeCondition>> mapper =
                new Mapper<NetServerParam, List<NetServerParam.JudgeCondition>>() {
                    @Override
                    public List<NetServerParam.JudgeCondition> map(NetServerParam src) {
                        return src.getJudgeConditions();
                    }
                };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 提交合同暂停/重启申请
     *
     * @param bean     提交内容
     * @param callback
     * @return
     */
    public Disposable submitContractApply(ContractApplyBean bean, NetCallback<Integer> callback) {
        ContractApplyBody body = new ContractApplyBody(bean);
        Observable<ResultBean<Integer>> observable = netApiService.submitContractApply(body);
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 查询合同概况
     *
     * @param date 截至日期
     * @return
     */
    public Disposable getContractSumByDate(long date, NetCallback<ComContractsBean> callback) {
        Observable<ResultBean<NetContractSumBean>> observable =
                netApiService.getContractSumByDate(CalenderUtils.getInstance().toSmartFactoryDateStringFormat(date));
        Mapper<NetContractSumBean, ComContractsBean> mapper = new Mapper<NetContractSumBean, ComContractsBean>() {
            @Override
            public ComContractsBean map(NetContractSumBean src) {
                ComContractsBean bean = new ComContractsBean();
                bean.setClientTotal(src.getCusNum());
                bean.setContractsTotal(src.getContNum());
                bean.setExceptionContracts(src.getExceptionCount());
                bean.setExecutingContracts(src.getExecutingCount());
                bean.setFinishedContracts(src.getCompleteNum());
                bean.setNewContracts(src.getThisYearNum());
                bean.setPreAnnualDelivers(src.getLastYearNum());
                return bean;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据类型和日期获取合同列表
     *
     * @param type     {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_EXCEPTION_CONTRACTS}
     *                 {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_EXECUTING_CONTRACTS}
     *                 {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_FINISHED_CONTRACTS}
     *                 {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_NEW_CONTRACTS}
     *                 {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_PRE_ANNUAL_DELIVERS}
     *                 {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_UNFINISHED_CONTRACTS}
     * @param date
     * @param callback
     * @return
     */
    public Disposable getContractListByTypeAndDate(int type, long date, int pageIndex, int pageSize, NetCallback<List<BaseContractBean>> callback) {
        SearchRqBody body = new SearchRqBody();
        HashMap<String, String> filters = new HashMap<>(3);
        filters.put("name", "");
        filters.put("type", String.valueOf(type));
        filters.put("time", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(date));
        body.setFilters(filters);
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Observable<ResultBean<BasePageResultBean<NetSearchContactInfo>>> observable = netApiService.getContractListByTypeAndDate(body);
        Mapper<BasePageResultBean<NetSearchContactInfo>, List<BaseContractBean>> mapper = new Mapper<BasePageResultBean<NetSearchContactInfo>, List<BaseContractBean>>() {
            @Override
            public List<BaseContractBean> map(BasePageResultBean<NetSearchContactInfo> netResult) {
                List<NetSearchContactInfo> src = netResult.getContent();
                List<BaseContractBean> result = new ArrayList<>();
                for (NetSearchContactInfo info : src) {
                    BaseContractBean bean = new BaseContractBean(info);
                    result.add(bean);
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);

    }

    /**
     * 获取所有合同异常列表
     *
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getPauseContractList(int pageIndex, int pageSize, NetCallback<List<BaseContractExcptBean>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageSize(pageSize);
        body.setPageNum(pageIndex);
        body.setFindType(1);
        body.setType(1);
        Observable<ResultBean<BasePageResultBean<NetContractInfo>>> observable = netApiService.getContractApplyList(body);
        Mapper<BasePageResultBean<NetContractInfo>, List<BaseContractExcptBean>> mapper =
                new Mapper<BasePageResultBean<NetContractInfo>, List<BaseContractExcptBean>>() {
                    @Override
                    public List<BaseContractExcptBean> map(BasePageResultBean<NetContractInfo> src) {
                        List<BaseContractExcptBean> result = new ArrayList<>();
                        List<NetContractInfo> content = src.getContent();
                        for (NetContractInfo temp : content) {
                            BaseContractExcptBean bean = new BaseContractExcptBean(temp);
                            result.add(bean);
                        }
                        return result;
                    }
                };

        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 获取所有合同重启列表
     *
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getRestartedContractList(int pageIndex, int pageSize, NetCallback<List<RestartedContractBean>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageSize(pageSize);
        body.setPageNum(pageIndex);
        body.setFindType(1);
        body.setType(2);
        Observable<ResultBean<BasePageResultBean<NetContractInfo>>> observable = netApiService.getContractApplyList(body);
        Mapper<BasePageResultBean<NetContractInfo>, List<RestartedContractBean>> mapper =
                new Mapper<BasePageResultBean<NetContractInfo>, List<RestartedContractBean>>() {
                    @Override
                    public List<RestartedContractBean> map(BasePageResultBean<NetContractInfo> src) {
                        List<RestartedContractBean> result = new ArrayList<>();
                        List<NetContractInfo> content = src.getContent();
                        for (NetContractInfo temp : content) {
                            RestartedContractBean bean = new RestartedContractBean(temp);
                            result.add(bean);
                        }
                        return result;
                    }
                };

        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 获取合同暂停申请的详情
     *
     * @param examineId
     * @param callback
     * @return
     */
    public Disposable getContractApplyDetail(int examineId, NetCallback<DetailContractApplyBean> callback) {
        Observable<ResultBean<NetContractStatusDetail>> observable = netApiService.getContractStatusDetail(examineId);
        Mapper<NetContractStatusDetail, DetailContractApplyBean> mapper = new Mapper<NetContractStatusDetail, DetailContractApplyBean>() {
            @Override
            public DetailContractApplyBean map(NetContractStatusDetail src) {
                return new DetailContractApplyBean(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据合同编号获取合同详细信息
     *
     * @param contractId
     * @return
     */
    public Disposable getContractDetail(int contractId, NetCallback<DetailContractBean> callback) {
        Observable<ResultBean<NetContractDetailInfo>> observable = netApiService.getContractDetailById(contractId);
        Mapper<NetContractDetailInfo, DetailContractBean> mapper = new Mapper<NetContractDetailInfo, DetailContractBean>() {
            @Override
            public DetailContractBean map(NetContractDetailInfo src) {
                return new DetailContractBean(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据合同id查询跟进
     *
     * @param contractId
     * @param callback
     * @return
     */
    public Disposable getVerifyFlowByContractId(int contractId, NetCallback<List<ContractComment>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(0);
        body.setPageSize(-1);
        Map<String, String> filters = new HashMap<>();
        filters.put("id", String.valueOf(contractId));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetVerifyFlowBean>>> observable = netApiService.getVerifyFlowById(body);
        Mapper<BasePageResultBean<NetVerifyFlowBean>, List<ContractComment>> mapper = new Mapper<BasePageResultBean<NetVerifyFlowBean>, List<ContractComment>>() {
            @Override
            public List<ContractComment> map(BasePageResultBean<NetVerifyFlowBean> src) {
                List<NetVerifyFlowBean> content = src.getContent();
                List<ContractComment> result = new ArrayList<>();
                for (NetVerifyFlowBean bean : content) {
                    ContractComment comment = new ContractComment(bean);
                    result.add(comment);
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);

    }

    /**
     * 获取用户申请的合同暂停列表
     *
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getMyPauseApplyList(int pageIndex, int pageSize, NetCallback<List<MyApplyBean>> callback) {
        return getMyApplyList(pageIndex, pageSize, 1, callback);
    }


    /**
     * 获取用户申请的合同重启列表
     *
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getMyRestartApplyList(int pageIndex, int pageSize, NetCallback<List<MyApplyBean>> callback) {
        return getMyApplyList(pageIndex, pageSize, 2, callback);
    }

    private Disposable getMyApplyList(int pageIndex, int pageSize, int type, NetCallback<List<MyApplyBean>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        body.setFindType(2);
        body.setType(type);
        Observable<ResultBean<BasePageResultBean<NetContractInfo>>> observable = netApiService.getContractApplyList(body);
        Mapper<BasePageResultBean<NetContractInfo>, List<MyApplyBean>> mapper = new Mapper<BasePageResultBean<NetContractInfo>, List<MyApplyBean>>() {
            @Override
            public List<MyApplyBean> map(BasePageResultBean<NetContractInfo> src) {
                List<MyApplyBean> result = new ArrayList<>();
                List<NetContractInfo> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetContractInfo info : content) {
                        MyApplyBean apply = new MyApplyBean(info);
                        result.add(apply);
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 获取我的合同重启/暂停申请详情信息
     *
     * @param examineId
     * @param callback
     * @return
     */
    public Disposable getMyApplyDetailFromNet(int examineId, NetCallback<MyApplyDetailBean> callback) {
        Observable<ResultBean<NetContractStatusDetail>> observable = netApiService.getContractStatusDetail(examineId);
        Mapper<NetContractStatusDetail, MyApplyDetailBean> mapper = new Mapper<NetContractStatusDetail, MyApplyDetailBean>() {
            @Override
            public MyApplyDetailBean map(NetContractStatusDetail src) {
                MyApplyDetailBean bean = new MyApplyDetailBean(src);
                return bean;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 获取合同概况
     *
     * @param dateFrom
     * @param dateTo
     * @param callback
     * @return
     */
    public Disposable getQcSummary(long dateFrom, long dateTo, NetCallback<QcSummaryBean> callback) {
        String startTime = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(dateFrom);
        String endTime = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(dateTo);
        Observable<ResultBean<NetQcSummaryBean>> observable = netApiService.getQcSummary(startTime, endTime);
        Mapper<NetQcSummaryBean, QcSummaryBean> mapper = new Mapper<NetQcSummaryBean, QcSummaryBean>() {
            @Override
            public QcSummaryBean map(NetQcSummaryBean src) {
                QcSummaryBean result = new QcSummaryBean(src);
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getMaterialQcPassList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "3");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        int qualityResult = bean.getQualityResult();
                        if (productType == 1 && qualityResult == 1) {
                            BaseMaterialQcReport report = new BaseMaterialQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getMaterialQcRejectList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "2");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        int qualityResult = bean.getQualityResult();
                        if (productType == 1 && qualityResult == 2) {
                            BaseMaterialQcReport report = new BaseMaterialQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getProductQcRejectList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "2");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        int qualityResult = bean.getQualityResult();
                        if (productType == 2 && qualityResult == 2) {
                            BaseProductQcReport report = new BaseProductQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getAllRejectedQcList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "2");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        if (productType == 2) {
                            BaseProductQcReport report = new BaseProductQcReport(bean);
                            result.add(report.toGenericQcReport());
                        } else if (productType == 1) {
                            BaseMaterialQcReport report = new BaseMaterialQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getAllTypesOfQcList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "1");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        if (productType == 2) {
                            BaseProductQcReport report = new BaseProductQcReport(bean);
                            result.add(report.toGenericQcReport());
                        } else if (productType == 1) {
                            BaseMaterialQcReport report = new BaseMaterialQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getProductQcPassList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "3");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        int qualityResult = bean.getQualityResult();
                        if (productType == 2 && qualityResult == 1) {
                            BaseProductQcReport report = new BaseProductQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param callback
     * @return
     */
    public Disposable getMyQcReportList(int pageIndex, int pageSize, long startTime, long endTime, NetCallback<List<GenericQcReport>> callback) {
        SearchRqBody body = new SearchRqBody();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        Map<String, String> filters = new HashMap<>();
        //listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表
        filters.put("listType", "4");
        filters.put("startTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(startTime));
        filters.put("endTime", CalenderUtils.getInstance().toSmartFactoryDateStringFormat(endTime));
        body.setFilters(filters);
        Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> observable = netApiService.getBaseQcListByType(body);
        Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>> mapper
                = new Mapper<BasePageResultBean<NetBaseQcBean>, List<GenericQcReport>>() {
            @Override
            public List<GenericQcReport> map(BasePageResultBean<NetBaseQcBean> src) {
                List<GenericQcReport> result = new ArrayList<>();
                List<NetBaseQcBean> content = src.getContent();
                if (content != null && !content.isEmpty()) {
                    for (NetBaseQcBean bean : content) {
                        int productType = bean.getProductType();
                        if (productType == 2) {
                            BaseProductQcReport report = new BaseProductQcReport(bean);
                            result.add(report.toGenericQcReport());
                        } else if (productType == 1) {
                            BaseMaterialQcReport report = new BaseMaterialQcReport(bean);
                            result.add(report.toGenericQcReport());
                        }
                    }
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 获取成品qc报告详情
     *
     * @param id       报告id
     * @param callback
     * @return
     */
    public Disposable getProductQcReportDetailById(int id, NetCallback<ProductQcReportDetail> callback) {
        Observable<ResultBean<NetQcReportDetailBean>> observable = netApiService.getQcReportDetailById(id);
        Mapper<NetQcReportDetailBean, ProductQcReportDetail> mapper = new Mapper<NetQcReportDetailBean, ProductQcReportDetail>() {
            @Override
            public ProductQcReportDetail map(NetQcReportDetailBean src) {
                return new ProductQcReportDetail(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 获取原材料qc报告详情
     *
     * @param id
     * @param callback
     * @return
     */
    public Disposable getMaterialQcReportDetailById(int id, NetCallback<MaterialQcReportDetail> callback) {
        Observable<ResultBean<NetQcReportDetailBean>> observable = netApiService.getQcReportDetailById(id);
        Mapper<NetQcReportDetailBean, MaterialQcReportDetail> mapper = new Mapper<NetQcReportDetailBean, MaterialQcReportDetail>() {
            @Override
            public MaterialQcReportDetail map(NetQcReportDetailBean src) {
                return new MaterialQcReportDetail(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据ID获取QC报告详情下面的审批历史
     *
     * @param id
     * @param callback
     * @return
     */
    public Disposable getQcApproveFlowById(int id, NetCallback<List<GenericQcLogBean>> callback) {
        Observable<ResultBean<List<NetQcReportApproveFlow>>> observable = netApiService.getQcReportApproveFlow(id);
        Mapper<List<NetQcReportApproveFlow>, List<GenericQcLogBean>> mapper = new Mapper<List<NetQcReportApproveFlow>, List<GenericQcLogBean>>() {
            @Override
            public List<GenericQcLogBean> map(List<NetQcReportApproveFlow> src) {
                List<GenericQcLogBean> result = new ArrayList<>();
                for (NetQcReportApproveFlow flow : src) {
                    GenericQcLogBean bean = new GenericQcLogBean(flow);
                    result.add(bean);
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据合同id获取合同状态流程图
     *
     * @param contractId
     * @param callback
     * @return
     */
    public Disposable getContractStatusFlow(int contractId, NetCallback<ContractStatusFlowBean> callback) {
        Observable<ResultBean<NetContractStatusFlow>> observable = netApiService.getContractStatusFlowById(contractId);
        Mapper<NetContractStatusFlow, ContractStatusFlowBean> mapper = new Mapper<NetContractStatusFlow, ContractStatusFlowBean>() {
            @Override
            public ContractStatusFlowBean map(NetContractStatusFlow src) {
                return new ContractStatusFlowBean(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 获取所有项目的清单
     *
     * @param callback
     * @return
     */
    public Disposable getBaseProjectList(NetCallback<List<BaseProjectBean>> callback) {
        Observable<ResultBean<List<NetBaseProjectBean>>> observable = netApiService.getBaseProjectList();
        Mapper<List<NetBaseProjectBean>, List<BaseProjectBean>> mapper = new Mapper<List<NetBaseProjectBean>, List<BaseProjectBean>>() {
            @Override
            public List<BaseProjectBean> map(List<NetBaseProjectBean> src) {
                List<BaseProjectBean> result = new ArrayList<>();
                for (NetBaseProjectBean bean : src) {
                    result.add(new BaseProjectBean(bean));
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据项目ID获取材料清单
     *
     * @param projectId
     * @param callback
     * @return
     */
    public Disposable getMaterialListByProject(int projectId, NetCallback<List<MaterialInfo>> callback) {
        Observable<ResultBean<List<NetMaterialBean>>> observable = netApiService.getMaterialListByProjectId(projectId);
        Mapper<List<NetMaterialBean>, List<MaterialInfo>> mapper = new Mapper<List<NetMaterialBean>, List<MaterialInfo>>() {
            @Override
            public List<MaterialInfo> map(List<NetMaterialBean> src) {
                List<MaterialInfo> infos = new ArrayList<>();
                for (NetMaterialBean bean : src) {
                    infos.add(new MaterialInfo(bean));
                }
                return infos;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 获取质检抽查方式
     *
     * @param callback
     * @return
     */
    public Disposable getQcApproaches(NetCallback<List<BaseQcApproachBean>> callback) {
        Observable<ResultBean<List<NetQcApproach>>> observable = netApiService.getQcApproaches();
        Mapper<List<NetQcApproach>, List<BaseQcApproachBean>> mapper = new Mapper<List<NetQcApproach>, List<BaseQcApproachBean>>() {
            @Override
            public List<BaseQcApproachBean> map(List<NetQcApproach> src) {
                List<BaseQcApproachBean> result = new ArrayList<>();
                for (NetQcApproach approach : src) {
                    BaseQcApproachBean bean = new BaseQcApproachBean(approach);
                    result.add(bean);
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 提交原材料/成品的质检报告
     *
     * @param body
     * @param callback
     * @return
     */
    public Disposable submitQcReport(SubmitQcReportBody body, NetCallback<Integer> callback) {
        Observable<ResultBean<Integer>> observable = netApiService.submitQcReport(body);
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 根据项目id获取产品列表
     *
     * @param projectId
     * @param callback
     * @return
     */
    public Disposable getProductListByProjectId(int projectId, NetCallback<List<BaseProductBatchInfo>> callback) {
        Observable<ResultBean<List<NetProductInfo>>> observable = netApiService.getProductListByProjectId(projectId);
        Mapper<List<NetProductInfo>, List<BaseProductBatchInfo>> mapper = new Mapper<List<NetProductInfo>, List<BaseProductBatchInfo>>() {
            @Override
            public List<BaseProductBatchInfo> map(List<NetProductInfo> src) {
                List<BaseProductBatchInfo> result = new ArrayList<>();
                for (NetProductInfo info : src) {
                    result.add(new BaseProductBatchInfo(info));
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 获取今日人员出勤概况
     *
     * @param callback
     * @return
     */
    public Disposable getHrSummary(NetCallback<HrSummaryBean> callback) {
        Observable<ResultBean<NetHrSummary>> observable = netApiService.getHrSummary();
        Mapper<NetHrSummary, HrSummaryBean> mapper = new Mapper<NetHrSummary, HrSummaryBean>() {
            @Override
            public HrSummaryBean map(NetHrSummary src) {
                return new HrSummaryBean(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 获取我的打卡状态和上下班时间，地点等配置
     *
     * @param callback
     * @return
     */
    public Disposable getClockInConfig(NetCallback<NetClockInConfig> callback) {
        Observable<ResultBean<NetClockInConfig>> observable = netApiService.getClockInConfig();
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 上下班打卡
     *
     * @param clockInType {@link ClockInRqBody#CLOCK_IN_TYPE_CLOCK_IN_INSIDE_COMPANY_AREA}
     *                    {@link ClockInRqBody#CLOCK_IN_TYPE_CLOCK_IN_OUTSIDE_COMPANY_AREA}
     *                    {@link ClockInRqBody#CLOCK_IN_TYPE_CHECK_OUT_INSIDE_COMPANY_AREA}
     *                    {@link ClockInRqBody#CLOCK_IN_TYPE_CHECK_OUT_OUTSIDE_COMPANY_AREA}
     * @param lat         纬度
     * @param lng         经度
     * @return
     */
    public Disposable clockIn(int clockInType, double lat, double lng, NetCallback<Boolean> callback) {
        ClockInRqBody body = new ClockInRqBody();
        body.setClockType(clockInType);
        body.setGpsLatitude(lat);
        body.setGpsLongitude(lng);
        Observable<ResultBean<Integer>> observable = netApiService.clockIn(body);
        Mapper<Integer, Boolean> mapper = new Mapper<Integer, Boolean>() {
            @Override
            public Boolean map(Integer src) {
                //返回1表示成功
                return src >= 1;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 根据当天出勤状况获取人员id列表
     *
     * @return
     */
    public Disposable getStaffByAttendanceType(int type, NetCallback<List<StaffBean>> callback) {
        Observable<ResultBean<List<Integer>>> observable = netApiService.getStaffIdsByAttendantType(type);
        Mapper<List<Integer>, List<StaffBean>> mapper = new Mapper<List<Integer>, List<StaffBean>>() {
            @Override
            public List<StaffBean> map(List<Integer> src) {
                List<StaffEntity> staffEntities = DearDbManager.getInstance().getDataBase().getStaffDao().getStaffsById(src);
                List<StaffBean> result = new ArrayList<>();
                for (StaffEntity entity : staffEntities) {
                    result.add(new StaffBean(entity));
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 根据指定日期和出勤状况类型获取人员id列表
     *
     * @param type {@link HrStatusGroup#GRP_TYPE_LATE},{@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY},
     *             {@link HrStatusGroup#GRP_TYPE_ABSENT},{@link HrStatusGroup#GRP_TYPE_ON_LEAVE}
     * @return
     */
    public Disposable getStaffsByDateAndAttendanceType(long date, int type, NetCallback<List<StaffBean>> callback) {
        String yearAndMonth = CalenderUtils.getInstance().toLongYearAndMonth(date);
        Observable<ResultBean<List<Integer>>> observable = netApiService.getHrAbnormalListByTypeAndDate(yearAndMonth, type);
        Mapper<List<Integer>, List<StaffBean>> mapper = new Mapper<List<Integer>, List<StaffBean>>() {
            @Override
            public List<StaffBean> map(List<Integer> src) {
                List<StaffEntity> staffEntities = DearDbManager.getInstance().getDataBase().getStaffDao().getStaffsById(src);
                List<StaffBean> result = new ArrayList<>();
                for (StaffEntity entity : staffEntities) {
                    result.add(new StaffBean(entity));
                }
                return result;
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 获取员工当月出勤概况（上半部情况）
     *
     * @param staffId
     * @param callback
     * @return
     */
    public Disposable getStaffAttendStatus(int staffId, NetCallback<NetStaffAttendStatus> callback) {
        Observable<ResultBean<NetStaffAttendStatus>> observable = netApiService.getStaffAttendStatus(staffId);
        return getDisposalAsync(observable, callback, null);
    }


    /**
     * 获取员工当月出勤概况（下半部情况）
     *
     * @param yearMonth yyyy-MM
     * @param staffId
     * @param callback
     * @return
     */
    public Disposable getStaffAttendRecord(String yearMonth, int staffId, NetCallback<List<NetStaffAttendRecord>> callback) {
        Observable<ResultBean<List<NetStaffAttendRecord>>> observable = netApiService.getStaffAttendRecord(yearMonth, staffId);
        return getDisposalAsync(observable, callback, null);
    }


    /**
     * 根据月份查询异常的人数
     *
     * @param yearMonth
     * @param callback
     * @return
     */
    public Disposable getHrAbnormalSumByDate(String yearMonth, NetCallback<HrSummaryBean> callback) {
        Observable<ResultBean<NetHrSummary>> observable = netApiService.getHrAbnormalSumByDate(yearMonth);
        Mapper<NetHrSummary, HrSummaryBean> mapper = new Mapper<NetHrSummary, HrSummaryBean>() {
            @Override
            public HrSummaryBean map(NetHrSummary src) {
                return new HrSummaryBean(src);
            }
        };
        return getDisposalAsync(observable, callback, mapper);
    }


    /**
     * 获取所有的id和当前出勤状况的映射表
     * @param callback
     * @return
     */
    public Disposable getStaffIdAndAttendStatusMapping(NetCallback<List<NetIdAndStatusMapping>> callback){
        Observable<ResultBean<List<NetIdAndStatusMapping>>> observable = netApiService.getStaffIdAndAttendStatus();
        return getDisposalAsync(observable, callback, null);
    }


    /*************************************底层方法分界线******************************************/
    public interface NetCallback<T> {
        void onStart(Disposable disposable);

        void onGetResult(T result);

        void onError(Throwable error);
    }

    public interface Mapper<FROM, TO> {
        TO map(FROM src);
    }

    /**
     * @param mapper         把Data类转换成ReturnType的适配器
     * @param <Response>     网络请求的返回类型，必须继承ResultBean
     * @param <ResponseData> 必须为Response的变量data代表的类，否则会报错
     * @param <ResultClass>  最终返回类型，可以是Data，也可以是经过Data转化过的其他类
     */
    private <Response extends ResultBean, ResponseData, ResultClass> Disposable getDisposalAsync(
            Observable<Response> observable, @Nullable NetCallback<ResultClass> callback,
            @Nullable Mapper<ResponseData, ResultClass> mapper) {

        return observable.compose(SchedulersCompat.applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(disposable);
                        }
                    }
                }).subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object obj) throws Exception {
                        if (callback == null) {
                            return;
                        }
                        if (mapper != null) {
                            ResponseData responseData = (ResponseData) obj;
                            callback.onGetResult(mapper.map(responseData));
                        } else {
                            ResultClass data = (ResultClass) obj;
                            callback.onGetResult(data);
                        }
                    }
                }, new ErrorResultBean() {
                    @Override
                    protected void onError(ErrorBean errorBean) {
                        if (callback != null) {
                            Throwable throwable = new Throwable(errorBean.getErrorResult());
                            callback.onError(throwable);
                        }

                    }
                });
    }

    /**
     * 注意：这个回调是在子线程中的，不能在回调中修改UI
     *
     * @param mapper         把Data类转换成ReturnType的适配器
     * @param <Response>     网络请求的返回类型，必须继承ResultBean
     * @param <ResponseData> 必须为Response的变量data代表的类，否则会报错
     * @param <ResultClass>  最终返回类型，可以是Data，也可以是经过Data转化过的其他类
     */
    private <Response extends ResultBean, ResponseData, ResultClass> Disposable getDisposal(
            Observable<Response> observable, @Nullable NetCallback<ResultClass> callback,
            @Nullable Mapper<ResponseData, ResultClass> mapper) {

        return observable.compose(SchedulersCompat.applyIoSchedulers())
                .observeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(disposable);
                        }
                    }
                }).subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object obj) throws Exception {
                        if (callback == null) {
                            return;
                        }
                        if (mapper != null) {
                            ResponseData responseData = (ResponseData) obj;
                            callback.onGetResult(mapper.map(responseData));
                        } else {
                            ResultClass data = (ResultClass) obj;
                            callback.onGetResult(data);
                        }
                    }
                }, new ErrorResultBean() {
                    @Override
                    protected void onError(ErrorBean errorBean) {
                        if (callback != null) {
                            Throwable throwable = new Throwable(errorBean.getErrorResult());
                            callback.onError(throwable);
                        }

                    }
                });
    }


}
