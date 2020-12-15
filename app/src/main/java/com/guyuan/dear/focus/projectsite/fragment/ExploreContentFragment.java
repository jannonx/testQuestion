package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FooterExploreContentBinding;
import com.guyuan.dear.databinding.FragmentExploreContentBinding;
import com.guyuan.dear.focus.projectsite.adapter.CheckContentAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.type.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.type.CheckSafeSatisfyType;
import com.guyuan.dear.focus.projectsite.type.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.type.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.focus.projectsite.type.SiteProjectSatisfyType;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.projectsite.adapter.CheckGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;


/**
 * @description: 我的关注--工程现场
 * --现场勘查报告/货物清点报告/安全排查报告/安装调试报告/客户验收报告--详情页面
 * --右边
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
    private FooterExploreContentBinding footerBinding;
    private List<ProjectSiteOpinionBean> siteExploreContentList = new ArrayList<>();
    private List<CheckGoodsBean> checkGoodsContentList = new ArrayList<>();
    private List<InstallDebugBean> installDebugContentList = new ArrayList<>();
    List<String> imageDataList = new ArrayList<>();
    private SiteExploreBean simpleData;
    private View footerView;
    private LinearLayoutCompat llContent;
    private TextView tvRemark, tvStatus;

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

    }

    /**
     * 设置数据
     *
     * @param detailProjectData 报告数据
     */
    public void setCheckContentData(SiteExploreBean detailProjectData) {
        simpleData = detailProjectData;
        ProjectReportType projectReportType = detailProjectData.getProjectReportType();
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        setReportAdapterByType(projectReportType);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        if (detailProjectData.getPsAuditItemVOList() != null) {
            siteExploreContentList.clear();
            siteExploreContentList.addAll(detailProjectData.getPsAuditItemVOList());
        }
        if (detailProjectData.getCheckTransportProjectListVO() != null) {
            checkGoodsContentList.clear();
            checkGoodsContentList.addAll(detailProjectData.getCheckTransportProjectListVO());
        }
        if (detailProjectData.getAppInstallDebugItemVOList() != null) {
            installDebugContentList.clear();
            installDebugContentList.addAll(detailProjectData.getAppInstallDebugItemVOList());
        }

        addContentFooterView();

    }


    /**
     * 添加列表FooterView,增加拖动范围
     */
    private void addContentFooterView() {
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_explore_content, binding.baseRecycleView, false);

        tvRemark = footerView.findViewById(R.id.tv_remark);
        tvStatus = footerView.findViewById(R.id.tv_status);
        RelativeLayout videoPanel = footerView.findViewById(R.id.video_panel);
        llContent = footerView.findViewById(R.id.llc_content);
        LinearLayoutCompat llRemark = footerView.findViewById(R.id.ll_remark);

        LinearLayoutCompat llDocument = footerView.findViewById(R.id.ll_document);
        TextView labelDocument = footerView.findViewById(R.id.label_document);
        TextView tvTip = footerView.findViewById(R.id.tv_tip);

        BaseRecyclerView imageRecyclerView = footerView.findViewById(R.id.image_recycleView);

        if (simpleData.getImgUrlList() != null) {
            imageDataList.clear();
            imageDataList.addAll(simpleData.getImgUrlList());
        }

        llDocument.setVisibility(imageDataList.size() == 0 ? View.GONE : View.VISIBLE);

        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imageRecyclerView.setAdapter(imageAdapter);
        imageRecyclerView.setPullRefreshEnabled(false);
        imageRecyclerView.setLoadMoreEnabled(false);


        adapter.addFooterView(footerView);
//        客户验收隐藏
        llDocument.setVisibility(ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE == simpleData.getProjectReportType() ? View.GONE : View.VISIBLE);
        llRemark.setVisibility(ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE == simpleData.getProjectReportType() ? View.GONE : View.VISIBLE);
