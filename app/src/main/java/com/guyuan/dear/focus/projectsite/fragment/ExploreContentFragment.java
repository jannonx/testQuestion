package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentExploreContentBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.projectsite.adapter.CheckContentAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.adapter.GoodsListAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.adapter.CheckGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 我的关注--工程现场--货物清点报告--详情页面--到货清单
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class ExploreContentFragment extends BaseDataBindingFragment<FragmentExploreContentBinding, FocusProjectSiteViewModel> {
    /**
     * 清点是否异常，0正常，1异常
     */
    private static final int CHECK_RIGHT = 0;
    private static final int CHECK_WRONG = 1;
    public static final String TAG = ExploreContentFragment.class.getSimpleName();

    private BaseRecyclerViewAdapter adapter;
    private BaseRecyclerViewAdapter imageAdapter;
    private List<ProjectSiteOpinionBean> siteExploreContentList = new ArrayList<>();
    private List<CheckGoodsBean> checkGoodsContentList = new ArrayList<>();
    private List<InstallDebugBean> installDebugContentList = new ArrayList<>();
    List<String> imageDataList = new ArrayList<>();
    private SiteExploreBean simpleData;

    public static ExploreContentFragment newInstance(SiteExploreBean detailProjectData) {
        Bundle bundle = new Bundle();
        ExploreContentFragment fragment = new ExploreContentFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, detailProjectData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_explore_content;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        simpleData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        setReportAdapterByType(simpleData.getProjectReportType());
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);


        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);
        binding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        setReportAdapterByType(simpleData.getProjectReportType());
        binding.imageRecycleView.setAdapter(imageAdapter);
        binding.imageRecycleView.setPullRefreshEnabled(false);
        binding.imageRecycleView.setLoadMoreEnabled(false);

        //http://pic-bucket.ws.126.net/photo/0001/2020-11-09/FR0M2RL000AN0001NOS.jpg
        // FullScreenShowActivity.start(mContext, StringUtils.splicePhotoUrl(getTagDataList()),position);
    }

    /**
     * 设置数据
     *
     * @param detailProjectData
     */
    public void setCheckContentData(SiteExploreBean detailProjectData) {
        ProjectReportType projectReportType = detailProjectData.getProjectReportType();
        binding.llcContent.setVisibility(ProjectReportType.TYPE_INSTALLATION_DEBUG == projectReportType ? View.GONE : View.VISIBLE);
        binding.videoPanel.setVisibility(ProjectReportType.TYPE_INSTALLATION_DEBUG == projectReportType ? View.VISIBLE : View.GONE);
        LogUtils.showLog("工程现场=" + (detailProjectData.getPsAuditItemVOList() != null ? "" + detailProjectData.getPsAuditItemVOList().size() : "空"));
        LogUtils.showLog("购物清单=" + (detailProjectData.getCheckTransportProjectListVO() != null ? "" + detailProjectData.getCheckTransportProjectListVO().size() : "空"));
        LogUtils.showLog("安装调试=" + (detailProjectData.getAppInstallDebugItemVOList() != null ? "" + detailProjectData.getAppInstallDebugItemVOList().size() : "空"));
        siteExploreContentList.clear();
        checkGoodsContentList.clear();
        installDebugContentList.clear();
        if (detailProjectData.getPsAuditItemVOList() != null) {
            siteExploreContentList.addAll(detailProjectData.getPsAuditItemVOList());
        }
        if (detailProjectData.getCheckTransportProjectListVO() != null) {
            checkGoodsContentList.addAll(detailProjectData.getCheckTransportProjectListVO());
        }
        if (detailProjectData.getAppInstallDebugItemVOList() != null) {
            installDebugContentList.addAll(detailProjectData.getAppInstallDebugItemVOList());
        }
        adapter.refreshData();

        imageDataList.clear();
        LogUtils.showLog("图片="+detailProjectData.getImgUrlList());
//        imageDataList.addAll(detailProjectData.getImgUrlList());
//        imageAdapter.refreshData();

        switch (detailProjectData.getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                setSiteExplorationData(detailProjectData);
                break;
            case TYPE_CHECK_GOODS:
                setCheckGoodsData(detailProjectData);
                break;
            case TYPE_CHECK_SAFE:
                setCheckSafeData(detailProjectData);
                break;
            case TYPE_INSTALLATION_DEBUG:
                setInstallationDebugData(detailProjectData);
                break;
            case TYPE_CUSTOMER_ACCEPTANCE:
                setCustomerAcceptanceData(detailProjectData);
                break;

            default:
        }

    }

    private void setCustomerAcceptanceData(SiteExploreBean detailProjectData) {

    }

    private void setInstallationDebugData(SiteExploreBean detailProjectData) {

    }

    private void setCheckSafeData(SiteExploreBean detailProjectData) {
        //清点货物 #2FC25B  #F04864 红色
        binding.tvRemark.setText(detailProjectData.getAuditItemExplain());
        //是否满足条件、是否安全(1:是，2:否)
        binding.tvStatus.setTextColor(getActivity().getResources().getColor(
                detailProjectData.getSatisfyFlag() == 1 ? R.color.color_green_2fc25b : R.color.color_red_F04864));
        binding.tvStatus.setText("存在安全隐患：" + (detailProjectData.getSatisfyFlag() == 1 ? "否" : "是"));
    }

    private void setCheckGoodsData(SiteExploreBean detailProjectData) {
        //清点货物 #2FC25B  #F04864 红色
        binding.tvRemark.setText(detailProjectData.getCheckRemark());
        binding.tvStatus.setTextColor(getActivity().getResources().getColor(
                detailProjectData.getIsException() == CHECK_RIGHT ? R.color.color_green_2fc25b : R.color.color_red_F04864));
        binding.tvStatus.setText("货物异常：" + (detailProjectData.getIsException() == CHECK_RIGHT ? "否" : "是"));
    }

    private void setSiteExplorationData(SiteExploreBean detailProjectData) {
        //清点货物 #2FC25B  #F04864 红色
        binding.tvRemark.setText(detailProjectData.getAuditItemExplain());
        //是否满足条件、是否安全(1:是，2:否)
        binding.tvStatus.setTextColor(getActivity().getResources().getColor(
                detailProjectData.getSatisfyFlag() == 1 ? R.color.color_green_2fc25b : R.color.color_red_F04864));
        binding.tvStatus.setText("满足条件：" + (detailProjectData.getSatisfyFlag() == 1 ? "是" : "否"));
    }


    /**
     * 根据类型不同设置不同的adapter
     *
     * @param projectReportType 报告类型
     */
    private void setReportAdapterByType(ProjectReportType projectReportType) {
        LogUtils.showLog("报告类型" + projectReportType.getDes());
        switch (projectReportType) {
            ///现场勘查报告 - 安全排查报告
            case TYPE_SITE_EXPLORATION:
            case TYPE_CHECK_SAFE:
                simpleData.setModuleType(ProjectModuleType.TYPE_FOCUS);
                CheckContentAdapter checkContentAdapter = new CheckContentAdapter(getContext(),
                        siteExploreContentList, R.layout.item_explorate_content, simpleData);
                adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                CheckGoodsAdapter checkGoodsAdapter = new CheckGoodsAdapter(getContext(),
                        checkGoodsContentList, R.layout.item_goods_list, ProjectModuleType.TYPE_FOCUS);
                adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
                break;

            ///安装调试报告 - 客户验收报告
            case TYPE_INSTALLATION_DEBUG:
            case TYPE_CUSTOMER_ACCEPTANCE:
                ProjectInstallAdapter installDebugAdapter = new ProjectInstallAdapter(getContext(),
                        installDebugContentList, R.layout.item_install_project);
                adapter = new BaseRecyclerViewAdapter(installDebugAdapter);
                break;


            default:
        }
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
