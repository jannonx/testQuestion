package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.ListProjectRequestBody;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.fragment.ProjectReportClassifyFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.projectsite.activity.WorkCheckGoodsActivity;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--货物清点报告--列表页面
 * @author: 许建宁
 * @since: 2020/11/18 10:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkCheckGoodsListFragment extends BaseListSearchFragment<SiteExploreBean, FragmentListBinding, WorkProjectSiteViewModel> {

    public static final String TAG = ProjectReportClassifyFragment.class.getSimpleName();
    private ProjectReportType reportType;


    public static WorkCheckGoodsListFragment newInstance(ProjectReportType type) {
        Bundle args = new Bundle();
        WorkCheckGoodsListFragment fragment = new WorkCheckGoodsListFragment();
        args.putSerializable(ConstantValue.KEY_CONTENT, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        etSearch.setHint("输入项目名称、编号、人员");
        reportType = (ProjectReportType) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        LogUtils.showLog("siteExploreBean...init="+reportType.getDes());
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
                LogUtils.showLog("siteExploreBean...init="+listData.get(position).getProjectReportType().getDes());
                WorkCheckGoodsActivity.start(getContext(),listData.get(position));
            }
        });


        viewModel.getCheckGoodList(getListRequestBody(true));
        viewModel.getCheckGoodListEvent().observe(getActivity(), new Observer<RefreshBean<SiteExploreBean>>() {
            @Override
            public void onChanged(RefreshBean<SiteExploreBean> data) {
                dealDataByAddReportType(data.getContent());
            }
        });
    }



    /**
     * 给每一条数据，添加报告类型
     *
     * @param dataList 数据
     */
    private void dealDataByAddReportType(List<SiteExploreBean> dataList) {
        List<SiteExploreBean> tempList = new ArrayList<>();
        for (SiteExploreBean bean : dataList) {
            LogUtils.showLog("siteExploreBean...dealDataByAddReportType="+reportType.getDes());
            bean.setProjectReportType(reportType);
            tempList.add(bean);
        }
        LogUtils.showLog("dataList="+dataList.size());
        setListData(tempList);
    }


    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListProjectRequestBody body = new ListProjectRequestBody();
        ListProjectRequestBody.FiltersBean filtersBean = new ListProjectRequestBody.FiltersBean();
        filtersBean.setQueryParams(searchContent);
        filtersBean.setMyWork(ProjectModuleType.TYPE_WORK.getCode());
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected void refresh() {
        viewModel.getCheckGoodList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getCheckGoodList(getListRequestBody(false));
    }

    @Override
    protected boolean isPullEnable() {
        return true;
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
