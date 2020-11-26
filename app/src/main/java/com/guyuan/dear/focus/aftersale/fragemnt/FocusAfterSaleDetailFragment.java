package com.guyuan.dear.focus.aftersale.fragemnt;

import android.content.Context;
import android.content.DialogInterface;
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
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.bean.CustomerAcceptanceSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.focus.projectsite.fragment.AcceptanceRecordFragment;
import com.guyuan.dear.focus.projectsite.fragment.ExploreContentFragment;
import com.guyuan.dear.focus.projectsite.fragment.InstallDebugStatusFragment;
import com.guyuan.dear.focus.projectsite.fragment.ProjectSiteStatusFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.client.fragment.FollowCommentDialog;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
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
 * @description: 我的关注--售后服务--详情页面
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusAfterSaleDetailFragment extends BaseDataBindingFragment<FragmentFocusSiteExplorationBinding, FocusAfterSaleViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener, View.OnClickListener {

    public static final String TAG = FocusAfterSaleDetailFragment.class.getSimpleName();

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
    protected ArrayList<String> photoList = new ArrayList<>();
    private ProduceApplyDialog dialog;

    private FocusSiteExplorationDetailActivity activity;

    public static FocusAfterSaleDetailFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        FocusAfterSaleDetailFragment fragment = new FocusAfterSaleDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        LogUtils.showLog("newInstance=" + data.getModuleType().getDes());
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
        LogUtils.showLog("initialization=" + detailProjectData.getModuleType().getDes());
        binding.tvPauseBtn.setOnClickListener(this);
        binding.tvCompleteBtn.setOnClickListener(this);
        binding.tvActivateBtn.setOnClickListener(this);

        initViewPager();

        viewModel.getAfterSaleDetail(detailProjectData.getId());
        viewModel.getAfterSaleDetailEvent().observe(getActivity(), new Observer<AfterSaleBean>() {
            @Override
            public void onChanged(AfterSaleBean data) {

            }
        });
//        getDataFromNet();
//        getDetailDataByClassify();

    }




    /**
     * 设置数据
     *
     * @param data 详情数据
     */
    private void setProduceData(SiteExploreBean data) {
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
                break;
        }
    }



    private void clickRightBtn() {
        rightDialog = new ProjectCheckConfirmDialog(getActivity(), detailProjectData, new ProjectCheckConfirmDialog.OnDialogClickListener() {
            @Override
            public void onPickImageClick() {
                activity.openAlbum(BaseTabActivity.FIRST);
            }

            @Override
            public void onCommitCheckGoodsInfo(PostCheckInfo data) {

            }

            @Override
            public void onCommitInstallationDebugInfo(PostInstallationDebugInfo data) {
                LogUtils.showLog("onCommitInstallationDebugInfo");
                //安装调试
                data.setStatus(InstallDebugSatisfyType.TYPE_INSTALL_COMPLETE.getCode());
                activity.checkPhotoAndFileUpLoad(data.getImgUrl());
            }

            @Override
            public void onCommitCustomerAcceptanceInfo(PostCustomerAcceptanceInfo data) {
                //验收状态，0:待验收，10合格，20不合格
                data.setCheckStatus(10);

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

    private void clickLeftBtn() {

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
        binding.tvActivateBtn.setVisibility(ProjectModuleType.TYPE_FOCUS == detailProjectData.getModuleType() ? View.GONE : View.VISIBLE);
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
    }


    private void initViewPager() {
        List<Fragment> tabFragmentList = new ArrayList<>();

        statusFragment = ProjectSiteStatusFragment.newInstance(detailProjectData);
        installDebugStatusFragment = InstallDebugStatusFragment.newInstance(detailProjectData);
        recordFragment = AcceptanceRecordFragment.newInstance(detailProjectData);

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
    public ArrayList<String> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<String> photoList) {
        photoList.addAll(photoList);
        if (leftDialog != null) {
            leftDialog.setPhotoList(photoList);
        }

        if (rightDialog != null) {
            rightDialog.setPhotoList(photoList);
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
