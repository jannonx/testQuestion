package com.guyuan.dear.focus.projectsite.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.databinding.FragmentFocusSiteExplorationBinding;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.bean.CustomerAcceptanceSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.EventFocusSiteListRefresh;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.client.fragment.FollowCommentDialog;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
import com.guyuan.dear.work.projectsite.bean.EventWorkSiteListRefresh;
import com.guyuan.dear.work.projectsite.bean.OnConfirmDialogListenerImpl;
import com.guyuan.dear.work.projectsite.bean.PostAnswerInfo;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.bean.PostCustomerAcceptanceInfo;
import com.guyuan.dear.work.projectsite.bean.PostInstallationDebugInfo;
import com.guyuan.dear.work.projectsite.fragment.ProjectCheckConfirmDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * @description: 我的关注--工程现场--现场勘查报告--详情页面
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusSiteExplorationDetailFragment extends BaseDataBindingFragment<FragmentFocusSiteExplorationBinding, FocusProjectSiteViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener, View.OnClickListener {

    public static final String TAG = FocusSiteExplorationDetailFragment.class.getSimpleName();

    private ProjectSiteStatusFragment statusFragment;
    //安装调试--安装动态
    private InstallDebugStatusFragment installDebugStatusFragment;
    //客户验收--验收记录
    private AcceptanceRecordFragment recordFragment;
    private ExploreContentFragment planFragment;

    private ProjectCheckConfirmDialog leftDialog;
    private ProjectCheckConfirmDialog rightDialog;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private SiteExploreBean detailProjectData;
    protected ArrayList<Uri> photoList = new ArrayList<>();
    private ProduceApplyDialog dialog;
    private PostInstallationDebugInfo postInstallationDebugInfo;
    private PostCustomerAcceptanceInfo postCustomerAcceptanceInfo;
    private FocusSiteExplorationDetailActivity activity;

    public static FocusSiteExplorationDetailFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        FocusSiteExplorationDetailFragment fragment = new FocusSiteExplorationDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_site_exploration;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        detailProjectData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        binding.tvPauseBtn.setOnClickListener(this);
        binding.tvCompleteBtn.setOnClickListener(this);
        binding.tvActivateBtn.setOnClickListener(this);

        initViewPager();
        getDataFromNet();
        getDetailDataByClassify();

    }

    private void getDataFromNet() {
        LogUtils.showLog("getDataFromNet=" + detailProjectData.getModuleType().getDes());

        //评论
        viewModel.getPostAnswerInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
                ToastUtils.showShort(getContext(), "评论成功!");
                EventBus.getDefault().post(new EventAnswerListRefresh());
            }
        });


        viewModel.getSiteExploreDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                LogUtils.showLog("getDataFromNet000=" + detailProjectData.getModuleType().getDes());
                setProduceData(data);
                LogUtils.showLog("getDataFromNet111=" + detailProjectData.getModuleType().getDes());
            }
        });
        viewModel.getCheckGoodDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getCheckSafeDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getInstallDebugDetailBySingleEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getCustomerAcceptanceDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });

        //提交--安装调试
        viewModel.getPostInstallDebugInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                LogUtils.showLog("onCommitInstallationDebugInfo...onChanged");
                refreshDataStatus();
            }
        });
        //提交--安装调试
        viewModel.getCommitCustomerAcceptanceInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                ToastUtils.showLong(getContext(), "提交完成");
                refreshDataStatus();
            }
        });

        viewModel.getUploadImageEvent().observe(this, new Observer<List<UploadBean>>() {
            @Override
            public void onChanged(List<UploadBean> dataList) {
                if (dataList.isEmpty()) return;
                List<String> imageUrlList = new ArrayList<>();
                for (UploadBean bean : dataList) {
                    LogUtils.showLog("upLoadPicAndVideo=" + bean.getUrl());
                    imageUrlList.add(bean.getUrl());
                }
                postInfo(imageUrlList);
            }
        });
    }

    /**
     * 改变状态
     */
    private void refreshDataStatus() {
        ToastUtils.showLong(getContext(), "提交完成");
        getDetailDataByClassify();
        EventBus.getDefault().post(new EventWorkSiteListRefresh());
        EventBus.getDefault().post(new EventAnswerListRefresh());
    }

    /**
     * 根据报告类型请求数据
     */
    private void getDetailDataByClassify() {
        switch (detailProjectData.getProjectReportType()) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                viewModel.getSiteExploreDetailData(detailProjectData.getId());
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                viewModel.getCheckGoodDetailData(detailProjectData.getId());
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                viewModel.getCheckSafeDetailData(detailProjectData.getId());
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                viewModel.getInstallDebugDetailDataBySingle(detailProjectData.getId());
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                viewModel.getCustomerAcceptanceDetailData(detailProjectData.getId());
                break;

            default:
        }


    }

    /**
     * 设置数据
     *
     * @param data 详情数据
     */
    private void setProduceData(SiteExploreBean data) {
        if (getActivity() == null) return;
        data.setProjectReportType(detailProjectData.getProjectReportType());
        data.setModuleType(detailProjectData.getModuleType());
        detailProjectData = data;
        //设置审核数据
        planFragment.setCheckContentData(detailProjectData);
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


        //状态属性设置
        binding.tvProjectStatus.setText(detailProjectData.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(detailProjectData.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(detailProjectData.getStatusTextBg());
        int statusTextColor = detailProjectData.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pause_btn:
                clickLeftBtn();
                break;
            case R.id.tv_complete_btn:
                clickRightBtn();
                break;
            case R.id.tv_activate_btn:
                /**
                 * 1.安装调试：继续安装
                 * 2.其他：反馈问题
                 */
                if (ProjectReportType.TYPE_INSTALLATION_DEBUG == detailProjectData.getProjectReportType()) {
                    clickLeftBtn();
                } else {
                    postStatusInfo();
                }
                break;
        }
    }

    /**
     * 其他：反馈问题
     */
    private void postStatusInfo() {
        FollowCommentDialog.show(getActivity(), new FollowCommentDialog.OnFollowClickListener() {
            @Override
            public void onClick(String content) {
                PostAnswerInfo answerInfo = new PostAnswerInfo();
                answerInfo.setIdea(content);
                answerInfo.setPsAuditId(detailProjectData.getId());
                answerInfo.setType(detailProjectData.getProjectReportType().getCode());
                String str = GsonUtil.objectToString(answerInfo);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);
                viewModel.postAnswerInfo(requestBody);
            }
        });

    }

    private void clickLeftBtn() {
        leftDialog = new ProjectCheckConfirmDialog(getActivity(), detailProjectData, new OnConfirmDialogListenerImpl() {
            @Override
            public void onPickImageClick() {
                activity.openAlbum(BaseTabActivity.FIRST);
            }

            @Override
            public void onCommitInstallationDebugInfo(PostInstallationDebugInfo data) {
                LogUtils.showLog("onCommitInstallationDebugInfo");
                //安装调试
                int codeStatus = binding.tvPauseBtn.getText().toString().equals("暂停") ?
                        InstallDebugSatisfyType.TYPE_INSTALL_PAUSE.getCode() : InstallDebugSatisfyType.TYPE_INSTALL_ING.getCode();
                data.setStatus(codeStatus);
                postInstallationDebugInfo = data;
                activity.checkPhotoAndFileUpLoad(data.getImgUrl());
            }

            @Override
            public void onCommitCustomerAcceptanceInfo(PostCustomerAcceptanceInfo data) {
                //验收状态，0:待验收，10合格，20不合格
                data.setCheckStatus(20);
                postCustomerAcceptanceInfo = data;
                activity.checkPhotoAndFileUpLoad(data.getCheckUrl());
            }
        });
        leftDialog.show();

        leftDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                LogUtils.showLog("leftDialog....dismiss");
                leftDialog = null;
            }
        });
    }

    private void clickRightBtn() {
        rightDialog = new ProjectCheckConfirmDialog(getActivity(), detailProjectData, new OnConfirmDialogListenerImpl() {
            @Override
            public void onPickImageClick() {
                activity.openAlbum(BaseTabActivity.FIRST);
            }

            @Override
            public void onCommitInstallationDebugInfo(PostInstallationDebugInfo data) {
                LogUtils.showLog("onCommitInstallationDebugInfo");
                //安装调试
                data.setStatus(InstallDebugSatisfyType.TYPE_INSTALL_COMPLETE.getCode());
                postInstallationDebugInfo = data;
                activity.checkPhotoAndFileUpLoad(data.getImgUrl());
            }

            @Override
            public void onCommitCustomerAcceptanceInfo(PostCustomerAcceptanceInfo data) {
                //验收状态，0:待验收，10合格，20不合格
                data.setCheckStatus(10);
                postCustomerAcceptanceInfo = data;
                activity.checkPhotoAndFileUpLoad(data.getCheckUrl());
            }
        });
        rightDialog.show();
        rightDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                LogUtils.showLog("rightDialog....dismiss");
                rightDialog = null;
            }
        });
    }


    private void postInfo(List<String> imageUrlList) {
        switch (detailProjectData.getProjectReportType()) {
            case TYPE_INSTALLATION_DEBUG:
                postInstallationDebugInfo.setImgUrl(imageUrlList);
                String installStr = GsonUtil.objectToString(postInstallationDebugInfo);
                RequestBody installRequestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), installStr);
                viewModel.postInstallDebugInfo(installRequestBody);
                break;
            case TYPE_CUSTOMER_ACCEPTANCE:
                postCustomerAcceptanceInfo.setCheckUrl(imageUrlList);
                String customerStr = GsonUtil.objectToString(postCustomerAcceptanceInfo);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), customerStr);
                viewModel.postCustomerAcceptanceInfo(requestBody);
                break;

            default:
        }
    }

    /**
     * 现场勘查报告
     *
     * @param detailProjectData 数据
     */
    private void setSiteExplorationData(SiteExploreBean detailProjectData) {
        binding.tvProjectName.setText(detailProjectData.getProjectName());

        binding.tvProjectCode.setText(detailProjectData.getProjectNum());
        binding.tvCheckName.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());

        //我的关注不显示
        LogUtils.showLog("ProjectModuleType=" + detailProjectData.getModuleType().getDes());
        binding.tvActivateBtn.setText("反馈问题");
        binding.tvActivateBtn.setVisibility(FunctionModuleType.TYPE_FOCUS == detailProjectData.getModuleType() ? View.GONE : View.VISIBLE);
    }

    /**
     * 现场勘查报告
     *
     * @param detailProjectData 数据
     */
    private void setCheckGoodsData(SiteExploreBean detailProjectData) {
        binding.tvProjectName.setText(detailProjectData.getSendGoodsNumber());
        binding.labelProjectCode.setText("项目名称：");
        binding.tvProjectCode.setText(detailProjectData.getProjectName());
        binding.labelCheckName.setText("项目编号：");
        binding.tvCheckName.setText(detailProjectData.getProjectNumber());


        binding.tvCompanyName.setText(detailProjectData.getCustomerName());
        binding.tvTime.setText(detailProjectData.getCheckTime());
        binding.tvCompanyLocation.setText(detailProjectData.getAcceptAddress());

        binding.tvActivateBtn.setText("反馈问题");
        binding.tvActivateBtn.setVisibility(FunctionModuleType.TYPE_FOCUS == detailProjectData.getModuleType() ? View.GONE : View.VISIBLE);
    }

    /**
     * 现场勘查报告
     *
     * @param detailProjectData 数据
     */
    private void setCheckSafeData(SiteExploreBean detailProjectData) {

        binding.tvProjectName.setText(detailProjectData.getProjectName());


        binding.tvProjectCode.setText(detailProjectData.getProjectNum());
        binding.labelCheckName.setText("安全人员：");
        binding.tvCheckName.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());

        binding.tvActivateBtn.setText("反馈问题");
        binding.tvActivateBtn.setVisibility(FunctionModuleType.TYPE_FOCUS == detailProjectData.getModuleType() ? View.GONE : View.VISIBLE);
    }

    /**
     * 现场勘查报告
     *
     * @param detailProjectData 数据
     */
    private void setInstallationDebugData(SiteExploreBean detailProjectData) {
        LogUtils.showLog("onCommitInstallationDebugInfo...setInstallationDebugData");
        binding.tvProjectName.setText(detailProjectData.getProjectName());

        binding.labelProjectCode.setText("施工：");
        binding.tvProjectCode.setText(detailProjectData.getCustomerName());
        binding.labelCheckName.setText("负责人：");
        binding.tvCheckName.setText(detailProjectData.getPersonLiableName());

        binding.clCustomerPanel.setVisibility(View.GONE);
        binding.tvInstallTime.setVisibility(View.VISIBLE);
        binding.tvInstallTime.setText(detailProjectData.getDebugStartTime());

        String leftText = detailProjectData.getInstallDebugSatisfyType() == InstallDebugSatisfyType.TYPE_INSTALL_PAUSE ?
                "继续" : "暂停";
        binding.tvPauseBtn.setText(leftText);
        binding.tvCompleteBtn.setText("完工");
        //我的关注，我的工作(暂停，完成)不显示
        binding.llApplyPanel.setVisibility(FunctionModuleType.TYPE_FOCUS == detailProjectData.getModuleType()
                ? View.GONE : InstallDebugSatisfyType.TYPE_INSTALL_COMPLETE == detailProjectData.getInstallDebugSatisfyType()
                || InstallDebugSatisfyType.TYPE_INSTALL_PAUSE == detailProjectData.getInstallDebugSatisfyType() ? View.GONE : View.VISIBLE);
        //暂停，隐藏--llApplyPanel，显示--tvActivateBtn
        binding.tvActivateBtn.setText("继续安装");
        binding.tvActivateBtn.setVisibility(FunctionModuleType.TYPE_FOCUS == detailProjectData.getModuleType()
                ? View.GONE : InstallDebugSatisfyType.TYPE_INSTALL_PAUSE == detailProjectData.getInstallDebugSatisfyType()
                ? View.VISIBLE : View.GONE);
    }

    /**
     * 现场勘查报告
     *
     * @param detailProjectData 数据
     */
    private void setCustomerAcceptanceData(SiteExploreBean detailProjectData) {

        binding.tvProjectName.setText(detailProjectData.getCustomerName());

        binding.labelProjectCode.setText("合同编号：");
        binding.tvProjectCode.setText(detailProjectData.getContractNum());
        binding.labelCheckName.setText("项目名称：");
        binding.tvCheckName.setText(detailProjectData.getProjectName());

        binding.rlAddress.setVisibility(View.VISIBLE);
        binding.tvAddress.setText(detailProjectData.getAddress());

        binding.tvCompanyName.setText("现场监工：" + detailProjectData.getPersonLiableName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setVisibility(View.GONE);

        binding.tvPauseBtn.setText("验收不合格");
        binding.tvCompleteBtn.setText("验收合格");
        LogUtils.showLog("TYPE_WORK=" + detailProjectData.getModuleType().getDes());
        binding.llApplyPanel.setVisibility(FunctionModuleType.TYPE_FOCUS == detailProjectData.getModuleType() ? View.GONE :
                CustomerAcceptanceSatisfyType.TYPE_ACCEPTANCE_OK == detailProjectData.getCustomerAcceptanceSatisfyType()
                        || CustomerAcceptanceSatisfyType.TYPE_ACCEPTANCE_EXCEPTION == detailProjectData.getCustomerAcceptanceSatisfyType() ?
                        View.GONE : View.VISIBLE);


        if (recordFragment != null) {
            recordFragment.setRecordData(detailProjectData);
        }
    }

    private void initViewPager() {
        List<Fragment> tabFragmentList = new ArrayList<>();

        statusFragment = ProjectSiteStatusFragment.newInstance(detailProjectData);
        installDebugStatusFragment = InstallDebugStatusFragment.newInstance(detailProjectData);
        recordFragment = AcceptanceRecordFragment.newInstance();

        tabFragmentList.add(ProjectReportType.TYPE_INSTALLATION_DEBUG == detailProjectData.getProjectReportType()
                ? installDebugStatusFragment : ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE == detailProjectData.getProjectReportType() ?
                recordFragment : statusFragment);
        planFragment = ExploreContentFragment.newInstance(detailProjectData);
        tabFragmentList.add(planFragment);

        titleList = getResources().getStringArray(detailProjectData.getProjectReportTabArray());
        TabAdapter tabAdapter = new
                TabAdapter(getChildFragmentManager(), Arrays.asList(titleList), tabFragmentList, getContext(),
                getCustomViewId());

        binding.viewPager.setAdapter(tabAdapter);

        //设置TabLayout和ViewPager联动
        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.tabLayout.setTabIndicatorFullWidth(false);
        binding.tabLayout.setupWithViewPager(binding.viewPager, false);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            View view = tabAdapter.getView();
            if (tab != null) {
                setContent(i, view);
                tab.setCustomView(view);
                if (i == startPosition) {
                    setTabSelect(tab);
                    tab.select();
                } else {
                    setTabUnselected(tab);
                }
            }
        }
    }


    private int getCustomViewId() {
        return R.layout.layout_tab_text;
    }

    protected void setTabSelect(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (selectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        }
        tv.setTextSize(15f);
    }

    protected void setTabUnselected(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (unSelectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        }
        tv.setTextSize(15f);
    }


    private void setContent(int position, View customView) {
        TextView tv = customView.findViewById(R.id.tv_tab_text);
        tv.setText(Arrays.asList(titleList).get(position));
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


    @Override
    public ArrayList<Uri> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<Uri> dataList) {
        photoList.clear();
        photoList.addAll(dataList);
        if (leftDialog != null) {
            leftDialog.setPhotoList(dataList);
        }

        if (rightDialog != null) {
            rightDialog.setPhotoList(dataList);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (FocusSiteExplorationDetailActivity) getActivity();
        }
    }
}