//        安装调试
        llContent.setVisibility(ProjectReportType.TYPE_INSTALLATION_DEBUG == simpleData.getProjectReportType() ? View.GONE : View.VISIBLE);
        videoPanel.setVisibility(ProjectReportType.TYPE_INSTALLATION_DEBUG == simpleData.getProjectReportType() ? View.VISIBLE : View.GONE);
        switch (simpleData.getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                setSiteExplorationData(simpleData);
                break;
            case TYPE_CHECK_GOODS:
                setCheckGoodsData(simpleData);
                break;
            case TYPE_CHECK_SAFE:
                setCheckSafeData(simpleData);
                break;
            case TYPE_INSTALLATION_DEBUG:
                setInstallationDebugData(simpleData);
                break;
            case TYPE_CUSTOMER_ACCEPTANCE:
                setCustomerAcceptanceData(simpleData);
                break;

            default:
        }

    }

    private void setCustomerAcceptanceData(SiteExploreBean detailProjectData) {

    }

    private void setInstallationDebugData(SiteExploreBean detailProjectData) {

    }

    private void setCheckSafeData(SiteExploreBean detailProjectData) {
        CheckSafeSatisfyType checkSafeSatisfyType = detailProjectData.getCheckSafeSatisfyType();

        //清点货物 #2FC25B  #F04864 红色
        tvRemark.setText(detailProjectData.getAuditItemExplain());
        //是否满足条件、是否安全(1:是，2:否)
        tvStatus.setTextColor(getActivity().getResources().getColor(
                detailProjectData.getSatisfyFlag() == 1 ? R.color.color_green_2fc25b : R.color.color_red_F04864));
        tvStatus.setText("存在安全隐患：" + (detailProjectData.getSatisfyFlag() == 1 ? "否" : "是"));

        llContent.setVisibility((CheckSafeSatisfyType.TYPE_CHECK_WAIT == checkSafeSatisfyType
                || CheckSafeSatisfyType.TYPE_CHECK_ING == checkSafeSatisfyType) ? View.GONE : View.VISIBLE);
    }

    private void setCheckGoodsData(SiteExploreBean detailProjectData) {
        CheckGoodsSatisfyType checkGoodsSatisfyType = detailProjectData.getCheckGoodsSatisfyType();
        tvRemark.setText(detailProjectData.getCheckRemark());
        tvStatus.setTextColor(getActivity().getResources().getColor(
                detailProjectData.getIsException() == CHECK_RIGHT ? R.color.color_green_2fc25b : R.color.color_red_F04864));
        tvStatus.setText("货物异常：" + (detailProjectData.getIsException() == CHECK_RIGHT ? "否" : "是"));

        llContent.setVisibility((checkGoodsSatisfyType == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                || checkGoodsSatisfyType == CheckGoodsSatisfyType.TYPE_GOODS_CHECK_ING) ? View.GONE : View.VISIBLE);
    }

    private void setSiteExplorationData(SiteExploreBean detailProjectData) {
        SiteProjectSatisfyType siteProjectSatisfyType = detailProjectData.getSiteProjectSatisfyType();
        //清点货物 #2FC25B  #F04864 红色
        tvRemark.setText(detailProjectData.getAuditItemExplain());
        //是否满足条件、是否安全(1:是，2:否)
        tvStatus.setTextColor(getActivity().getResources().getColor(
                detailProjectData.getSatisfyFlag() == 1 ? R.color.color_green_2fc25b : R.color.color_red_F04864));
        tvStatus.setText("满足条件：" + (detailProjectData.getSatisfyFlag() == 1 ? "是" : "否"));

        llContent.setVisibility((SiteProjectSatisfyType.TYPE_EXPLORE_WAIT == siteProjectSatisfyType
                || SiteProjectSatisfyType.TYPE_EXPLORE_ING == siteProjectSatisfyType) ? View.GONE : View.VISIBLE);
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
                CheckContentAdapter checkContentAdapter = new CheckContentAdapter(getContext(),
                        siteExploreContentList, R.layout.item_explorate_content, simpleData);
                adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                CheckGoodsAdapter checkGoodsAdapter = new CheckGoodsAdapter(getContext(),
                        checkGoodsContentList, R.layout.item_goods_list, FunctionModuleType.TYPE_FOCUS);
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
