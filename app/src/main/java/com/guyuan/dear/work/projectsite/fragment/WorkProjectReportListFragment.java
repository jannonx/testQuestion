package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.projectsite.activity.FocusCheckGoodsDetailActivity;
import com.guyuan.dear.focus.projectsite.activity.FocusCheckSafeDetailActivity;
import com.guyuan.dear.focus.projectsite.activity.FocusCustomerAcceptanceDetailActivity;
import com.guyuan.dear.focus.projectsite.activity.FocusInstallationDebugAllActivity;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场
 * --现场勘查报告/货物清点报告/安全排查报告/安装调试报告/客户验收报告/
 * --列表页面
 * @author: 许建宁
 * @since: 2020/11/18 10:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProjectReportListFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, FocusProjectSiteViewModel> {

    public static final String TAG = WorkProjectReportListFragment.class.getSimpleName();

    public static WorkProjectReportListFragment newInstance(ProjectReportType type) {
        Bundle args = new Bundle();
        WorkProjectReportListFragment fragment = new WorkProjectReportListFragment();
        args.putSerializable(ConstantValue.KEY_CONTENT, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        etSearch.setHint("输入项目名称、编号、人员");
        ProjectReportType reportType = (ProjectReportType) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        for (int i = 0; i < 5; i++) {
            SimpleTabBean simpleTabBean = new SimpleTabBean();
            listData.add(simpleTabBean);
        }

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
                jumpToDetail(reportType);
            }
        });
    }

    /**
     * 跳转到不同的详情页面
     *
     * @param reportType 报告类型
     */
    private void jumpToDetail(ProjectReportType reportType) {
        switch (reportType) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                FocusSiteExplorationDetailActivity.start(getContext(), ProjectReportType.TYPE_SITE_EXPLORATION);
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                FocusCheckGoodsDetailActivity.start(getContext(), ProjectReportType.TYPE_CHECK_GOODS);
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                FocusCheckSafeDetailActivity.start(getContext(), ProjectReportType.TYPE_CHECK_SAFE);
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                FocusInstallationDebugAllActivity.start(getContext(), ProjectReportType.TYPE_INSTALLATION_DEBUG);
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                FocusCustomerAcceptanceDetailActivity.start(getContext(), ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE);
                break;

            default:
        }
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
