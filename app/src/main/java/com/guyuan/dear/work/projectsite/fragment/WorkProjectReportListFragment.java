package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.CheckSafeSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ListProjectRequestBody;
import com.guyuan.dear.focus.projectsite.bean.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.bean.SiteProjectSatisfyType;
import com.guyuan.dear.focus.projectsite.fragment.ProjectReportClassifyFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.activity.WorkCheckGoodsActivity;
import com.guyuan.dear.work.projectsite.activity.WorkInstallDebugActivity;
import com.guyuan.dear.work.projectsite.activity.WorkSiteExploresActivity;
import com.guyuan.dear.work.projectsite.bean.EventCheckGoodsListRefresh;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

import static com.guyuan.dear.focus.projectsite.bean.ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE;

/**
 * @description: 我的工作--工程现场
 * --现场勘查报告/货物清点报告/安全排查报告/安装调试报告/客户验收报告/
 * --列表页面
 * @author: 许建宁
 * @since: 2020/11/18 10:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProjectReportListFragment extends BaseListSearchFragment<SiteExploreBean, FragmentListBinding, WorkProjectSiteViewModel> {

    public static final String TAG = ProjectReportClassifyFragment.class.getSimpleName();
    private ProjectReportType reportType;
    private boolean isFirstLoad = true;

    public static WorkProjectReportListFragment newInstance(ProjectReportType type) {
        Bundle args = new Bundle();
        WorkProjectReportListFragment fragment = new WorkProjectReportListFragment();
        args.putSerializable(ConstantValue.KEY_CONTENT, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {

        reportType = (ProjectReportType) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        etSearch.setHint(TYPE_CUSTOMER_ACCEPTANCE == reportType
                ? "输入客户名称、项目名称" : "输入项目名称、编号、人员");
        LogUtils.showLog("siteExploreBean=" + reportType.getDes());
        ProjectReportAdapter checkGoodsAdapter = new ProjectReportAdapter(getContext(),
                listData, R.layout.item_focus_project_site);
        adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setPullRefreshEnabled(isPullEnable());
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpByReportType(listData.get(position));
            }
        });
        getDataListByClassify(true);

    }


    /**
     * 根据数据类型以及状态跳转到具体页面
     *
     * @param siteExploreBean 数据
     */
    private void jumpByReportType(SiteExploreBean siteExploreBean) {
//        siteExploreBean.setModuleType(FunctionModuleType.TYPE_WORK);
        LogUtils.showLog("jumpByReportType=" + siteExploreBean.getProjectReportType().getDes());
        switch (reportType) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                LogUtils.showLog("jumpByReportType=" + siteExploreBean.getModuleType().getDes());
                SiteProjectSatisfyType siteProjectSatisfyType = siteExploreBean.getSiteProjectSatisfyType();
                if (SiteProjectSatisfyType.TYPE_EXPLORE_WAIT == siteProjectSatisfyType
                        || SiteProjectSatisfyType.TYPE_EXPLORE_ING == siteProjectSatisfyType) {
                    WorkSiteExploresActivity.start(getContext(), siteExploreBean);
                } else {
                    FocusSiteExplorationDetailActivity.start(getContext(), siteExploreBean);
                }

                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                LogUtils.showLog("siteExploreBean=" + siteExploreBean.getProjectReportType().getDes());
                //运输中,待清点
                if (siteExploreBean.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                        || siteExploreBean.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_CHECK_ING) {
                    WorkCheckGoodsActivity.start(getContext(), siteExploreBean);
                } else {
                    FocusSiteExplorationDetailActivity.start(getContext(), siteExploreBean);
                }
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                CheckSafeSatisfyType checkSafeSatisfyType = siteExploreBean.getCheckSafeSatisfyType();
                if (CheckSafeSatisfyType.TYPE_CHECK_WAIT == checkSafeSatisfyType
                        || CheckSafeSatisfyType.TYPE_CHECK_ING == checkSafeSatisfyType) {
                    WorkSiteExploresActivity.start(getContext(), siteExploreBean);
                } else {
                    FocusSiteExplorationDetailActivity.start(getContext(), siteExploreBean);
                }

                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
//                siteExploreBean.setModuleType(FunctionModuleType.TYPE_WORK);
                WorkInstallDebugActivity.start(getContext(), siteExploreBean);
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                FocusSiteExplorationDetailActivity.start(getContext(), siteExploreBean);
                break;

            default:
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //第一次加载不刷新，再次进入页面刷新数据
        if (!isFirstLoad && reportType == TYPE_CUSTOMER_ACCEPTANCE) {
            viewModel.getCustomerAcceptanceList(getListRequestBody(true));
        }

        isFirstLoad = false;
    }

    /**
     * 根据报告类型请求数据
     *
     * @param isRefresh 是否刷新
     */
    public void getDataListByClassify(boolean isRefresh) {
        switch (reportType) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                viewModel.getSiteExploreList(getListRequestBody(isRefresh));
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                viewModel.getCheckGoodList(getListRequestBody(isRefresh));
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                viewModel.getCheckSafeList(getListRequestBody(isRefresh));
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                viewModel.getInstallDebugList(getListRequestBody(isRefresh));
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                viewModel.getCustomerAcceptanceList(getListRequestBody(isRefresh));
                break;

            default:
        }

    }


    /**
     * 给每一条数据，添加报告类型
     *
     * @param dataList 数据
     */
    public void dealDataByAddReportType(List<SiteExploreBean> dataList, ProjectReportType reportType) {
        LogUtils.showLog("size=" + dataList.size());
        List<SiteExploreBean> tempList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            SiteExploreBean siteExploreBean = dataList.get(i);
            siteExploreBean.setModuleType(FunctionModuleType.TYPE_WORK);
            siteExploreBean.setProjectReportType(reportType);
            LogUtils.showLog("dealDataByAddReportType=" + reportType.getDes());
            tempList.add(siteExploreBean);
        }
        setListData(tempList);
    }


    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListProjectRequestBody body = new ListProjectRequestBody();
        ListProjectRequestBody.FiltersBean filtersBean = new ListProjectRequestBody.FiltersBean();
        filtersBean.setQueryParams(searchContent);
        filtersBean.setMyWork(FunctionModuleType.TYPE_WORK.getCode());
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }


    /**
     * 刷新列表
     */
    public void refreshList() {
//        ToastUtils.showLong(getContext(), "提交成功");
        getDataListByClassify(true);
    }

    @Override
    protected void refresh() {
        getDataListByClassify(true);
    }

    @Override
    protected void loadMore() {
        getDataListByClassify(false);
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
